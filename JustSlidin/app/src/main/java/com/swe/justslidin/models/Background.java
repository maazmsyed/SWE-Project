package com.swe.justslidin.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.swe.justslidin.R;
import com.swe.justslidin.constants.Constants;

/**
 * The background class lets us create the background object of the universe, define its
 * bitmap, and initialize variables that would allow for a small-sized background to
 * act as if it is infinitely moving.
 */
public class Background {

    private static final Constants constants = new Constants();
    private int cut = 0;
    private Bitmap backgroundBitmap;
    private int height = 0;
    private static final String TAG = "Background";

    public Background() { }

    /**
     * Sets the bitmap of the background object.
     * @param bitmap
     * The bitmap which must be set for the particular background.
     */
    public void setBackgroundBitmap(Bitmap bitmap) {
        this.backgroundBitmap = bitmap;
        this.height = backgroundBitmap.getHeight();
    }

    /**
     * General function to enable motion in the background.
     * This allows the background to move up and reset to origin
     * if it has moved up enough.
     * @param m
     */
    public void moveUp(Motion m) {
        float y = m.getY();
        this.cut -= y;
        if (cut <= -height) {
            cut = 0;
        }
    }

    /**
     * Allows to draw the bitmap of the background object onto the canvas.
     * @param canvas
     * The canvas on which the bitmap is to be drawn.
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.backgroundBitmap, 0, this.cut, null);
        canvas.drawBitmap(this.backgroundBitmap, 0, this.height + this.cut, null);
    }


}
