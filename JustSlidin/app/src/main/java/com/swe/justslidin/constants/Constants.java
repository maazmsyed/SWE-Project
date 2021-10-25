package com.swe.justslidin.constants;

import android.content.res.Resources;
import com.swe.justslidin.models.Motion;


public abstract class Constants {

    public static final Motion DEFAULT_GRAVITY_MOTION = new Motion(0,5f);
    public static final int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final float COIN_RADIUS = SCREEN_HEIGHT / 50;
    public static final float BARRIER_SHORT_SIZE = SCREEN_WIDTH / 5;
    public static final float BARRIER_LONG_SIZE = (float) (SCREEN_WIDTH / 2.5);



}
