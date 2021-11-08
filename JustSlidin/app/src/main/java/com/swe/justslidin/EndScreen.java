package com.swe.justslidin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.swe.justslidin.network.Firebase;
import com.swe.justslidin.network.PlayerStats;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        TextView resultBox = findViewById(R.id.result_text);

        DatabaseReference dataBase = Firebase.getDatabase().getReference();

        dataBase.child("playerOne").child("Already").setValue(false);
        dataBase.child("playerOne").child("gameRunning").setValue(false);

        dataBase.child("playerTwo").child("Already").setValue(false);
        dataBase.child("playerTwo").child("gameRunning").setValue(false);

        dataBase.child("gameState").setValue(false);

        int resPlayer = PlayerStats.coinCounter + ((int) (5000000 / PlayerStats.elapsedTime));
        int resOtherPlayer = PlayerStats.otherCoinCounter + ((int) (5000000 / PlayerStats.otherElapsedTime));

        if (resPlayer > resOtherPlayer) {

        }

    }
}