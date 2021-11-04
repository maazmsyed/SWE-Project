package com.swe.justslidin.constants;

import android.content.res.Resources;
import com.swe.justslidin.models.Motion;


public class Constants {

    public float SCREEN_WIDTH; // = Resources.getSystem().getDisplayMetrics().widthPixels;
    public float SCREEN_HEIGHT; // = Resources.getSystem().getDisplayMetrics().heightPixels;

    // Coin
    public float COIN_RADIUS; // = SCREEN_HEIGHT / 40f;

    // Barrier
    public float BARRIER_SHORT_SIZE; // = SCREEN_WIDTH / 5f;
    public float BARRIER_LONG_SIZE; // = SCREEN_WIDTH / 3f;
    public float BARRIER_HEIGHT; // = SCREEN_HEIGHT / 18f;

    // Player
    public float PLAYER_RADIUS; // = SCREEN_WIDTH / 20f;

    public Constants() {

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
