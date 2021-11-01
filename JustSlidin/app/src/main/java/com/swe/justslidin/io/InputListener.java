package com.swe.justslidin.io;

import android.view.MotionEvent;
import android.view.View;

import com.swe.justslidin.models.Position;

/**
 * Handles touch input from the user.
 *
 *
 *
 *
 */

public class InputListener implements View.OnTouchListener {
    private Position
            up_pos;
    private Callback callback;
//    private Boolean holding;

    public void setCallback(InputHandler cb) {
        this.callback = cb;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
//                int temp = 0;
//                this.holding = true;
//                while (this.holding) {
//                    if (temp % 5 == 0) {
//                        // Keep on moving player
//                        up_pos = new Position(motionEvent.getRawX(), motionEvent.getRawY());
//                        callback.onClick(up_pos);
//                    }
//                    temp += 1;
//                }
                break;
            case MotionEvent.ACTION_UP:
//                this.holding = false;
                up_pos = new Position(motionEvent.getRawX(), motionEvent.getRawY());
                callback.onClick(up_pos);
                break;
        }
        return true;
    }
    public interface Callback {
        void onClick ( Position pos ) ;
    }
}