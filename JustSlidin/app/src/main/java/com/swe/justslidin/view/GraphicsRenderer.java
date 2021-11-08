package com.swe.justslidin.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.swe.justslidin.R;
import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Background;
import com.swe.justslidin.models.Barrier;
import com.swe.justslidin.models.Character;
import com.swe.justslidin.models.Coin;
import com.swe.justslidin.models.Elements;
import com.swe.justslidin.models.HitBox;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;
import com.swe.justslidin.network.PlayerStats;

import java.util.Vector;


public class GraphicsRenderer implements SurfaceHolder.Callback, Universe.Callback {

    private static final String TAG = "GraphicsRenderer";
    private final Universe universe;
    private SurfaceHolder holder;
    private static final Constants constants = new Constants();

    // Coin
    private Bitmap coinBitmap; // For coins
    private Bitmap coinIconBitmap; // For coin counter
    private int coinBitmapCount;

    // Barrier
    private Bitmap shortBarrierBitmap;
    private Bitmap longBarrierBitmap;
    private int barrierBitmapCount;


    // Player
    private Bitmap playerWalkingOne;
    private Bitmap playerWalkingTwo;
    private Bitmap playerWalkingThree;
    private Bitmap playerWalkingFour;
    private Bitmap playerWalkingFive;
    private Bitmap playerWinningOne;
    private Bitmap playerWinningTwo;
    private Bitmap playerWinningThree;
    private Bitmap playerCoin;
    private Bitmap playerBarrier;
    private Bitmap playerWin;
    private Vector<Bitmap> playerWalking;
    private int playerWalkingCount;
    private Vector<Bitmap> playerWinning;
    private int playerWinningCount;

    // Other Player
    private Vector<Bitmap> otherPlayerWalking;
    private int otherPlayerWalkingCount;

    // Background
    private Bitmap bgBitmap;

    // Context
    private Resources context;

    // Finishing Line
    private Bitmap finishingLine;

    private int screenWidth;
    private int screenHeight;

//    private Bitmap scaledBG;
//    private Bitmap characterBitmap;
//    private int coinHitCount = 0;
//    private Background background;


