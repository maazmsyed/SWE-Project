package com.swe.justslidin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.swe.justslidin.network.PlayerID;

public class MainMenu extends AppCompatActivity {

    private static final Constants constants = new Constants();
    private static final String TAG = "MainMenu";
    private DatabaseReference dbRef;
    Button playerOneButton;
    Button playerTwoButton;
    Boolean isAlreadyPlayerOne = false;
    Boolean isAlreadyPlayerTwo = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playerOneButton = findViewById(R.id.playerOneButton);
        playerTwoButton = findViewById(R.id.playerTwoButton);

        // = FirebaseDatabase.getInstance().getReference();

        FirebaseDatabase database =
                FirebaseDatabase.getInstance("https://just-slidin-default-rtdb.asia-southeast1.firebasedatabase.app/");

        playerOneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

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

                if (isAlreadyPlayerOne) {
                    playerOneButton.setText(R.string.playerOneButtonAlreadySelected);
                    playerOneButton.setEnabled(false);

                } else if (!isAlreadyPlayerTwo) {
                    PlayerID.playerID = "playerOne";
                    database.getReference().child("playerOne").setValue(true);
                    database.getReference().child("alreadyPlayerOne").setValue(true);// TODO: remove later on, just for testing
                    playerOneButton.setText(R.string.playerButtonSelected);
                    playerOneButton.setEnabled(false);
                }
            }
        });


        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                if (isAlreadyPlayerTwo) {
                    playerTwoButton.setText(R.string.playerTwoButtonAlreadySelected);
                    playerTwoButton.setEnabled(false);
                } else if (!isAlreadyPlayerOne) {
                    PlayerID.playerID = "playerTwo";
                    database.getReference().child("playerTwo").setValue(true);
                    database.getReference().child("alreadyPlayerTwo").setValue(true);
                    playerTwoButton.setText(R.string.playerButtonSelected);
                    playerTwoButton.setEnabled(false);
                }
            }
        });


    }
}