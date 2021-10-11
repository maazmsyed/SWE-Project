package ync.ysc3232.week7.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import ync.ysc3232.week7.models.Ball;
import ync.ysc3232.week7.models.Position;
import ync.ysc3232.week7.models.Universe;

public class GraphicsRenderer implements SurfaceHolder.Callback, Universe.Callback {
    private static final String TAG = "GraphicsRenderer";
    private final Universe universe;
    private SurfaceHolder holder;

    public GraphicsRenderer(Universe u) {
        this.universe = u;
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
        ballPaint.setARGB(135, 0, 0, 0);
        for (Ball b : universe.getBalls()) {
            Position p = b.getPosition();
            Position r = new Position(b.getRadius());
            canvas.drawCircle(p.getX(), p.getY(), b.getRadius(), ballPaint);
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
