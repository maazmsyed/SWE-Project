package com.swe.justslidin.models;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Universe {

    Motion gravity;
    List<Character> data;

    public Universe () {
        this (new Motion(0, -5.0f)); // TODO : constant is bad !!
    }

    public Universe (Motion g) {
        data = new Vector<>();
        gravity = g;
    }

    public void addChar(float x, float y) {
        data.add(new Character (x,y));
        castChanges();
    }

    public Collection<Character> getChar() {
        return data;
    }

    public void down() {
        for (Character b : data) {
            b.move(this.gravity);
        }
        castChanges();
    }

    public void addChar(Position pos) {
        this.addChar(pos.getX(),pos.getY());
    }

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
