package com.swe.justslidin.io;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;




public class MoveAction implements ClickAction { //TODO: extends AppCompatActivity
    private static final String TAG = "MoveAction";
    private final Universe universe;
    private final float screenWidth = Constants.SCREEN_WIDTH;
    String playerName = "";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://justslidin-d4b80-default-rtdb.asia-southeast1.firebasedatabase.app/");
    //TODO: SharedPreferences preferences = getSharedPreferences("PREFS",0);
    DatabaseReference move_ref= database.getReference("players/" + playerName).child("pos").child("x");



    public MoveAction(Universe universe) {
        this.universe = universe;
    }

    // db declaration
    // sp declaration
    // get the player from sp

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveAction executed");
        //universe.addChar(pos);

        //TODO: playerName = preferences.getString("playerName","");

        if (pos.getX() <= screenWidth / 2){
            Log.i(TAG,"ON THE LEFT");
            // universe.moveCharLeft(10f);
            this.universe.getPlayer().moveLeft(15);     // TODO; Check with Maaz
            Log.i(TAG, move_ref.toString());
            move_ref.setValue(this.universe.getPlayer().getPosition().getX());

        }
        else{
            Log.i(TAG,"ON THE RIGHT");
            // universe.moveCharRight(10f);
            this.universe.getPlayer().moveRight(15);
            move_ref.setValue(this.universe.getPlayer().getPosition().getX());
        }

    }
}