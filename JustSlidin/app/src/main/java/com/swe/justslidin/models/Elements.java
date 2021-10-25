package com.swe.justslidin.models;

import android.graphics.Canvas;
import com.swe.justslidin.MainController;
import java.util.ListIterator;
import java.util.Vector;

public class Elements {

    // TODO: Do we actually need this in Elements?
//    private MainController mc;
//    private Position pos;
    private boolean pause = false;
//    private Position pos;
//    private char id;

    public Elements() {
//        this.mc = mc;
//        this.pos = new Position(x, y);
    }

    public void draw(Canvas canvas) {
    }

    public void moveUp(Motion m) {
    }

//    public Position getPosition () {
//        return this.pos;
//    }
//
//    public char getId() {
//        return this.id;
//    }

//    public MainController getMainController() {
//        return this.mc;
//    }

    public boolean getPause() {
        return this.pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

//    public void setHitBox(HitBox hitBox) {
//        this.hitBox = hitBox;
//    }

//    void updateHitBoxLeft(float value) {
//        this.hitBox.setLeft(value);
//    }
//
//    void updateHitBoxRight(float value) {
//        this.hitBox.setRight(value);
//    }
//
//    void updateHitBoxTop(float value) {
//        this.hitBox.setTop(value);
//    }
//
//    void updateHitBoxBottom(float value) {
//        this.hitBox.setBottom(value);
//    }

}
