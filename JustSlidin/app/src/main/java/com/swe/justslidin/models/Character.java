package com.swe.justslidin.models;

import android.content.res.Resources;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.swe.justslidin.R;

/**
* Character class that handles everything related to the player character.
*  This class deals with the player creation object, positioning with hitboxes, and movement.
* @author
 * Danny and Maaz
 */

public class Character extends Elements {
    private float rad;
    private int coinCount;
    private Position pos;
    private HitBox hitBox;
    private boolean hitCoin;
    private boolean hitBarrier;
    private Position absolutePos;
    private static final String TAG = "Character";


    /**
    * Constructor that creates a new Character with a certain x- and y-coordinate, radius, and hitbox.
    * @param x x-coordinate for player position
    * @param y y-coordinate for player position
    * @param rad radius holds the radius value.
     */
    public Character(float x, float y, float rad) {
        super();
        this.pos = new Position(x,y);
        this.hitBox = new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
        this.coinCount = 0;
        this.absolutePos = new Position(x, y);
    }

    /**
     * Sets whether the character is in contact with a coin or not.
     * @param bool
     * The value to set the character-coin contact.
     * Input true if they come in contact, false otherwise.
     */
    public void setHitCoin(boolean bool) {
        this.hitCoin = bool;
    }

    /**
     * Checks if the character is in contact with a coin or not.
     * @return
     * The value for the character-coin contact.
     * True if currently in contact, false otherwise.
     */
    public boolean ifHitCoin() {
        return this.hitCoin;
    }

    /**
     * Sets whether the character is in contact with a barrier or not.
     * @param bool
     * The value to set the character-barrier contact.
     * Input true if they come in contact, false otherwise.
     */
    public void setHitBarrier(boolean bool) {
        this.hitBarrier = bool;
    }

    /**
     * Checks if the character is in contact with a barrier or not.
     * @return
     * The value for the character-barrier contact.
     * True if currently in contact, false otherwise.
     */
    public boolean ifHitBarrier() {
        return this.hitBarrier;
    }

    /**
     * Updates the y-value of the absolute position of the character.
     * Useful when we want to determine relative positions of multiple players.
     * @param m
     * The motion by which the y-value of the absolution position is updated.
     */
    public void updateAbsPosY(Motion m) {
        float y = this.absolutePos.getY();
        this.absolutePos.setY(y + m.getY());
    }

    /**
     * Updates the x-value of the absolute position of the character.
     * Useful when we want to determine relative positions of multiple players.
     */
    public void updateAbsPosX() {
        this.absolutePos.setX(this.pos.getX());
    }

    /**
     * Moves the character to the left by the units input and updates
     * the position and hitbox to reflect the new position.
     * @param f
     * The float value by which the player moves left.
     */
    public void moveLeft(float f) {
        if (this.pos.getX() > this.rad) {
            this.pos.left(f);
            this.hitBox.updateLeft(-f);
            this.hitBox.updateRight(-f);
            this.updateAbsPosX();
        }
    }

    /**
     * Moves the character to the right by the units input and updates
     * the position and hitbox to reflect the new position.
     * @param f
     * The float value by which the player moves right.
     */
    public void moveRight(float f) {
        float widthPixels = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (this.pos.getX() < widthPixels - this.rad) {
            this.pos.right(f);
            this.hitBox.updateLeft(f);
            this.hitBox.updateRight(f);
            this.updateAbsPosX();
        }
    }

    /**
    * Returns the Position of the Character. Composed of x- and y-coordinate.
    * @return
     * Position object of the character.
     */
    public Position getPosition () {return this.pos;}


    /**
    * Returns HitBox of the Character. Composed of 4-coordinates to get height and width.
    * @return
     * Hitbox object of the character.
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

    /**
     * Gets the amount of coins collected by the player minus
     * any subtractions from hitting barriers.
     * @return
     * The coin counter integer value.
     */
    public int getCoinCount() {
        return this.coinCount;
    }

    /**
     * Increments the coin counter by one integer (meaning 1 coin collected)
     */
    public void updateCoinCount() {
        this.coinCount += 1;
    }

    /**
     * Decrements the coin counter by one integer (meaning 1 coin is lost)
     */
    public void decrementCoinCount() {
        this.coinCount -= 1;
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
                ", coinCount=" + coinCount +
                ", hitBox=" + li[0] + " " +  li[1] + " " + li[2] + " " +  li[3] +
                '}';
    }

}
