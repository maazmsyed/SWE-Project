package com.swe.justslidin.io;

import android.util.DisplayMetrics;
import android.util.Log;

import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;

public class MoveAction implements ClickAction {
    private static final String TAG = "MoveAction";
    private final Universe universe;
    private static final Constants constants = new Constants();
    private final static float screenWidth = constants.SCREEN_WIDTH;
    private final static float moveLeftValue = screenWidth / 40;

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
            this.universe.getPlayer().moveLeft(constants.COIN_RADIUS / 2);
        }
        if (pos.getX() > screenWidth / 2 && this.universe.isGameRunning()) {
            Log.i(TAG,"ON THE RIGHT");
            this.universe.getPlayer().moveRight(constants.COIN_RADIUS / 2);
        }

    }
}