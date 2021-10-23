package com.swe.justslidin.models;

import java.util.List;
import java.util.Vector;

public class Universe {

    // TODO: Do we still need gravity?
    final static Motion DEFAULT_GRAVITY_MOTION = new Motion(0,5f); // Added after referring prof
    Motion gravity;
    List<Elements> elements;

    public Universe () {
        this (DEFAULT_GRAVITY_MOTION);
    }

    public Universe (Motion g) {
        elements = new Vector<>();
        gravity = g;
    }

//    public void addChar(float x, float y, float rad) {
//        data.add(new Character (x,y,rad));
//        castChanges();
//    }

    /**
     * This method adds an instantiation of a coin to the elements list.
     *
     * @param x
     * This value defines the x-coordinate of the new coin instance's position.
     * @param y
     * This value defines the y-coordinate of the new coin instance's position.
     * @param rad
     * This value defines the radius (size) of the new coin instance.
     */
    public void addCoin(float x, float y, float rad) {
        elements.add(new Coin(x, y, rad));
        castChanges();
    }

    /**
     * This method adds an instantiation of a barrier to the elements list.
     *
     * @param x
     * This value defines the x-coordinate of the new barrier instance's position.
     * @param y
     * This value defines the y-coordinate of the new barrier instance's position.
     * @param h
     * This value defines the height of the new barrier instance.
     * @param l
     * This value defines the length of the new barrier instance.
     */
    public void addBarrier(float x, float y, float h, float l) {
        elements.add(new Barrier (x,y,h,l));
        castChanges();
    }

    /**
     * Gets the list of elements (list of coins and barriers in the game).
     *
     * @return elements
     */
    public List<Elements> getElements() {
        return elements;
    }

    /**
     * Moves all the elements in the elements list up by the natural gravity. This is because
     * all the coins and barriers in the game have a natural upward movement.
     */
    public void step() {
        for (Elements e : elements) {
            e.moveUp(this.gravity);
        }
        castChanges();
    }

    /**
     * This is just another way to access the addCoin method. If you want to pass an instance of
     * the Position class as input instead of manually inputting the x- and y-coordinates.
     *
     * @param pos
     * The Position of the new coin instance.
     * @param rad
     * This value defines the radius (size) of the new coin instance.
     */
    public void addCoin(Position pos, float rad) {
        this.addCoin(pos.getX(),pos.getY(),rad);
    }

    /**
     * This is just another way to access the addBarrier method. If you want to pass an instance of
     * the Position class as input instead of manually inputting the x- and y-coordinates.
     *
     * @param pos
     * The Position of the new barrier instance.
     * @param h
     * This value defines the height of the new barrier instance.
     * @param l
     * This value defines the length of the new barrier instance.
     */
    public void addBarrier(Position pos, float h, float l) {
        this.addBarrier(pos.getX(), pos.getY(),h,l);
    }

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
