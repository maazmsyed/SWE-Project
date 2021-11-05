package com.swe.justslidin.models;


import android.graphics.Bitmap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.network.Firebase;
import com.swe.justslidin.network.PlayerStats;
import com.swe.justslidin.view.GraphicsRenderer;
import android.util.Log;


import androidx.annotation.NonNull;

import java.util.List;
import java.util.Vector;

public class Universe {

    private static final String TAG = "Universe";
    final static Motion DEFAULT_GRAVITY_MOTION = new Motion(0,10f); // Added after referring prof

    public Motion getGravity() {
        return gravity;
    }

    private static final Constants constants = new Constants();
    Motion gravity;
    Character player;
    List<Elements> elements;
    Background background = new Background();
    private float additionalMotionY;
    private int speedUpCounter;
    private int speedDownCounter;
    FinishingLine finishingLine = new FinishingLine((constants.SCREEN_WIDTH / 2),
            constants.SCREEN_HEIGHT * 20); // TODO: When to end game?
    private volatile Boolean gameRunning;
    DatabaseReference refPlayerPos;

    // Other Player's Position
    private Position otherPlayerPos;
    private String otherPlayerID;
    DatabaseReference refOtherPlayerPos;


    public Universe () {
        this (DEFAULT_GRAVITY_MOTION, new Character(constants.SCREEN_WIDTH / 2,
                constants.SCREEN_HEIGHT / 4, constants.PLAYER_RADIUS));
    }

    public Universe (Motion g, Character pl) {

        refPlayerPos = Firebase.getDatabase().getReference(PlayerStats.playerID + "Pos");
        // Setting up other player's position
        otherPlayerPos = new Position(constants.SCREEN_WIDTH / 2, constants.SCREEN_HEIGHT / 4);
        if (PlayerStats.playerID.equals("playerOne")) { otherPlayerID = "playerTwo"; }
        else {otherPlayerID = "playerOne"; }
        refOtherPlayerPos = Firebase.getDatabase().getReference(otherPlayerID + "Pos");

        elements = new Vector<>();
        gravity = g;
        player = pl;
        additionalMotionY = DEFAULT_GRAVITY_MOTION.getY();
        speedUpCounter = 0;
        speedDownCounter = 0;
        gameRunning = true;
    }

    public Boolean isGameRunning() {
        return this.gameRunning;
    }

    public void setGameRunning(Boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        this.background.setBackgroundBitmap(bitmap);
    }

    public void setFinishingLineBitmap(Bitmap bitmap) {
        this.finishingLine.setFinishingBitmap(bitmap);
    }

    public FinishingLine getFinishingLine() {
        return this.finishingLine;
    }

    public Background getBackground() {
        return this.background;
    }

    public Position getOtherPlayerPos() {
        return otherPlayerPos;
    }

    /**
     * This method adds an instantiation of a coin to the elements list.
     *
     * @param x
     * This value defines the x-coordinate of the new coin instance's position.
     * @param y
     * This value defines the y-coordinate of the new coin instance's position.
     * @param rad
     * This value defines the radius (size) of the new coin instance.
     */
    public void addCoin(float x, float y, float rad) {
        Log.i(TAG, "Adding Coin");
        Coin newCoin = new Coin(x, y, rad);
        for (Elements elem : this.elements) {
            if (elem instanceof Barrier) {
                Barrier b = (Barrier) elem;
                HitBox hb = b.getHitBox();
                if (newCoin.getHitBox().collide(hb)) {
                    float bLeft = hb.getLeft();
                    float bRight = hb.getRight();
                    if (bLeft <= constants.SCREEN_WIDTH) {
                        newCoin = new Coin(bRight + (constants.SCREEN_WIDTH / 10), y, rad);
                        // newCoin.setPos(new Position(bRight + 100, y));
                    } else {
                        // newCoin.setPos(new Position(bLeft - 100, y));
                        newCoin = new Coin(bLeft - (constants.SCREEN_WIDTH / 10), y, rad);
                    }
                }
            }
        }
        elements.add(newCoin);
        castChanges();
    }

    public Character getPlayer(){
        return this.player;
    }

    /**
     * This method adds an instantiation of a barrier to the elements list.
     *
     * @param x
     * This value defines the x-coordinate of the new barrier instance's position.
     * @param y
     * This value defines the y-coordinate of the new barrier instance's position.
     * @param h
     * This value defines the height of the new barrier instance.
     */
    public void addBarrier(float x, float y, float h) {
        Log.i(TAG, "Adding Barrier");
        elements.add(new Barrier (x,y,h));
        castChanges();
    }

    /**
     * Gets the list of elements (list of coins and barriers in the game).
     *
     * @return elements
     */
    public List<Elements> getElements() {
        return elements;
    }

    /**
     * Moves all the elements in the elements list up by the natural gravity. This is because
     * all the coins and barriers in the game have a natural upward movement.
     */
    public void step() {
        this.updateOtherPlayerPos();
        this.setFirebasePlayerPos();
        this.speedUpCounter += 1;
        if (this.additionalMotionY <= (DEFAULT_GRAVITY_MOTION.getY() * 2)
                && this.speedUpCounter % 20 == 0) {
            this.additionalMotionY += 0.75;
            this.gravity.setY(additionalMotionY);
        }
        finishingLine.moveUp(this.gravity);
        background.moveUp(this.gravity);
        for (Elements e : elements) {
            e.moveUp(this.gravity);
        }
        this.player.updateAbsPosY(this.gravity);
        if (this.player.getHitBox().collide(this.finishingLine.getHitBox())) {
            gameRunning = false;
        }
        castChanges();
    }

