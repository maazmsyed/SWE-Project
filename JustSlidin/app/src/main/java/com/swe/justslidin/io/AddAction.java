package com.swe.justslidin.io;

import android.util.Log;

import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;


public class AddAction implements ClickAction {
    private static final String TAG = "AddAction";
    private final Universe universe;

    public AddAction(Universe universe) {
        this.universe = universe;
    }

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "AddAction executed");
        //universe.addChar(pos); TODO: THIS DOESNT WORK BECAUSE addChar method is non-functional.
    }
}