package com.swe.justslidin.constants;

import android.content.res.Resources;
import com.swe.justslidin.models.Motion;


public class Constants {

    public static float SCREEN_WIDTH;
    public static float SCREEN_HEIGHT;

    // Coin
    public static float COIN_RADIUS;

    // Barrier
    public static float BARRIER_SHORT_SIZE;
    public static float BARRIER_LONG_SIZE;
    public static float BARRIER_HEIGHT;

    // Player
    public static float PLAYER_RADIUS;

    public Constants(){

        try {
            SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
            SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        } catch (Exception e) {
            SCREEN_HEIGHT = 1920f;
            SCREEN_WIDTH = 1080f;
        }

        // Coin
        COIN_RADIUS = SCREEN_HEIGHT / 40f;

        // Barrier
        BARRIER_SHORT_SIZE = SCREEN_WIDTH / 5f;
        BARRIER_LONG_SIZE = (SCREEN_WIDTH / 3f);
        BARRIER_HEIGHT = SCREEN_HEIGHT / 18f;

        // Player
        PLAYER_RADIUS = SCREEN_WIDTH / 20f;

    }




}
