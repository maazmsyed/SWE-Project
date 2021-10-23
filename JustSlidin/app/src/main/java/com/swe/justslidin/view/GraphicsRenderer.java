package com.swe.justslidin.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.swe.justslidin.R;
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
    private Bitmap barrierBitmap;

    public GraphicsRenderer(Universe u, Resources context) {
        this.universe = u;
        this.universe.setCallBack(this);
        this.coinBitmap = BitmapFactory.decodeResource(context, R.mipmap.coin);
    }

    public void drawSurfaceView () {    // TODO: Skipped. Do Later.
        if (universe != null && holder != null) {
            Canvas canvas = holder.lockCanvas();
            this.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    private void draw(Canvas canvas, Rect bounds) {
        canvas.drawARGB(255, 255, 255, 255);
        Paint ballPaint = new Paint();
        ballPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ballPaint.setStrokeWidth(10);
        ballPaint.setARGB(135, 0, 0, 0);
        for (Elements elem : universe.getElements()) {
            if (elem instanceof Coin) {
                Bitmap scaled_bmp = Bitmap.createScaledBitmap(this.coinBitmap,
                        bounds.width(), bounds.height(), true);
                canvas.drawBitmap(scaled_bmp, bounds.left, bounds.bottom, ballPaint);
//                Coin c = (Coin)elem;
//                Position p = c.getPosition();
//                canvas.drawCircle(p.getX(), p.getY(), c.getRad(), ballPaint);
            } else if (elem instanceof Barrier) {
                Barrier b = (Barrier)elem;
                Position p = b.getPosition();
                HitBox hb = b.getHitBox();
                canvas.drawRect(hb.getLeft(), hb.getTop(), hb.getRight(), hb.getBottom(), ballPaint);
            }
        }
//        for (Character b : universe.getChar()) { //TODO: FIX THIS
//            Position p = b.getPosition();
//            Position r = new Position(b.getRadius());
//            canvas.drawCircle(p.getX(), p.getY(), b.getRadius(), ballPaint);
//        }
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
