package com.swe.justslidin.models;

import com.swe.justslidin.constants.Constants;

import java.util.Random;

/**
 * This class represents the barrier objects that the player
 * will be placed on the slide.
 */
public class Barrier extends Elements {

    private final float height;
    private final float length;
    private final boolean isShort;
    private Position pos;
    private HitBox hitBox;
    private final float DEFAULT_SHORT_SIZE;
    private final float DEFAULT_LONG_SIZE;
    private static final String TAG = "Barrier";
    private static final Constants constants = new Constants();

    /**
     * Barrier class constructor constructs a barrier object by
     * declaring the position and the hitbox of the barrier.
     * @param x
     * The x-value of the position.
     * @param y
     * The y-value of the position.
     * @param h
     * The height of the barrier.
     */
    public Barrier(float x, float y, float h) {
        super();
        this.pos = new Position(x,y);
        this.height = h;
        this.DEFAULT_SHORT_SIZE = constants.BARRIER_SHORT_SIZE;
        this.DEFAULT_LONG_SIZE = constants.BARRIER_LONG_SIZE;
        Random rd = new Random();
        this.isShort = rd.nextBoolean();
        if (this.isShort) {
            this.length = DEFAULT_SHORT_SIZE;
        } else {
            this.length = DEFAULT_LONG_SIZE;
        }
        this.hitBox =
                new HitBox(x - (this.length / 2),
                        x + (this.length / 2),
                        y + (h / 2),
                        y - (h / 2));
    }

    /**
     * Determines the hitbox for the specific barrier object. Will be
     * useful to determine collisions of the barrier with the player(s).
     * @return
     * A hitBox object.
     */
    public HitBox getHitBox() {
        return this.hitBox;
    }

    /**
     * Simply returns the current position of the barrier. Note that
     * the position returned is of the middle of the barrier.
     * @return
     * The position of the barrier.
     */
    public Position getPosition () { return this.pos; }

    /**
     * Gets the height of the barrier.
     * @return
     * The height of the barrier.
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Gets the length of the barrier.
     * @return
     * The length of the barrier.
     */
    public float getLength() {
        return this.length;
    }

    /**
     * Gets whether the barrier is of a short length or a long length.
     * @return
     * Boolean value for whether barrier is short or not.
     */
    public boolean isShort() {
        return isShort;
    }


    /**
     * moveUp gets the current position of the barrier and moves it up
     * (visually down) of the screen. While doing so, it changes the
     * position and the hitbox to account for the new position.
     * @param m
     * Takes as input a motion event.
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
