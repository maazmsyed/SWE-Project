package ync.ysc3232.week7.io;

import android.util.Log;

import ync.ysc3232.week7.models.Position;
import ync.ysc3232.week7.models.Universe;

public class AddAction implements ClickAction {
    private static final String TAG = "AddAction";
    private final Universe universe;

    public AddAction(Universe universe) {
        this.universe = universe;
    }

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "AddAction executed");
        universe.addBall(pos);
    }
}