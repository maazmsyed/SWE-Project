package com.swe.justslidin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.swe.justslidin.network.Firebase;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        DatabaseReference dataBase = Firebase.getDatabase().getReference();

        dataBase.child("playerOne").child("Already").setValue(false);
        dataBase.child("playerOne").child("gameRunning").setValue(false);

        dataBase.child("playerTwo").child("Already").setValue(false);
        dataBase.child("playerTwo").child("gameRunning").setValue(false);

        dataBase.child("gameState").setValue(false);



    }
}