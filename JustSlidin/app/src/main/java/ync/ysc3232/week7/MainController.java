package ync.ysc3232.week7;

import android.view.SurfaceView;

import ync.ysc3232.week7.io.AddAction;
import ync.ysc3232.week7.io.InputHandler;
import ync.ysc3232.week7.io.InputListener;
import ync.ysc3232.week7.models.Universe;
import ync.ysc3232.week7.view.GraphicsRenderer;

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
