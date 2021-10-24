package com.swe.justslidin;

import android.content.res.Resources;
import android.view.SurfaceView;

import com.swe.justslidin.io.AddAction;
import com.swe.justslidin.io.InputHandler;
import com.swe.justslidin.io.InputListener;
import com.swe.justslidin.models.Background;
import com.swe.justslidin.models.Motion;
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
        inputHandler.setOnClickAction(new AddAction(this.universe));
        inputListener.setCallback(inputHandler);

    }

    public void GenerateCoins(){

    }


    @Override
    public void run() {
        while (true) {

            try {

                this.universe.step();

                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
