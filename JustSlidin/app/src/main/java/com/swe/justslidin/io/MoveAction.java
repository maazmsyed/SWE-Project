package com.swe.justslidin.io;

import android.util.DisplayMetrics;
import android.util.Log;

import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;

/**
 * The MoveAction class implements ClickAction (which itself extends Action)
 * Here, the position of the character (within the given Universe) will be updated according to a user's clicking position
 * The .execute() function, given the user's clicking coordinates, then performs the updating of the user's positions in the universe
 */

public class MoveAction implements ClickAction {
    private static final String TAG = "MoveAction";
    private final Universe universe;
    private static final Constants constants = new Constants();
    private final static float screenWidth = constants.SCREEN_WIDTH;
    private final static float moveLeftValue = screenWidth / 40;

    public MoveAction(Universe universe) {
        this.universe = universe;
    }

    /**
     * The .execute function that ClickActions possess
     * @param pos is the Position object parameter that this function takes
     * There are two possible scenarios in this case: one where the clicking position is on the left half of the screen, and the other the right half
     * We have a constant screenWidth that documents the width of the screen. For standard Pixel 2s, it is 540
     * To move the characters, we first get the given universe (this.universe), then the character (.getPlayer), then call the moveRight/moveLeft functions
     * we specify the child nodes "players" -> "pos" (documents position of that specific player)
     */

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