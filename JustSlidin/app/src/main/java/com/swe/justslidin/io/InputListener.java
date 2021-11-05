package com.swe.justslidin.io;

import android.view.MotionEvent;
import android.view.View;

import com.swe.justslidin.models.Position;

/**
 * Handles touch input from the user.
 * Character moves based on users' clicks
 * if the user clicks on the RIGHT side of the screen, character moves LEFT, using moveLeft
 * if the user clicks on the LEFT side of the screen, character moves RIGHT, using moveRight
 * moveLeft and moveRight are defined within the Character model, and called in MoveAction
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

    /**
     * The boolean method onTouch discerns the users' motion events
     * upon an ACTION_DOWN (user places finger on screen), break statement is executed (nothing to do)
     * upon an ACTION_UP (user's finger leaves screen), we get the "up_pos"
     * "up_pos" here is the user's finger's coordinate on the "screen"
     * "up_pos" is a Position object, that is retrieved through calling getRawX() and getRawY()
     * the attribute "callback" then calls the onClick method (see below), with parameter "up_pos"
     * the callback from InputListener is implemented by the InputHandler (see InputHandler.java file)
     * @param view view parameter that onTouch takes, created by default when View.OnTouchListener is implemented
     * @param motionEvent motionEvents of two types (in our case): ACTION_DOWN and ACTION_UP)
     * @return the boolean TRUE is returned in this case
     */

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