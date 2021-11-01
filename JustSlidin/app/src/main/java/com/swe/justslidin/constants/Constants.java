package com.swe.justslidin.constants;

import android.content.res.Resources;
import com.swe.justslidin.models.Motion;


public abstract class Constants {

    public static final float SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final float SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;

    // Coin
    public static final float COIN_RADIUS = SCREEN_HEIGHT / 40;

    // Barrier
    public static final float BARRIER_SHORT_SIZE = SCREEN_WIDTH / 5;
    public static final float BARRIER_LONG_SIZE = (float) (SCREEN_WIDTH / 2.5);
    public static final float BARRIER_HEIGHT = SCREEN_HEIGHT / 12;



}
