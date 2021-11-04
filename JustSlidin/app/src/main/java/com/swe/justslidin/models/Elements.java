package com.swe.justslidin.models;

import android.graphics.Canvas;
import com.swe.justslidin.MainController;
import java.util.ListIterator;
import java.util.Vector;

/**
 * This class is the parent class for all the elements in
 * the game (Coins, Barriers, and the Player(s)).
 */
public class Elements {

    public Elements() { }

    public void draw(Canvas canvas) { }

    public void moveUp(Motion m) { }

}
