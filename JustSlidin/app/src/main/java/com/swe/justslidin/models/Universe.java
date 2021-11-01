package com.swe.justslidin.models;


import android.graphics.Bitmap;

import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.view.GraphicsRenderer;
import android.util.Log;


import java.util.List;
import java.util.Vector;

public class Universe {

    private static final String TAG = "Universe";
    // TODO: Do we still need gravity?
    final static Motion DEFAULT_GRAVITY_MOTION = new Motion(0,10f); // Added after referring prof
    Motion gravity;
    Character player;
    List<Elements> elements;
    Background background = new Background();
    private float additionalMotionY;
    private int speedUpCounter;

    public Universe () {
        this (DEFAULT_GRAVITY_MOTION, new Character(Constants.SCREEN_WIDTH/2, 400, 50f));
    }

    public Universe (Motion g, Character pl) {
        elements = new Vector<>();
        gravity = g;
        player = pl;
        additionalMotionY = 10f;
        speedUpCounter = 0;
//        this.background = new Background();
    }

    public void incrementGravity() {
        // this.gravity.setY(DEFAULT_GRAVITY_MOTION.getY() + additionalMotionY);
        this.gravity = new Motion(0, DEFAULT_GRAVITY_MOTION.getY() + additionalMotionY); // Also not working
        System.out.println((gravity.getY())); // TODO: This is not updating!
    }

    public void defaultGravity() {
        this.gravity = DEFAULT_GRAVITY_MOTION;
    }

    public void setBackgroundBitmap(Bitmap bitmap) {
        this.background.setBackgroundBitmap(bitmap);
    }

    public void setPlayerBitmap(Bitmap bitmap){ this.player.setPlayerBitmap(bitmap);}

    public Background getBackground() {
        return this.background;
    }

//    public void addChar(float x, float y, float rad) {
//        data.add(new Character (x,y,rad));
//        castChanges();
//    }

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
        Coin newCoin = new Coin(x, y, rad);
        for (Elements elem : this.elements) {
            if (elem instanceof Barrier) {
                Barrier b = (Barrier) elem;
                HitBox hb = b.getHitBox();
                if (newCoin.getHitBox().collide(hb)) {
                    float bLeft = hb.getLeft();
                    float bRight = hb.getRight();
                    if (bLeft <= Constants.SCREEN_WIDTH) {
                        newCoin = new Coin(bRight + 100, y, rad);
                        // newCoin.setPos(new Position(bRight + 100, y));
                    } else {
                        // newCoin.setPos(new Position(bLeft - 100, y));
                        newCoin = new Coin(bLeft - 100, y, rad);
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
        this.speedUpCounter += 1;
        if (this.additionalMotionY <= 20f && this.speedUpCounter % 20 == 0) {
            this.additionalMotionY += 0.75;
            // this.incrementGravity();
//            this.gravity.setY(DEFAULT_GRAVITY_MOTION.getY() + additionalMotionY);
            this.gravity.setY(additionalMotionY);
        }
        background.moveUp(this.gravity);
        for (Elements e : elements) {
            e.moveUp(this.gravity);
        }
        this.player.updateAbsPosY(this.gravity);
        castChanges();
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
                    // this.elements.remove(elem);
                    tempVec.add(elem);
                }
            } else if (elem instanceof Barrier) {
                Barrier b = (Barrier) elem;
                HitBox hb = b.getHitBox();
                if (this.player.getHitBox().collide(hb)) {
                    this.player.decrementCoinCount();
                    this.player.decrementCoinCount();
                    this.player.setHitBarrier(true);
//                    this.player.setHitCoin(false);

                    this.speedUpCounter = 0;
                    this.additionalMotionY = 7f;
                    // this.elements.remove(elem);
                    tempVec.add(elem);
//                    this.elements.remove(elements.indexOf(elem));
                    this.gravity = DEFAULT_GRAVITY_MOTION;
//                    this.additionalMotionY = 0;
                }
            }
        }
        this.elements.removeAll(tempVec);
    }

//    public void CheckPlayerCoinCollision(){
//        for (Elements elem: elements){
//            if (elem instanceof Coin){
//                Coin c = (Coin) elem;
//                HitBox hb = c.getHitBox();
//                if (this.player.getHitBox().collide(hb)){
//                    this.player.setHitCoin(true);
//                }
//            }
//        }
//    }

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