    /**
     * This is the constructor for the graphic renderer. This is where we create all of the bitmaps
     * that we plan to render in the future.
     * @param u
     * This is just universe of the player.
     * @param context
     * This is the resources that we got from the AppCompactActivity class.
     *
     */
    public GraphicsRenderer(Universe u, Resources context) {
        Log.i(TAG, "Is it entering GraphicsRenderer or not");
        this.universe = u;
        this.universe.setCallBack(this);
        this.context = context;
        this.screenWidth = (int) constants.SCREEN_WIDTH;
        this.screenHeight = (int) constants.SCREEN_HEIGHT;
//        this.universe.setOtherPlayerScreenDim();

        Log.i(TAG, "Is it processing setOtherPlayerScreenDim?");

        // Coin Bitmap
        this.coinBitmap = BitmapFactory.decodeResource(context, R.mipmap.coin);
        this.coinBitmap = Bitmap.createScaledBitmap(this.coinBitmap,
                (int) constants.COIN_RADIUS * 2,
                (int) constants.COIN_RADIUS * 2, true);

        // Coin Icon Bitmap
        this.coinIconBitmap = BitmapFactory.decodeResource(context, R.mipmap.coin);
        this.coinIconBitmap = Bitmap.createScaledBitmap(this.coinBitmap,
                (int) (constants.COIN_RADIUS * 1.5f),
                (int) (constants.COIN_RADIUS * 1.5f), true);

        // Long Barrier Bitmap
        this.longBarrierBitmap = BitmapFactory.decodeResource(context, R.mipmap.long_barrier);
        this.longBarrierBitmap = Bitmap.createScaledBitmap(this.longBarrierBitmap,
                (int) constants.BARRIER_LONG_SIZE, (int) constants.BARRIER_HEIGHT, true);

        // Short Barrier Bitmap
        this.shortBarrierBitmap = BitmapFactory.decodeResource(context, R.mipmap.short_barrier);
        this.shortBarrierBitmap = Bitmap.createScaledBitmap(this.shortBarrierBitmap,
                (int) constants.BARRIER_SHORT_SIZE, (int) constants.BARRIER_HEIGHT, true);

        // Player & Coin Counter / Player & Barrier Counter / Player Walking & Winning Counters
        this.barrierBitmapCount = 0;
        this.coinBitmapCount = 0;
        this.playerWalkingCount = 0;
        this.playerWinningCount = 0;
        this.otherPlayerWalkingCount = 0;

        // Player Walking Bitmaps
        this.playerWalkingTwo = BitmapFactory.decodeResource(context,R.mipmap.player_walking_2);
        this.playerWalkingTwo = Bitmap.createScaledBitmap(this.playerWalkingTwo,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);
        this.playerWalkingThree = BitmapFactory.decodeResource(context,R.mipmap.player_walking_3);
        this.playerWalkingThree = Bitmap.createScaledBitmap(this.playerWalkingThree,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);
        this.playerWalkingFour = BitmapFactory.decodeResource(context,R.mipmap.player_walking_4);
        this.playerWalkingFour = Bitmap.createScaledBitmap(this.playerWalkingFour,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);
        this.playerWalkingFive = BitmapFactory.decodeResource(context,R.mipmap.player_walking_5);
        this.playerWalkingFive = Bitmap.createScaledBitmap(this.playerWalkingFive,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);

        // Setting up playerWalking Vector and otherPlayerWalking Vector
        this.playerWalking = new Vector<>();
        this.otherPlayerWalking = new Vector<>();
        for (int i = 0; i < 5; i++) {
            playerWalking.add(this.playerWalkingTwo);
            otherPlayerWalking.add(this.playerWalkingTwo);
        }
        for (int i = 0; i < 5; i++) {
            playerWalking.add(this.playerWalkingThree);
            otherPlayerWalking.add(this.playerWalkingThree);
        }
        for (int i = 0; i < 5; i++) {
            playerWalking.add(this.playerWalkingFour);
            otherPlayerWalking.add(this.playerWalkingFour);
        }
        for (int i = 0; i < 5; i++) {
            playerWalking.add(this.playerWalkingFive);
            otherPlayerWalking.add(this.playerWalkingFive);
        }

        // Player Winning Bitmaps
        this.playerWinningOne = BitmapFactory.decodeResource(context,R.mipmap.player_win_1);
        this.playerWinningOne = Bitmap.createScaledBitmap(this.playerWinningOne,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);
        this.playerWinningTwo = BitmapFactory.decodeResource(context,R.mipmap.player_win_2);
        this.playerWinningTwo = Bitmap.createScaledBitmap(this.playerWinningTwo,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);
        this.playerWinningThree = BitmapFactory.decodeResource(context,R.mipmap.player_win_3);
        this.playerWinningThree = Bitmap.createScaledBitmap(this.playerWinningThree,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);

        // Setting up playerWinning Vector
        this.playerWinning = new Vector<>();
        for (int i = 0; i < 5; i++) { playerWinning.add(this.playerWinningOne); }
        for (int i = 0; i < 5; i++) { playerWinning.add(this.playerWinningTwo); }
        for (int i = 0; i < 5; i++) { playerWinning.add(this.playerWinningThree); }

        // Player Coin Interaction and Player Barrier Interaction Bitmap
        this.playerCoin = BitmapFactory.decodeResource(context,R.mipmap.player_coin);
        this.playerCoin = Bitmap.createScaledBitmap(this.playerCoin,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);
        this.playerBarrier = BitmapFactory.decodeResource(context,R.mipmap.player_barrier);
        this.playerBarrier = Bitmap.createScaledBitmap(this.playerBarrier,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);

        // Player Win
        this.playerWin = BitmapFactory.decodeResource(context,R.mipmap.player_win);
        this.playerWin = Bitmap.createScaledBitmap(this.playerWin,
                (int) constants.PLAYER_RADIUS * 2,
                (int) constants.PLAYER_RADIUS * 2,true);


        // Background Bitmap
        this.bgBitmap = BitmapFactory.decodeResource(context, R.mipmap.background);
        this.bgBitmap = Bitmap.createScaledBitmap(this.bgBitmap, screenWidth, screenHeight, true);
        this.universe.setBackgroundBitmap(this.bgBitmap);

        // FinishingLine Bitmap
        System.out.println(this.universe.getFinishingLine().getHitBox().getLeft());
        System.out.println(this.universe.getFinishingLine().getHitBox().getTop());
        this.finishingLine = BitmapFactory.decodeResource(context, R.mipmap.finishing_line);
        this.finishingLine = Bitmap.createScaledBitmap(this.finishingLine,
                screenWidth, (int) (this.universe.getFinishingLine().getHitBox().getBottom()
                        - this.universe.getFinishingLine().getHitBox().getTop()), true);
        this.universe.setFinishingLineBitmap(this.finishingLine);

    }