    public void setFirebasePlayerPos() {
        refPlayerPos.child("X").setValue(player.getAbsolutePos().getX());
        Log.i(TAG, "Set main player's X position");
        refPlayerPos.child("Y").setValue(player.getAbsolutePos().getY());
        Log.i(TAG, "Set main player's Y position");

    }

    public void updateOtherPlayerPos() {

        refOtherPlayerPos.child("X").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // float tempX = snapshot.getValue(Integer.class);
                otherPlayerPos.setX(snapshot.getValue(Float.class));
                Log.i(TAG, "Get other player's X position");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "Did not set other player's X position");
            }
        });

        refOtherPlayerPos.child("Y").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                otherPlayerPos.setY(snapshot.getValue(Float.class));
                Log.i(TAG, "Get other player's Y position");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "Did not set other player's Y position");
            }
        });

    }


    /**
     * This is just another way to access the addCoin method. If you want to pass an instance of
     * the Position class as input instead of manually inputting the x- and y-coordinates.
     *
     * @param pos
     * The Position of the new coin instance.
     * @param rad
     * This value defines the radius (size) of the new coin instance.
     */
    public void addCoin(Position pos, float rad) {
        this.addCoin(pos.getX(),pos.getY(),rad);
    }

    /**
     * This is just another way to access the addBarrier method. If you want to pass an instance of
     * the Position class as input instead of manually inputting the x- and y-coordinates.
     *
     * @param pos
     * The Position of the new barrier instance.
     * @param h
     * This value defines the height of the new barrier instance.
     */
    public void addBarrier(Position pos, float h) {
        this.addBarrier(pos.getX(), pos.getY(),h);
    }

    public void checkPlayerCollision() {
        Vector<Elements> tempVec = new Vector<Elements>();
        for (Elements elem : this.elements) {
            if (elem instanceof Coin) {
                Coin c = (Coin) elem;
                HitBox hb = c.getHitBox();
                if (this.player.getHitBox().collide(hb)) {
                    this.player.updateCoinCount();
                    this.player.setHitCoin(true);
                    tempVec.add(elem);
                }
            } else if (elem instanceof Barrier) {
                Barrier b = (Barrier) elem;
                HitBox hb = b.getHitBox();
                if (this.player.getHitBox().collide(hb)) {
                    this.player.decrementCoinCount();
                    this.player.decrementCoinCount();
                    this.player.setHitBarrier(true);
                    tempVec.add(elem);
                    this.speedUpCounter = 0;
                    this.additionalMotionY = DEFAULT_GRAVITY_MOTION.getY() -
                            (DEFAULT_GRAVITY_MOTION.getY() / 4);
                    this.gravity = DEFAULT_GRAVITY_MOTION;
                }
            }
        }
        this.elements.removeAll(tempVec);
    }


    public void removeExtraElements() {
        Vector<Elements> tempVec = new Vector<Elements>();
        for (Elements elem : this.elements) {
            if (elem instanceof Coin) {
                Coin c = (Coin) elem;
                HitBox hb = c.getHitBox();
                if (hb.getBottom() < 0) { // Out of the top of the screen
                    // this.elements.remove(elem);
                    // this.elements.remove(elements.indexOf(elem));
                    tempVec.add(elem);
                }
            }
            if (elem instanceof Barrier) {
                Barrier b = (Barrier) elem;
                HitBox hb = b.getHitBox();
                if (hb.getBottom() < 0) {
                    // this.elements.remove(elem);
                    // this.elements.remove(elements.indexOf(elem));
                    tempVec.add(elem);
                }
            }
        }
        this.elements.removeAll(tempVec);
    }

    public void stop() {
        this.speedDownCounter += 1;
        if (this.gravity.getY() > 0 && this.speedDownCounter % 5 == 0) {
            this.gravity.setY(this.gravity.getY() - 2.5f);
        }
        if (this.gravity.getY() < 0) {
            this.gravity.setY(0f);
        }
        finishingLine.moveUp(this.gravity);
        background.moveUp(this.gravity);
        for (Elements e : elements) {
            e.moveUp(this.gravity);
        }
        castChanges();
    }

    public void moveCharLeft(float f) {
        //Log.i(TAG,"CHAR HAS MOVED LEFT.");
        player.moveLeft(f);
        System.out.println(player.getPosition());
    }

    public void moveCharRight(float f) {
        //Log.i(TAG,"CHAR HAS MOVED RIGHT.");
        player.moveRight(f);
        System.out.println(player.getPosition());
    }

    public void waitingForOthers() {
        castChanges();
    }

    public Position getPosition () { return player.getPosition(); }


    public interface Callback {
        void universeChanged ( Universe u ) ;
    }

    public void setCallBack(Callback c) {
        callback = c;
    }

    public void addCallBack (Callback c ) {
        this.callback = c;
    }

    protected void castChanges() {
        if (callback != null) {
            callback.universeChanged(this);
        }
    }

    private Callback callback = null;


}
