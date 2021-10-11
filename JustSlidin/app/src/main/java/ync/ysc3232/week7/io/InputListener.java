package ync.ysc3232.week7.io;

import android.view.MotionEvent;
import android.view.View;

import ync.ysc3232.week7.io.InputHandler;
import ync.ysc3232.week7.models.Position;

public class InputListener implements View.OnTouchListener {
    private Position
            up_pos;
    private Callback callback;
    public void setCallback(InputHandler cb) {
        this.callback = cb;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                up_pos = new Position(motionEvent.getX(), motionEvent.getY());
                callback.onClick(up_pos);
                break;
        }
        return true;
    }
    public interface Callback {
        void onClick ( Position pos ) ;
    }
}