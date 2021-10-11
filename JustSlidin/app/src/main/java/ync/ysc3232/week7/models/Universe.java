package ync.ysc3232.week7.models;


import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Universe {
    Motion gravity;
    List<Ball> data;
    public Universe () {
        this (new Motion(0,5f)); // TODO : constant is bad !!
    }
    public Universe (Motion g) {
        data = new Vector<>();
        gravity = g;
    }
    public void addBall(float x, float y) {
        data.add(new Ball (x,y));
        castChanges();
    }
    public Collection<Ball> getBalls() {
        return data;
    }
    public void step() {
        for (Ball b : data) {
            b.move(this.gravity);
        }
        castChanges();
    }
    public void addBall(Position pos) {
        this.addBall(pos.getX(),pos.getY());
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
