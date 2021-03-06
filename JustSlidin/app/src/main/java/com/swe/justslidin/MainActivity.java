package com.swe.justslidin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.media.AudioManager;
import android.media.SoundPool;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.swe.justslidin.network.Firebase;
import com.swe.justslidin.network.PlayerStats;

public class MainActivity extends AppCompatActivity {

    public static Boolean endGameRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String TAG = "MainActivity";
        setContentView(R.layout.activity_main);
        SurfaceView sv = findViewById(R.id.surfaceView);
        MainController mc = new MainController(sv, getResources(), getApplicationContext());
        DatabaseReference dataBase = Firebase.getDatabase().getReference();
        final Boolean[] playerOneRunning = {true};
        final Boolean[] playerTwoRunning = {true};
//        FirebaseDatabase database = Firebase.getDatabase();
//        Boolean playerGameRunning;
//        Boolean otherPlayerGameRunning;



        mc.start();
//        try {
//            mc.join(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        dataBase.child("playerOne").child("gameRunning").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerOneRunning[0] = snapshot.getValue(Boolean.class);
                if (!playerOneRunning[0] && !playerTwoRunning[0]) {
//                    try {
//                        mc.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    endGameRunning = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(MainActivity.this, EndScreen.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dataBase.child("playerTwo").child("gameRunning").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                playerTwoRunning[0] = snapshot.getValue(Boolean.class);
                if (!playerOneRunning[0] && !playerTwoRunning[0]) {
//                    try {
//                        mc.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    endGameRunning = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(MainActivity.this, EndScreen.class));
                }
                Log.i(TAG, "Fetched player one game running value");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        if (  !(playerOneRunning[0] && playerTwoRunning[0])  ) {
//            try {
//                mc.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        if (PlayerStats.gameEnded){
//            endGameRunning = false;
//            try {
//                Log.i(TAG, "Is it joining?");
//                mc.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            startActivity(new Intent(MainActivity.this, EndScreen.class));
//        }





    }

}