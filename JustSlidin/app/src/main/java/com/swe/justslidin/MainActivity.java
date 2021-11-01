package com.swe.justslidin;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView sv = findViewById(R.id.surfaceView);
        MainController mc = new MainController(sv, getResources(),getApplicationContext());
//        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.coins);
//        mp.start();
        mc.start();
    }

}