package com.swe.justslidin;

import android.view.SurfaceView;

import com.swe.justslidin.io.AddAction;
import com.swe.justslidin.io.InputHandler;
import com.swe.justslidin.io.InputListener;
import com.swe.justslidin.models.Universe;
import com.swe.justslidin.view.GraphicsRenderer;

public class MainController extends Thread {

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;

    public MainController(SurfaceView sv) {
        this.sv = sv;
        this.universe = new Universe();
        this.graphicsRenderer = new GraphicsRenderer(this.universe);
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer);

        InputListener inputListener = new InputListener();
        this.sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        inputHandler.setOnClickAction(new AddAction(this.universe));
        inputListener.setCallback(inputHandler);

    }


    @Override
    public void run() {
        while (true) {
            this.universe.step();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
