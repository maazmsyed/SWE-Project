package com.swe.justslidin.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.swe.justslidin.constants.Constants;

/**
 * The FinishingLine class lets us create the finishing line object
 * in the game and define its position, hit box, and bitmap.
 */
public class FinishingLine {

    private Bitmap finishingBitmap;
    private HitBox hitBox;
    private Position pos;
    private static final Constants constants = new Constants();

    /**
     * This is the constructor of this class. It instantiates the position and hit box
     * of the finishing line.
     *
     * @param x
     * The x-coordinate of the position of the finishing line.
     * @param y
     * The y-coordinate of the position of the finishing line.
     */
    public FinishingLine(float x, float y) {
        this.pos = new Position(x, y);
        this.hitBox = new HitBox(0, constants.SCREEN_WIDTH,
                y + (constants.SCREEN_HEIGHT * 0.05f), y - (constants.SCREEN_HEIGHT * 0.05f));
    }

    /**
     * This method helps us set a bitmap to the finishing line.
     *
     * @param finishingBitmap
     * The bitmap that is to be assigned to the finishing line object.
     */
    public void setFinishingBitmap(Bitmap finishingBitmap) {
        this.finishingBitmap = finishingBitmap;
    }

    /**
     * This method simply gets and returns the hit box of the finishing line.
     * @return
     * Hit box of the finishing line.
     */
    public HitBox getHitBox() {
        return this.hitBox;
    }

    /**
     * This method helps us draw the finishing line on the canvas by using its bitmap
     * and the hitbox we had defined inthe constructor.
     *
     * @param canvas
     * The canvas on which to draw the bitmap.
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.finishingBitmap, hitBox.getLeft(), hitBox.getTop(), null);
    }

    /**
     * This method helps us move the finishing line up on the screen. It updates
     * the position of the finishing line and also updates its hit box simultaneously.
     *
     * @param m
     * The Motion at which we want to move the finishing line up by (gravity).
     */
    public void moveUp(Motion m) {
        float x = m.getX();
        float y = m.getY();
        this.pos.add(m);
        this.hitBox.updateLeft(x); // Won't really use
        this.hitBox.updateRight(x); // Won't really use
        this.hitBox.updateTop(y);
        this.hitBox.updateBottom(y);
    }

}
