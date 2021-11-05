package com.swe.justslidin.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.swe.justslidin.constants.Constants;

public class FinishingLine {

    private Bitmap finishingBitmap;
    private HitBox hitBox;
    private Position pos;
    private static final Constants constants = new Constants();

    public FinishingLine(float x, float y) {
        this.pos = new Position(x, y);
        this.hitBox = new HitBox(0, constants.SCREEN_WIDTH,
                y + (constants.SCREEN_HEIGHT * 0.05f), y - (constants.SCREEN_HEIGHT * 0.05f));
    }

    public void setFinishingBitmap(Bitmap finishingBitmap) {
        this.finishingBitmap = finishingBitmap;
    }

    public HitBox getHitBox() {
        return this.hitBox;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.finishingBitmap, hitBox.getLeft(), hitBox.getTop(), null);
    }

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
