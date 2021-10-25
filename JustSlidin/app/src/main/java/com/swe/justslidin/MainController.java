package com.swe.justslidin;

import android.content.res.Resources;
import android.view.SurfaceView;

import com.swe.justslidin.io.AddAction;
import com.swe.justslidin.io.InputHandler;
import com.swe.justslidin.io.InputListener;
import com.swe.justslidin.models.Background;
import com.swe.justslidin.models.Motion;
import com.swe.justslidin.io.MoveAction;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;
import com.swe.justslidin.view.GraphicsRenderer;

public class MainController extends Thread {

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;
    private final long fps = 60;


    public MainController(SurfaceView sv, Resources context) {
        this.sv = sv;
        this.universe = new Universe();

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

    public void GenerateCoins(){

    }


    @Override
    public void run() {
        int counter = 0;
        while (true) {

            try {

                this.universe.step();
                counter += 1;
                if (counter % 50 == 0) {
                    this.universe.addCoin(new Position(500, 1000), 50);
                }
                if (counter % 77 == 0) {
                    this.universe.addBarrier(new Position(700, 2000), 20);
                }
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}