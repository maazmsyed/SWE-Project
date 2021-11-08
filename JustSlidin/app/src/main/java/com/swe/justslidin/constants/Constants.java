package com.swe.justslidin.constants;

import android.content.res.Resources;


public class Constants {

    public float SCREEN_WIDTH;
    public float SCREEN_HEIGHT;

    // Coin
    public float COIN_RADIUS;

    // Barrier
    public float BARRIER_SHORT_SIZE;
    public float BARRIER_LONG_SIZE;
    public float BARRIER_HEIGHT;

    // Player
    public float PLAYER_RADIUS;
    public float PLAYER_MOVE_LEFT;
    public float PLAYER_MOVE_RIGHT;
    public float PLAYER_GRAVITY;

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
        PLAYER_MOVE_LEFT = SCREEN_WIDTH / 25f;
        PLAYER_MOVE_RIGHT = SCREEN_WIDTH / 25f;
        PLAYER_GRAVITY = 10f;

    }




}
