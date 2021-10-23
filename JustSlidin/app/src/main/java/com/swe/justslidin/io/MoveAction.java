package com.swe.justslidin.io;

import android.util.DisplayMetrics;
import android.util.Log;

import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;

public class MoveAction implements ClickAction {
    private static final String TAG = "MoveAction";
    private final Universe universe;

    public MoveAction(Universe universe) {
        this.universe = universe;
    }

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveAction executed");
        //universe.addChar(pos);
        // TODO: get screen width
        if (pos.getX()<=540){
            Log.i(TAG,"ON THE LEFT");
            universe.moveCharLeft(10f);
        }
        else{
            Log.i(TAG,"ON THE RIGHT");
            universe.moveCharRight(10f);
        }

    }
}