package com.swe.justslidin.view;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.swe.justslidin.R;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static int hitCoinSound;
    private static int hitBarrierSound;

    public SoundPlayer(Context context) {
        this.soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        this.hitCoinSound = soundPool.load(context, R.raw.coins,1);
    }

    public void PlayCoinSound() {
        soundPool.play(hitCoinSound,1.0f,1.0f,1,0,1.0f);
    }



}
