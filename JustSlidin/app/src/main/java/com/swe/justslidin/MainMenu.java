package com.swe.justslidin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.network.PlayerStats;

public class MainMenu extends AppCompatActivity {

    private static final Constants constants = new Constants();
    private static final String TAG = "MainMenu";
    private DatabaseReference dbRef;
    Button playerOneButton;
    Button playerTwoButton;
    Button playGame;
    Boolean isAlreadyPlayerOne = false;
    Boolean isAlreadyPlayerTwo = false;
    Boolean gameState = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playerOneButton = findViewById(R.id.playerOneButton);
        playerTwoButton = findViewById(R.id.playerTwoButton);
        playGame = findViewById(R.id.letsSlideButton);

        // = FirebaseDatabase.getInstance().getReference();

        FirebaseDatabase database =
                FirebaseDatabase.getInstance("https://justslidin-94ef6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference refP1 = database.getReference().child("alreadyPlayerOne");

        refP1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                isAlreadyPlayerOne = snapshot.getValue(Boolean.class);
                Log.i(TAG, isAlreadyPlayerOne != null ? isAlreadyPlayerOne.toString() : null);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Read for already player one exists failed");
            }
        });

//        isAlreadyPlayerOne = Firebase.getBoolean("alreadyPlayerOne");

        playerOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // DatabaseReference ref = database.getReference("alreadyPlayerOne");
                if (isAlreadyPlayerOne) {
                    playerOneButton.setText(R.string.playerOneButtonAlreadySelected);
                    playerOneButton.setEnabled(false);
                } else if (PlayerStats.playerID.equals("")) {
                    PlayerStats.playerID = "playerOne";
                    database.getReference().child("playerOne").setValue(true);
                    Position positionP1 = new Position(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4);
                    database.getReference().child(PlayerStats.playerID + "Pos").child("X").setValue(positionP1.getX());
                    database.getReference().child(PlayerStats.playerID + "Pos").child("Y").setValue(positionP1.getY());
                    database.getReference().child("alreadyPlayerOne").setValue(true);// TODO: remove later on, just for testing
                    playerOneButton.setText(R.string.playerButtonSelected);
                    playerOneButton.setEnabled(false);
                }
//                if (isAlreadyPlayerTwo) {
//                    startActivity(new Intent(MainMenu.this, MainActivity.class));
//                }
            }
        });


        DatabaseReference refP2 = database.getReference().child("alreadyPlayerTwo");

        refP2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isAlreadyPlayerTwo = snapshot.getValue(Boolean.class);

                Log.i(TAG, isAlreadyPlayerTwo != null ? isAlreadyPlayerTwo.toString() : null);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Read for already player two exists failed");
            }
        });

        playerTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isAlreadyPlayerTwo) {
                    playerTwoButton.setText(R.string.playerTwoButtonAlreadySelected);
                    playerTwoButton.setEnabled(false);
                } else if (PlayerStats.playerID.equals("")) {
                    PlayerStats.playerID = "playerTwo";
                    database.getReference().child("playerTwo").setValue(true);
                    Position positionP2 = new Position(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4);
                    database.getReference().child(PlayerStats.playerID + "Pos").child("X").setValue(positionP2.getX());
                    database.getReference().child(PlayerStats.playerID + "Pos").child("Y").setValue(positionP2.getY());
                    database.getReference().child("alreadyPlayerTwo").setValue(true);
                    playerTwoButton.setText(R.string.playerButtonSelected);
                    playerTwoButton.setEnabled(false);
                }
//                if (isAlreadyPlayerOne){
//                    startActivity(new Intent(MainMenu.this, MainActivity.class));
//                }
            }
        });

        DatabaseReference refGameState = database.getReference().child("gameState");

        playGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isAlreadyPlayerOne && isAlreadyPlayerTwo) {
                    refGameState.setValue(true);
                }
            }
        });

        refGameState.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gameState = snapshot.getValue(Boolean.class);
                Log.i(TAG, gameState != null ? gameState.toString() : null);
                if (gameState) {
                    startActivity(new Intent(MainMenu.this, MainActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Reading game state failed");
            }
        });

    }
}






