package com.swe.justslidin;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.io.AddAction;
import com.swe.justslidin.io.InputHandler;
import com.swe.justslidin.io.InputListener;
import com.swe.justslidin.models.Background;
import com.swe.justslidin.models.Motion;
import com.swe.justslidin.io.MoveAction;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;
import com.swe.justslidin.network.Firebase;
import com.swe.justslidin.network.PlayerStats;
import com.swe.justslidin.view.GraphicsRenderer;

import java.util.Random;

public class MainController extends Thread {

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;
    private final long fps = 50;
    private int counter;
    private Random generator;
    private static final Constants constants = new Constants();
    public Boolean globalGameRunning;
    public Boolean otherPlayerGameRunning;
    private static final String TAG = "MainController";


    public MainController(SurfaceView sv, Resources context) {
        this.otherPlayerGameRunning = true;
        this.globalGameRunning = false;
        this.sv = sv;
        this.universe = new Universe();
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
        long startTime = System.currentTimeMillis();

        while (this.universe.isGameRunning()) {

            try {
                this.universe.checkPlayerCollision();
                this.universe.removeExtraElements();
                this.universe.step();
                this.counter += 1;

                if (this.universe.getFinishingLine().getHitBox().getTop() > constants.SCREEN_HEIGHT) {
                    if (this.counter % 67 == 0) {
                        this.universe.addCoin(new Position((constants.COIN_RADIUS * 2)
                                        + generator.nextFloat() *
                                        (constants.SCREEN_WIDTH - (constants.COIN_RADIUS * 4)),
                                        constants.SCREEN_HEIGHT + (constants.COIN_RADIUS * 2)),
                                constants.COIN_RADIUS);
                    }
                    if (this.counter % 127 == 0) {
                        this.universe.addBarrier(new Position(constants.BARRIER_LONG_SIZE
                                        + generator.nextFloat() *
                                        (constants.SCREEN_WIDTH - (constants.BARRIER_LONG_SIZE * 2)),
                                        constants.SCREEN_HEIGHT + constants.BARRIER_HEIGHT),
                                constants.BARRIER_HEIGHT);
                    }
                }
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        PlayerStats.elapsedTime = endTime - startTime;
        PlayerStats.coinCounter = this.universe.getPlayer().getCoinCount();

        Firebase.getDatabase().getReference(PlayerStats.playerID)
                .child("CoinCount").setValue(PlayerStats.coinCounter);
        Firebase.getDatabase().getReference(PlayerStats.playerID)
                .child("ElapsedTime").setValue(PlayerStats.elapsedTime);

        Firebase.getDatabase().getReference(PlayerStats.playerID).child("gameRunning").setValue(false);

        Firebase.getDatabase().getReference(PlayerStats.otherPlayerID)
                .child("gameRunning").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                otherPlayerGameRunning = snapshot.getValue(Boolean.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "Couldn't get other player's game running state");
            }
        });

        while (otherPlayerGameRunning) {
            try {
                this.universe.stop();
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace(); // TODO: Could add graceful shutdown from PCDP midterm
            }

        }

        Firebase.getDatabase().getReference(PlayerStats.otherPlayerID)
                .child("CoinCount").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlayerStats.otherCoinCounter = snapshot.getValue(Integer.class);
                Log.i(TAG, "Got other player's coin counter");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "Did not get other player's coin counter");

            }
        });

        Firebase.getDatabase().getReference(PlayerStats.otherPlayerID)
                .child("ElapsedTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PlayerStats.otherElapsedTime = snapshot.getValue(Long.class);
                Log.i(TAG, "Got other player's elapsed time");
                currentThread().interrupt();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i(TAG, "Did not get other player's elapsed time");
            }
        });

        Log.i(TAG, "Is it raising the flag?");

        PlayerStats.gameEnded = true;

    }
}

// Useful: System.currentTimeMillis()