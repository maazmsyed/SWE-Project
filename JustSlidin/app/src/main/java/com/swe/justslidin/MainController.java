package com.swe.justslidin;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.view.SurfaceView;

import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.io.AddAction;
import com.swe.justslidin.io.InputHandler;
import com.swe.justslidin.io.InputListener;
import com.swe.justslidin.models.Background;
import com.swe.justslidin.models.Motion;
import com.swe.justslidin.io.MoveAction;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;
import com.swe.justslidin.view.GraphicsRenderer;
import com.swe.justslidin.view.SoundPlayer;

import java.util.Random;

public class MainController extends Thread {

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;
    private final long fps = 60;
    private boolean running;
    private SoundPlayer sound;



    public MainController(SurfaceView sv, Resources context, Context applicationContext) {
        this.sv = sv;
        this.universe = new Universe();
        this.running = true;
        this.graphicsRenderer = new GraphicsRenderer(this.universe, context);
        this.sound = new SoundPlayer(applicationContext);
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer); //triggers graphics renderer BAD DESIGN TODO: MC TRIGGERED BY RENDERER & THEN TRIGGERS ITSELF
//        MediaPlayer mp = MediaPlayer.create(context, R.raw.coin);
//        mp.start();
        InputListener inputListener = new InputListener();
        this.sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        //inputHandler.setOnClickAction(new StartAction());
        inputHandler.setOnClickAction(new MoveAction(this.universe));
        inputListener.setCallback(inputHandler);

    }


    @Override
    public void run() {
        int counter = 0;

        while (running) {

            try {
                if (this.universe.getPlayer().ifHitCoin()){
                    sound.PlayCoinSound();
                    //this.universe.getPlayer().setHitCoin(false);
                }
                this.universe.checkPlayerCollision();
                // this.universe.CheckPlayerCoinCollision();
                this.universe.removeExtraElements();
                this.universe.step();
                counter += 1;
                if (counter % 67 == 0) {
                    this.universe.addCoin(new Position(100 + (new Random().nextFloat())*700, 1000), Constants.COIN_RADIUS);
                }
                if (counter % 127 == 0) {
                    this.universe.addBarrier(new Position(350 + (new Random().nextFloat())*400, 2000), Constants.BARRIER_HEIGHT);
                }
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}