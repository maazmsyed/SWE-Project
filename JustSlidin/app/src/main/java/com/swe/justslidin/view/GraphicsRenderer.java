package com.swe.justslidin.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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


public class GraphicsRenderer implements SurfaceHolder.Callback, Universe.Callback {

    private static final String TAG = "GraphicsRenderer";
    private final Universe universe;
    private SurfaceHolder holder;

    private Bitmap coinBitmap;
    private Bitmap scaledCoin;
    private Bitmap coinIconBitmap;

    private Bitmap shortBarrierBitmap;
    private Bitmap longBarrierBitmap;
    private Bitmap scaledLongBarrier;
    private Bitmap scaledShortBarrier;

    private Bitmap bgBitmap;
    private Bitmap scaledBG;
    private Bitmap characterBitmap;
    private Resources context;
    private int coinHitCount = 0;
//    private Background background;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public GraphicsRenderer(Universe u, Resources context) {
        this.universe = u;
        this.universe.setCallBack(this);
        this.context = context;

        this.coinBitmap = BitmapFactory.decodeResource(context, R.mipmap.coin);
        this.scaledCoin = Bitmap.createScaledBitmap(this.coinBitmap,
                (int) Constants.COIN_RADIUS * 2,
                (int) Constants.COIN_RADIUS * 2, true);

        this.coinIconBitmap = Bitmap.createScaledBitmap(this.coinBitmap,
                (int) Constants.COIN_RADIUS * 2,
                (int) Constants.COIN_RADIUS * 2, true);

        this.longBarrierBitmap = BitmapFactory.decodeResource(context, R.mipmap.long_barrier);
        this.shortBarrierBitmap = BitmapFactory.decodeResource(context, R.mipmap.short_barrier);

        this.scaledShortBarrier = Bitmap.createScaledBitmap(this.shortBarrierBitmap,
                (int) Constants.BARRIER_SHORT_SIZE, (int) Constants.BARRIER_HEIGHT, true);
        this.scaledLongBarrier = Bitmap.createScaledBitmap(this.longBarrierBitmap,
                (int) Constants.BARRIER_LONG_SIZE, (int) Constants.BARRIER_HEIGHT, true);


        this.characterBitmap = BitmapFactory.decodeResource(context,R.mipmap.player);

        this.bgBitmap = BitmapFactory.decodeResource(context, R.mipmap.background);
        this.scaledBG = Bitmap.createScaledBitmap(this.bgBitmap, screenWidth, screenHeight, true);
        this.universe.setBackgroundBitmap(scaledBG);
//        this.background = new Background(scaledBG);
    }


    public void drawSurfaceView () {
        if (universe != null && holder != null) {
            Canvas canvas = holder.lockCanvas();
            this.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }


    private void draw(Canvas canvas) {

        canvas.drawARGB(255, 255, 255, 255);
        Paint ballPaint = new Paint();
        ballPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ballPaint.setStrokeWidth(10);
        ballPaint.setTextSize(50);
        ballPaint.setARGB(135, 200, 100, 0);

        this.universe.getBackground().draw(canvas);

        Character character = this.universe.getPlayer();
        HitBox hbCharacter = character.getHitBox();
        float radius = character.getRadius();
        if (this.coinHitCount == 20) {
            this.coinHitCount = 0;
            this.universe.getPlayer().setHitCoin(false);
        }
        if (this.universe.getPlayer().ifHitCoin()){
            Bitmap scaledCharacter = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context,R.mipmap.happyplayer),
                    (int) radius*2,
                    (int) radius*2,true);
            canvas.drawBitmap(scaledCharacter,(int) hbCharacter.getLeft(),(int) hbCharacter.getTop(),null);
            this.coinHitCount += 1;
//            this.universe.getPlayer().setHitCoin(false);
        }else{
            Bitmap scaledCharacter = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context,R.mipmap.player),
                    (int) radius*2,
                    (int) radius*2,true);
            canvas.drawBitmap(scaledCharacter,(int) hbCharacter.getLeft(),(int) hbCharacter.getTop(),null);
        }


        for (Elements elem : universe.getElements()) {

            if (elem instanceof Coin) {
                Coin c = (Coin)elem;
                float r = c.getRad();
                HitBox hb = c.getHitBox();
//                Bitmap scaledCoin = Bitmap.createScaledBitmap(this.coinBitmap,
//                        (int)r*2, (int)r*2, true);
                canvas.drawBitmap(this.scaledCoin, hb.getLeft(), hb.getTop(), null);

            } else if (elem instanceof Barrier) {
                Barrier b = (Barrier)elem;
                HitBox hb = b.getHitBox();
                if (b.isShort()) {
//                    Bitmap scaledShortBarrier = Bitmap.createScaledBitmap(this.shortBarrierBitmap,
//                            (int)b.getLength(), (int)b.getHeight(), true);
                    canvas.drawBitmap(this.scaledShortBarrier, hb.getLeft(), hb.getTop(), null);
                } else {
//                    Bitmap scaledLongBarrier = Bitmap.createScaledBitmap(this.longBarrierBitmap,
//                            (int)b.getLength(), (int)b.getHeight(), true);
                    canvas.drawBitmap(this.scaledLongBarrier, hb.getLeft(), hb.getTop(), null);
                }
//                canvas.drawRect(hb.getLeft(), hb.getTop(), hb.getRight(), hb.getBottom(), ballPaint);
            }
        }
        // canvas.drawText("Total Count " + this.universe.getPlayer().getCoinCount(), 50f, 50f,ballPaint);
        canvas.drawBitmap(this.coinIconBitmap, 50f, 10f, null);
        canvas.drawText(": " + this.universe.getPlayer().getCoinCount(), 150f, 75f, ballPaint);

        // TODO: Implement for the other player
        // canvas.drawBitmap(this.coinIconBitmap, 50f, 100f, null);
        // canvas.drawText(": " + this.universe.getPlayer().getCoinCount(), 150f, 165f, ballPaint);

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
