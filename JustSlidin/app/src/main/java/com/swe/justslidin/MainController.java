package com.swe.justslidin;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
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

import java.util.Random;

public class MainController extends Thread {

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;
    private final long fps = 50;
    private boolean running;
    private int counter;
    private Random generator;


    public MainController(SurfaceView sv, Resources context) {
        this.sv = sv;
        this.universe = new Universe();
        this.running = true;
        this.counter = 0;
        this.generator = new Random(100); // TODO: Input seed here

        this.graphicsRenderer = new GraphicsRenderer(this.universe, context);
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer); //triggers graphics renderer BAD DESIGN TODO: MC TRIGGERED BY RENDERER & THEN TRIGGERS ITSELF

        InputListener inputListener = new InputListener();
        this.sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        //inputHandler.setOnClickAction(new StartAction());
        inputHandler.setOnClickAction(new MoveAction(this.universe));
        inputListener.setCallback(inputHandler);

    }


    @Override
    public void run() {
        while (this.running) {

            try {
                this.universe.checkPlayerCollision();
                this.universe.removeExtraElements();
                this.universe.step();
                this.counter += 1;

                if (this.counter % 67 == 0) {
                    this.universe.addCoin(new Position((Constants.COIN_RADIUS * 2)
                            + generator.nextFloat() *
                            (Constants.SCREEN_WIDTH - (Constants.COIN_RADIUS * 4)),
                            Constants.SCREEN_HEIGHT + (Constants.COIN_RADIUS * 2)),
                            Constants.COIN_RADIUS);
                    // this.universe.addCoin(new Position(100 + (new Random().nextFloat())*700, 1000), Constants.COIN_RADIUS);
                }
                if (this.counter % 127 == 0) {
                    this.universe.addBarrier(new Position(Constants.BARRIER_LONG_SIZE
                            + generator.nextFloat() *
                            (Constants.SCREEN_WIDTH - (Constants.BARRIER_LONG_SIZE * 2)),
                            Constants.SCREEN_HEIGHT + Constants.BARRIER_HEIGHT),
                            Constants.BARRIER_HEIGHT);
                    // this.universe.addBarrier(new Position(350 + (new Random().nextFloat())*400, 2000), Constants.BARRIER_HEIGHT);
                }
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Useful: System.currentTimeMillis()