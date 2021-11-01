package com.swe.justslidin.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.swe.justslidin.constants.Constants;

public class FinishingLine {

    private Bitmap finishingBitmap;
    private HitBox hitBox;
    private Position pos;

    public FinishingLine() {
        this.hitBox = new HitBox(0, Constants.SCREEN_WIDTH,
                Constants.SCREEN_HEIGHT * 0.8f, Constants.SCREEN_HEIGHT * 0.7f);
    }

    public void setFinishingBitmap(Bitmap finishingBitmap) {
        this.finishingBitmap = finishingBitmap;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.finishingBitmap, hitBox.getLeft(), hitBox.getTop(), null);
    }

}
