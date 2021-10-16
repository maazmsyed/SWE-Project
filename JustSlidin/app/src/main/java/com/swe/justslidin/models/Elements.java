package com.swe.justslidin.models;

import android.graphics.Canvas;
import com.swe.justslidin.MainController;
import java.util.ListIterator;
import java.util.Vector;

public class Elements {

    // TODO: Do we actually need this in Elements?
    private MainController mc;

    private Position pos;
    private HitBox hitBox;
    private boolean pause;

    Elements(MainController mc) {
        this.mc = mc;
    }

    void draw(Canvas canvas) {
    }

//    void move(Vector<Elements> it) {
//    }

    void moveUp(Motion m) {
        // this.pos.add(m);
    }

    HitBox getHitBox() {
        return this.hitBox;
    }

    MainController getMainController() {
        return this.mc;
    }

    boolean getPause() {
        return this.pause;
    }

    Position getPosition () {
        return this.pos;
    }

    void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    void setPause(boolean pause) {
        this.pause = pause;
    }

    void updateHitBoxLeft(float value) {
        hitBox.setLeft(value);
    }

    void updateHitBoxRight(float value) {
        hitBox.setRight(value);
    }

    void updateHitBoxTop(float value) {
        hitBox.setTop(value);
    }

    void updateHitBoxBottom(float value) {
        hitBox.setBottom(value);
    }




}
