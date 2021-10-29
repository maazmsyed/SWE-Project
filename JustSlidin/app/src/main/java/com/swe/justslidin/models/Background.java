package com.swe.justslidin.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.swe.justslidin.R;

public class Background {

    private int cut = 0;
    private Bitmap backgroundBitmap;
    private int height = 0;

    public Background() { }

    public void setBackgroundBitmap(Bitmap bitmap) {
        this.backgroundBitmap = bitmap;
        this.height = backgroundBitmap.getHeight();
    }

    public void moveUp(Motion m) {
        float y = m.getY();
        this.cut -= y;
        if (cut <= -height) {
            cut = 0;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.backgroundBitmap, 0, this.cut, null);
        canvas.drawBitmap(this.backgroundBitmap, 0, this.height + this.cut, null);
    }


}
