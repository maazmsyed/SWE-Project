package com.swe.justslidin.models;

import android.util.Log;

import java.util.List;
import java.util.Vector;

public class Universe {

    private static final String TAG = "Universe";
    Motion gravity;
    Character player;
    // List<Character> data;
    List<Elements> elements;

    public Universe () {
        this (new Motion(0,5f), new Character(540, 100, 50f)); // TODO : constant is bad !!
    }

    public Universe (Motion g, Character pl) {
        elements = new Vector<>();
        gravity = g;
        player = pl;
    }

//    public void addChar(float x, float y, float rad) {
//        data.add(new Character (x,y,rad));
//        castChanges();
//    }

    public void addCoin(float x, float y, float rad) {
        elements.add(new Coin(x, y, rad));
        castChanges();
    }

    public void addBarrier(float x, float y, float h, float l) {
        elements.add(new Barrier (x,y,h,l));
        castChanges();
    }

    public List<Elements> getElements() {
        return elements;
    }

    public void step() {
        for (Elements e : elements) {
            e.moveUp(this.gravity);
        }
        castChanges();
    }

    public void addCoin(Position pos, float rad) {
        this.addCoin(pos.getX(),pos.getY(),rad);
    }

    public void addBarrier(Position pos, float h, float l) {
        this.addBarrier(pos.getX(), pos.getY(),h,l);
    }

    public void moveCharLeft() {
        Log.i(TAG,"CHAR HAS MOVED LEFT.");
        player.moveLeft();
        System.out.println(player.getPosition());
    }

    public void moveCharRight() {
        Log.i(TAG,"CHAR HAS MOVED RIGHT.");
        player.moveRight();
        System.out.println(player.getPosition());
    }

    public Position getPosition () { return player.getPosition(); }


// TODO: ADD A ADDCHAR METHOD that works properly.
//    public void addChar(Position pos) {
//        this.addChar(pos.getX(),pos.getY());
//    }

    public interface Callback {
        void universeChanged ( Universe u ) ;
    }

    public void setCallBack(Callback c) {
        callback = c;
    }

    public void addCallBack (Callback c ) {
        this.callback = c;
    }

    protected void castChanges() {
        if (callback != null) {
            callback.universeChanged(this);
        }
    }

    private Callback callback = null;


}
