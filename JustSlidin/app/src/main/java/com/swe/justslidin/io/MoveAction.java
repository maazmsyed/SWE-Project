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

/**
 * The MoveAction class implements ClickAction (which itself extends Action)
 * Here, the position of the character (within the given Universe) will be updated according to a user's clicking position
 * The .execute() function, given the user's clicking coordinates, then performs the updating of the user's positions both in the universe and to the database
 */


public class MoveAction implements ClickAction { // TODO: extends AppCompactActivity
    private static final String TAG = "MoveAction";
    private static final Constants constants = new Constants();
    private final Universe universe;
    private final float screenWidth = constants.SCREEN_WIDTH;
    private DatabaseReference playerRef;

    Context context = App.getInstance();
    FirebaseDatabase database;
    String playerName = "";

    public MoveAction(Universe universe) {
        this.universe = universe;
    }

    /**
     * The .execute function that ClickActions possess
     * @param pos is the Position object parameter that this function takes
     * There are two possible scenarios in this case: one where the clicking position is on the left half of the screen, and the other the right half
     * We have a constant screenWidth that documents the width of the screen. For standard Pixel 2s, it is 540
     * To move the characters, we first get the given universe (this.universe), then the character (.getPlayer), then call the moveRight/moveLeft functions
     * To update data to the database, we make use of the setValue function from Firebase
     * we specify the child nodes "players" -> "pos" (documents position of that specific player)
     * Since this class (MoveAction.java) is not an "Activity", to run getSharedPreferences, we must get an instance of the App as context
     */

    @Override
    public void execute(Position pos) {
        Log.i(TAG, "MoveAction executed");
        database = FirebaseDatabase.getInstance("https://justslidin-d4b80-default-rtdb.asia-southeast1.firebasedatabase.app/");
        SharedPreferences preferences = context.getSharedPreferences("PREFS",0);
        playerName = preferences.getString("playerName","");

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