    /**
     * This method draws the surface view (canvas).
     */
    public void drawSurfaceView () {
        if (universe != null && holder != null) {
            Canvas canvas = holder.lockCanvas();
            this.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }


    /**
     * This method draws all the game objects on the canvas depending on the state of the game.
     * We have also added the animations in this method. The method also draws the opponent's
     * bitmap depending on the opponent's position.
     * @param canvas
     * The canvas on which to draw on.
     */
    private void draw(Canvas canvas) {
        if (canvas != null) { // TODO: Double check this (needed or not?)
            Log.i(TAG, "Is it going to draw?");
            // canvas.drawARGB(150, 100, 100, 100);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(10);
            paint.setTextSize(50);
            paint.setARGB(150, 200, 100, 0);

            this.universe.getBackground().draw(canvas);

            this.universe.getFinishingLine().draw(canvas);

            Character player = this.universe.getPlayer();
            HitBox hbPlayer = player.getHitBox();


            // Reset player's coin reaction bitmap to normal
            if (this.coinBitmapCount >= 20) {
                this.coinBitmapCount = 0;
                this.universe.getPlayer().setHitCoin(false);
            }

            // Reset player's barrier hit reaction bitmap to normal
            if (this.barrierBitmapCount >= 20) {
                this.barrierBitmapCount = 0;
                this.universe.getPlayer().setHitBarrier(false);
            }

            if (this.universe.isGameRunning() || this.universe.getGravity().getY() > 0) {

                if (this.universe.getPlayer().ifHitCoin() && (!this.universe.getPlayer().ifHitBarrier())) {
                    canvas.drawBitmap(this.playerCoin, (int) hbPlayer.getLeft(),
                            (int) hbPlayer.getTop(), null);
                    this.coinBitmapCount += 1;
                }

                if ((!this.universe.getPlayer().ifHitCoin()) && this.universe.getPlayer().ifHitBarrier()) {
                    canvas.drawBitmap(this.playerBarrier, (int) hbPlayer.getLeft(),
                            (int) hbPlayer.getTop(), null);
                    this.barrierBitmapCount += 1;
                }

                if (this.universe.getPlayer().ifHitCoin() && this.universe.getPlayer().ifHitBarrier()) {
                    if (this.coinBitmapCount < this.barrierBitmapCount) {
                        canvas.drawBitmap(this.playerCoin, (int) hbPlayer.getLeft(),
                                (int) hbPlayer.getTop(), null);
                    } else {
                        canvas.drawBitmap(this.playerBarrier, (int) hbPlayer.getLeft(),
                                (int) hbPlayer.getTop(), null);
                    }
                    this.coinBitmapCount += 1;
                    this.barrierBitmapCount += 1;
                }

                if ((!this.universe.getPlayer().ifHitCoin()) && (!this.universe.getPlayer().ifHitBarrier())) {
                    canvas.drawBitmap(this.playerWalking.get(this.playerWalkingCount),
                            (int) hbPlayer.getLeft(), (int) hbPlayer.getTop(), null);
                    this.playerWalkingCount += 1;
                    if (this.playerWalkingCount >= 20) {
                        this.playerWalkingCount = 0;
                    }
                }

            } else if (!this.universe.isGameRunning()) {
                canvas.drawBitmap(this.playerWinning.get(this.playerWinningCount),
                        (int) hbPlayer.getLeft(), (int) hbPlayer.getTop(), null);
                this.playerWinningCount += 1;
                if (this.playerWinningCount >= 15) {
                    this.playerWinningCount = 0;
                }
            }

            Log.i(TAG, "But is it rendering its own?");
            if (this.universe.getOtherPlayerPos() != null) {
                Log.i(TAG, "Is it getting the other player's position?");

                float otherPlayerPosY = (PlayerStats.otherPlayerScreenHeight / 4) + (
                        ((((float) screenHeight) / PlayerStats.otherPlayerScreenHeight) *
                                this.universe.getOtherPlayerPos().getY()) - player.getAbsolutePos().getY());
                Log.i(TAG, "The other player's screen height is" + PlayerStats.otherPlayerScreenHeight);

                float otherPlayerPosX = (this.universe.getOtherPlayerPos().getX() *
                        ((((float) screenWidth) / PlayerStats.otherPlayerScreenWidth)));

//                float otherPlayerPosX = (player.getAbsolutePos().getX() - (
//                        (((float) screenWidth) / PlayerStats.otherPlayerScreenWidth) *
//                                this.universe.getOtherPlayerPos().getX()));
                Log.i(TAG, "The other player's screen width is " + PlayerStats.otherPlayerScreenWidth);
                float hbOtherPlayerTop = otherPlayerPosY - constants.PLAYER_RADIUS;
                float hbOtherPlayerLeft = otherPlayerPosX - constants.PLAYER_RADIUS;

                canvas.drawBitmap(this.playerWalking.get(this.playerWalkingCount),
                        (int) hbOtherPlayerLeft, hbOtherPlayerTop, paint);
                this.otherPlayerWalkingCount += 1;
                if (this.playerWalkingCount >= 20) {
                    this.playerWalkingCount = 0;
                }
            }

            for (Elements elem : this.universe.getElements()) {
                if (elem instanceof Coin) {
                    Coin c = (Coin) elem;
                    HitBox hb = c.getHitBox();
                    canvas.drawBitmap(this.coinBitmap, hb.getLeft(), hb.getTop(), null);
                } else if (elem instanceof Barrier) {
                    Barrier b = (Barrier) elem;
                    HitBox hb = b.getHitBox();
                    if (b.isShort()) {
                        canvas.drawBitmap(this.shortBarrierBitmap, hb.getLeft(), hb.getTop(), null);
                    } else {
                        canvas.drawBitmap(this.longBarrierBitmap, hb.getLeft(), hb.getTop(), null);
                    }
                }
            }

            canvas.drawBitmap(this.coinIconBitmap, constants.COIN_RADIUS,
                    constants.COIN_RADIUS / 2, paint);
            canvas.drawText("" + this.universe.getPlayer().getCoinCount(), 150f, 75f, paint);

//         TODO: Implement for the other player(s)
//         canvas.drawBitmap(this.coinIconBitmap, 50f, 100f, null);
//         canvas.drawText(": " + this.universe.getPlayer().getCoinCount(), 150f, 165f, ballPaint);


//        if (this.universe.getPlayer().ifHitCoin()){
//            Bitmap scaledCharacter = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context,R.mipmap.happyplayer),
//                    (int) radius*2,
//                    (int) radius*2,true);
//            canvas.drawBitmap(scaledCharacter,(int) hbCharacter.getLeft(),(int) hbCharacter.getTop(),null);
//            this.coinHitCount += 1;
//            this.universe.getPlayer().setHitCoin(false);
//        }else{
//            Bitmap scaledCharacter = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context,R.mipmap.player),
//                    (int) radius*2,
//                    (int) radius*2,true);
//            canvas.drawBitmap(scaledCharacter,(int) hbCharacter.getLeft(),(int) hbCharacter.getTop(),null);
//        }


//        for (Elements elem : universe.getElements()) {
//
//            if (elem instanceof Coin) {
//                Coin c = (Coin)elem;
//                float r = c.getRad();
//                HitBox hb = c.getHitBox();
////                Bitmap scaledCoin = Bitmap.createScaledBitmap(this.coinBitmap,
////                        (int)r*2, (int)r*2, true);
//                canvas.drawBitmap(this.scaledCoin, hb.getLeft(), hb.getTop(), null);
//
//            } else if (elem instanceof Barrier) {
//                Barrier b = (Barrier)elem;
//                HitBox hb = b.getHitBox();
//                if (b.isShort()) {
////                    Bitmap scaledShortBarrier = Bitmap.createScaledBitmap(this.shortBarrierBitmap,
////                            (int)b.getLength(), (int)b.getHeight(), true);
//                    canvas.drawBitmap(this.scaledShortBarrier, hb.getLeft(), hb.getTop(), null);
//                } else {
////                    Bitmap scaledLongBarrier = Bitmap.createScaledBitmap(this.longBarrierBitmap,
////                            (int)b.getLength(), (int)b.getHeight(), true);
//                    canvas.drawBitmap(this.scaledLongBarrier, hb.getLeft(), hb.getTop(), null);
//                }
////                canvas.drawRect(hb.getLeft(), hb.getTop(), hb.getRight(), hb.getBottom(), ballPaint);
//            }
//        }
            // canvas.drawText("Total Count " + this.universe.getPlayer().getCoinCount(), 50f, 50f,ballPaint);
//        canvas.drawBitmap(this.coinIconBitmap, 50f, 10f, null);
//        canvas.drawText(": " + this.universe.getPlayer().getCoinCount(), 150f, 75f, ballPaint);

            // TODO: Implement for the other player
            // canvas.drawBitmap(this.coinIconBitmap, 50f, 100f, null);
            // canvas.drawText(": " + this.universe.getPlayer().getCoinCount(), 150f, 165f, ballPaint);
        }
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Log.i(TAG, "SurfaceCreated!");
        this.holder = surfaceHolder;
        this.drawSurfaceView();
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        drawSurfaceView();
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        this.holder = null;
    }

    @Override
    public void universeChanged(Universe u) {
        drawSurfaceView();
    }


}
