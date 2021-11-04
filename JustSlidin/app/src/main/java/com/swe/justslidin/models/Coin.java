package com.swe.justslidin.models;

import com.swe.justslidin.constants.Constants;

/**
 * This class represents the coin objects that will be
 * placed on the slide.
 */
public class Coin extends Elements {

    private float rad;
    private Position pos;
    private HitBox hitBox;
    private static final String TAG = "Coin";
    private static final Constants constants = new Constants();

    /**
     * Constructor of the Coin
     * @param x is the x coordinate of the coin
     * @param y is the y coordinate of the coin
     * @param rad is the radius of the coin
     */
    public Coin(float x, float y, float rad) {
        super();
        this.pos = new Position(x,y);
        this.hitBox = new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
    }

    /**
     * Sets the position of the coin.
     * @param pos
     * The new position of the coin.
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Gets the HitBox surrounding the coin.
     * @return
     * HitBox object of that coin.
     */
    public HitBox getHitBox() {
        return this.hitBox;
    }

    /**
     * Gets the current position of the coin.
     * @return
     * The position object of the coin.
     */
    public Position getPosition () { return this.pos; }

    /**
     * Gets the radius of the coin as a float.
     * @return
     * The value of the coin's radius.
     */
    public float getRad() {
        return this.rad;
    }

    /**
     * Updates the position of the coin as well as its Hitbox by the given motion.
     * Mainly used to change the vertical positioning of the coin but it can
     * also work for horizontal motion changes.
     * @param m
     * The motion by which the coin is to move.
     */
    @Override public void moveUp(Motion m) {
        float x = m.getX();
        float y = m.getY();
        this.pos.add(m);
        this.hitBox.updateLeft(x); // Won't really use
        this.hitBox.updateRight(x); // Won't really use
        this.hitBox.updateTop(y);
        this.hitBox.updateBottom(y);
    }

}
