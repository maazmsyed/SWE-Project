package com.swe.justslidin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity2 extends AppCompatActivity {

    EditText editText;
    Button button;

    String playerName = "";

    private DatabaseReference _myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://softwareeng-cee86-default-rtdb.asia-southeast1.firebasedatabase.app/");
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                //logging the player in
                playerName = editText.getText().toString();
                editText.setText("");
                if(!playerName.equals("")) {
                    button.setText("LOGGING IN");
                    button.setEnabled(false);
                    _myRef = database.getReference("players/" + playerName);
                    addEventListener();
                    _myRef.setValue("");
                }
                //_myRef.setValue(dtf.format(now));
                switchActivities();
            }
        });
        _myRef=database.getReference("message");
        
        
    }

    private void addEventListener() {
        //read from database
        _myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //success - continue to the next screen after saving the player name.
                if(!playerName.equals("")) {

                    switchActivities();
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error
                button.setText("LOG IN");
                button.setEnabled(true);
                Toast.makeText(MainActivity2.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}