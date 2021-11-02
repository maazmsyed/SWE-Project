package com.swe.justslidin.io;

import android.util.DisplayMetrics;
import android.util.Log;

import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;

public class MoveAction implements ClickAction {
    private static final String TAG = "MoveAction";
    private final Universe universe;
    private final float screenWidth = Constants.SCREEN_WIDTH;

    public MoveAction(Universe universe) {
        this.universe = universe;
    }

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveAction executed");
        //universe.addChar(pos);
        // TODO: CHECK IF THE IF STATEMENTS ARE RIGHT WITH @MAAZ AND @DANNY
        if (pos.getX() <= screenWidth / 2 && this.universe.isGameRunning()){
            Log.i(TAG,"ON THE LEFT");
            // universe.moveCharLeft(10f);
            this.universe.getPlayer().moveLeft(15);
        }
        if (pos.getX() > screenWidth / 2 && this.universe.isGameRunning()) {
            Log.i(TAG,"ON THE RIGHT");
            // universe.moveCharRight(10f);
            this.universe.getPlayer().moveRight(15);
        }

    }
}