package com.swe.justslidin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.swe.justslidin.constants.Constants;

import java.util.Objects;

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

                // DatabaseReference ref = database.getReference("alreadyPlayerOne");
                DatabaseReference ref = database.getReference().child("alreadyPlayerOne");

                ref.addValueEventListener(new ValueEventListener() {
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


                database.getReference().child("PlayerOne").setValue(true);
                database.getReference().child("alreadyPlayerOne").setValue(true); // TODO: remove later on, just for testing

                if (isAlreadyPlayerOne) {
                    playerTwoButton.setText(R.string.playerOneButtonAlreadySelected);
                    playerTwoButton.setEnabled(false);

                }
            }
        });

        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (isAlreadyPlayerTwo){
//                    System.out.println("it comes here");
//                    playerTwoButton.setText(R.string.playerTwoButtonAlreadySelected); // this is unavailable
//                    playerTwoButton.setEnabled(false);
//                }else {
//                database.getReference().child("PlayerTwo").setValue(true);
//                database.getReference().child("alreadyPlayerTwo").setValue(true);
//                playerTwoButton.setText(R.string.buttonselected);
//                playerTwoButton.setEnabled(false);

                DatabaseReference ref2 = database.getReference().child("alreadyPlayerTwo");
                ref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Boolean isAlreadyPlayerTwo_new = snapshot.getValue(Boolean.class);
                        isAlreadyPlayerTwo = isAlreadyPlayerTwo_new;
                        System.out.println(isAlreadyPlayerTwo);
                        Log.i(TAG, isAlreadyPlayerTwo != null ? isAlreadyPlayerTwo.toString() : null);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d(TAG, "Read for already player one exists failed");
                    }
                });
                if (isAlreadyPlayerTwo){
                    playerTwoButton.setText(R.string.playerTwoButtonAlreadySelected);
                    playerTwoButton.setEnabled(false);
                }else {
                    database.getReference().child("PlayerTwo").setValue(true);
                    database.getReference().child("alreadyPlayerTwo").setValue(true);
                    playerTwoButton.setText(R.string.buttonselected);
                    playerTwoButton.setEnabled(false);
                }

            }});
        }}