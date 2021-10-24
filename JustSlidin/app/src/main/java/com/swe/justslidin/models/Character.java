package com.swe.justslidin.models;

import android.content.res.Resources;


/**
* Character class that handles everything related to the player character.
*  This class deals with the player creation object, positioning with hitboxes, and movement.
* @author: Danny and Maaz
*
 */

public class Character extends Elements {
    private float rad;
    private int coinCount;
    private Position pos;
    private HitBox hitBox;

    /**
    * Constructor that creates a new Character with a certain x- and y-coordinate, radius, and hitbox.
    * @param x x-coordinate for player position
    * @param y y-coordinate for player position
    * @param rad radius holds the radius value.
     */
    public Character(float x, float y, float rad) {
        super();
        this.pos = new Position(x,y);
        this.hitBox =
                new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
        this.coinCount = 0;
    }

    /**
    * Moves the character to the left by 10 units and updates hitbox to reflect the new position.
    *
     */
    public void moveLeft(float f) {
        if (this.pos.getX() > this.rad) {
            this.pos.left(f);
            this.hitBox.updateLeft(-f);
            this.hitBox.updateRight(-f);
        }
    }

    /**
    * Moves the character to the right by 10 units and updates character's hitbox w/ new coordinates.
    *
     */
    public void moveRight(float f) {
        float widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (this.pos.getX() < widthPixels - this.rad) {
            this.pos.right(f);
            this.hitBox.updateLeft(f);
            this.hitBox.updateRight(f);
        }
    }

    /**
    * Returns the Position of the Character. Composed of x- and y-coordinate.
    * @return: Position object of the character.
     */
    public Position getPosition () {return this.pos;}


    /**
    * Returns HitBox of the Character. Composed of 4-coordinates to get height and width.
    * @return: Hitbox object of the character.
     */
    public HitBox getHitBox() {
        return this.hitBox;
    }

    /**
    * Returns radius of the Character object. Composed of a single value.
    * @return: Radius of the Character object.
     */
    public float getRadius() {
        return this.rad;
    }

    public int getCoinCount() {
        return this.coinCount;
    }

    public void updateCoinCount() {
        this.coinCount += 1;
    }

    /**
    * Returns a String variable with all the relevant information of the Character
    * @return: String with Character, radius, position, and hitbox coordinates
     */
    @Override
    public String toString() {
        float li[] = hitBox.getBox();
        return "Character{" +
                "rad=" + rad +
                ", pos=" + pos +
                ", hitBox=" + li[0] + " " +  li[1] + " " + li[2] + " " +  li[3] +
                '}';
    }

}
