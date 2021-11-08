package com.swe.justslidin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.swe.justslidin.network.Firebase;
import com.swe.justslidin.network.PlayerStats;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        final String TAG = "Universe";

        TextView resultBox = findViewById(R.id.result_text);

        DatabaseReference database = Firebase.getDatabase().getReference();

        database.child(PlayerStats.playerID).child("Already").setValue(false);
        database.child(PlayerStats.playerID).child("gameRunning").setValue(false);

        //        database.child("playerOne").child("Already").setValue(false);
        //        database.child("playerOne").child("gameRunning").setValue(false);
        //
        //        database.child("playerTwo").child("Already").setValue(false);
        //        database.child("playerTwo").child("gameRunning").setValue(false);

        database.child("gameState").setValue(false);

        int resPlayer = PlayerStats.coinCounter + ((int) (5000000 / PlayerStats.elapsedTime));
        int resOtherPlayer = PlayerStats.otherCoinCounter + ((int) (5000000 / PlayerStats.otherElapsedTime));

        if (resPlayer > resOtherPlayer) {
            Log.i(TAG, "is res player > other player?");
            if (PlayerStats.playerID.equals("playerOne")) {
                resultBox.setText(R.string.playerOneWon);
            } else if (PlayerStats.otherPlayerID.equals("playerTwo")) {
                resultBox.setText(R.string.playerTwoWon);
            }
        } else if (resPlayer < resOtherPlayer) {
            Log.i(TAG, "is res player < other player?");
            if (PlayerStats.playerID.equals("playerOne")) {
                resultBox.setText(R.string.playerTwoWon);
            } else if (PlayerStats.otherPlayerID.equals("playerTwo")) {
                resultBox.setText(R.string.playerOneWon);
            }
        } else {
            resultBox.setText(R.string.tie);
        }

    }
}