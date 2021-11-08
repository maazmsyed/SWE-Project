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
import com.swe.justslidin.network.Firebase;
import com.swe.justslidin.network.PlayerStats;

public class MainMenu extends AppCompatActivity {

    private static final Constants constants = new Constants();
    private static final String TAG = "MainMenu";
    private DatabaseReference dbRef;

    // Buttons
    Button playerOneButton;
    Button playerTwoButton;
    Button playGame;

    Boolean playerOneAlready = false;
    Boolean playerTwoAlready = false;
    Boolean gameState = false;

    Integer otherPlayerScreenHeight;
    Integer otherPlayerScreenWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playerOneButton = findViewById(R.id.playerOneButton);
        playerTwoButton = findViewById(R.id.playerTwoButton);
        playGame = findViewById(R.id.letsSlideButton);

        // Gets database
        FirebaseDatabase database = Firebase.getDatabase();

        DatabaseReference refP1 = database.getReference().child("playerOne").child("Already");

        refP1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                playerOneAlready = snapshot.getValue(Boolean.class);
                // Log.i(TAG, playerOneAlready != null ? playerOneAlready.toString() : null);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Read for already player one exists failed");
            }
        });

        // Button for Player 1
        playerOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (playerOneAlready) {
                    playerOneButton.setText(R.string.playerOneButtonAlreadySelected);
                    playerOneButton.setEnabled(false);

                } else if (PlayerStats.playerID.equals("")) {
                    PlayerStats.playerID = "playerOne";

                    DatabaseReference p1Ref = database.getReference().child("playerOne");
                    p1Ref.child("Already").setValue(true);
                    p1Ref.child("Exists").setValue(true);
                    p1Ref.child("Screen").child("Width").setValue(constants.SCREEN_WIDTH);
                    p1Ref.child("Screen").child("Height").setValue(constants.SCREEN_HEIGHT);

                    // Temp position to avoid null value
                    Position positionP1 =
                            new Position(constants.SCREEN_WIDTH / 2, constants.SCREEN_HEIGHT / 4);
                    database.getReference().child(PlayerStats.playerID)
                            .child("Pos").child("X").setValue(positionP1.getX());
                    database.getReference().child(PlayerStats.playerID)
                            .child("Pos").child("Y").setValue(positionP1.getY());

                    // database.getReference().child("alreadyPlayerOne").setValue(true);

                    playerOneButton.setText(R.string.playerButtonSelected);
                    playerOneButton.setEnabled(false);
                }
            }
        });


        DatabaseReference refP2 = database.getReference().child("playerTwo").child("Already");

        refP2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                playerTwoAlready = snapshot.getValue(Boolean.class);
                // Log.i(TAG, playerTwoAlready != null ? playerTwoAlready.toString() : null);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, "Read for already player two exists failed");
            }
        });

        playerTwoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (playerTwoAlready) {
                    playerTwoButton.setText(R.string.playerTwoButtonAlreadySelected);
                    playerTwoButton.setEnabled(false);

                } else if (PlayerStats.playerID.equals("")) {
                    PlayerStats.playerID = "playerTwo";

                    DatabaseReference p2Ref = database.getReference().child("playerTwo");
                    p2Ref.child("Already").setValue(true);
                    p2Ref.child("Exists").setValue(true);
                    p2Ref.child("Screen").child("Width").setValue(constants.SCREEN_WIDTH);
                    p2Ref.child("Screen").child("Height").setValue(constants.SCREEN_HEIGHT);

                    // Temp position to avoid null value
                    Position positionP2 =
                            new Position(constants.SCREEN_WIDTH / 2, constants.SCREEN_HEIGHT / 4);
                    database.getReference().child(PlayerStats.playerID)
                            .child("Pos").child("X").setValue(positionP2.getX());
                    database.getReference().child(PlayerStats.playerID)
                            .child("Pos").child("Y").setValue(positionP2.getY());

                    // database.getReference().child("alreadyPlayerTwo").setValue(true);
                    playerTwoButton.setText(R.string.playerButtonSelected);
                    playerTwoButton.setEnabled(false);
                }
            }
        });

        DatabaseReference refGameState = database.getReference().child("gameState");

        playGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (playerOneAlready != null && playerTwoAlready != null && playerOneAlready && playerTwoAlready) {
                    refGameState.setValue(true);
                }
            }

        });

        refGameState.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gameState = snapshot.getValue(Boolean.class);
                // Log.i(TAG, gameState != null ? gameState.toString() : null);
                if (gameState) {
                    // Thread.sleep(100);
                    database.getReference(PlayerStats.playerID).child("gameRunning").setValue(true);

                    // Recently added.
//                    Firebase.getDatabase().getReference(PlayerStats.otherPlayerID)
//                            .child("Screen").child("Height").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            PlayerStats.otherPlayerScreenHeight = snapshot.getValue(Float.class);
//                            Log.i(TAG, "Get other player's Screen Height");
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.i(TAG, "Did not get other player's Screen Height");
//                        }
//                    });
//
//                    Firebase.getDatabase().getReference(PlayerStats.otherPlayerID)
//                            .child("Screen").child("Width").addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            PlayerStats.otherPlayerScreenWidth = snapshot.getValue(Integer.class);
//                            Log.i(TAG, "Get other player's Screen Width");
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Log.i(TAG, "Did not get other player's Screen Width");
//                        }
//                    });






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





