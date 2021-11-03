package com.swe.justslidin.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.swe.justslidin.App;
import com.swe.justslidin.constants.Constants;
import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;




public class MoveAction implements ClickAction { // TODO: extends AppCompactActivity
    private static final String TAG = "MoveAction";
    private final Universe universe;
    private final float screenWidth = Constants.SCREEN_WIDTH;
    public Context context = App.getInstance();
    public SharedPreferences preferences = context.getSharedPreferences("PREFS",0);
    private String playerName = preferences.getString("playerName", "");
    private DatabaseReference playerRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://justslidin-d4b80-default-rtdb.asia-southeast1.firebasedatabase.app/");



    public MoveAction(Universe universe) {
        this.universe = universe;
    }


    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveAction executed");
        Log.i(TAG, playerName);
        //universe.addChar(pos);
        this.playerRef = database.getReference("players/"+playerName);

        if (pos.getX() <= screenWidth / 2 && this.universe.isGameRunning()){
            Log.i(TAG,"ON THE LEFT");
            // universe.moveCharLeft(10f);
            this.universe.getPlayer().moveLeft(25);     // TODO; Check with Maaz
            this.playerRef.child("pos").setValue(this.universe.getPlayer().getPosition());
            Log.i(TAG,"ON THE LEFT TO THE DB");
        }
        if (pos.getX() > screenWidth / 2 && this.universe.isGameRunning()) {
            Log.i(TAG,"ON THE RIGHT");
            // universe.moveCharRight(10f);
            this.universe.getPlayer().moveRight(25);
            this.playerRef.child("pos").setValue(this.universe.getPlayer().getPosition());
            Log.i(TAG,"ON THE RIGHT TO THE DB");

        }

    }
}
