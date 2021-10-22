package com.swe.justslidin.models;

/**
 * @author Ozair, Mustafa, Areeb, Maaz, Danny
 */

public class HitBox {

    private float left;
    private float top;
    private float bottom;
    private float right;

    HitBox(float l, float r, float b, float t){
        this.left = l;
        this.bottom = b;
        this.right = r;
        this.top = t;
    }

    /**
     * this function checks if there is an overlap between the character class and the coin or the hurdle
     * @param hb this is the hitbox of either a coin or a hurdle
     * @return
     */
    public boolean collide(HitBox hb){
        return !(this.top >= hb.bottom ||
                this.bottom <= hb.top ||
                this.right <= hb.left ||
                this.left >= hb.right);
    }

    /**
     * returns the left side of the hitbox
     * @return
     */
    public float getLeft() {
        return this.left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    /**
     *
     *returns the top side of the hitbox
     * @return
     */
    public float getTop() {
        return this.top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    /**
     *
     * returns the bottom side of the hitbox
     * @return
     */
    public float getBottom() {
        return this.bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    /**
     * returns the right side of the hitbox
     * @return
     */
    public float getRight() {
        return this.right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    /**
     * this function updates the left side of the character's hitbox for when it moves left or right
     * @param value pre-defined
     */
    public void updateLeft(float value) { this.setLeft(this.left + value); }

    /**
     * this updates the right side of the character's hitbox for when the character moves left or right
     * @param value pre-defined
     */
    public void updateRight(float value) {
        this.setRight(this.right + value);
    }

    /**
     * this updates the top side of the coin or the hurdle's hitbox for when either of them move up
     * @param value pre-defined
     */
    public void updateTop(float value) { this.setTop(this.top - value); }
    /**
     * this updates the bottom side of the coin or the hurdle's hitbox for when either of them move up
     * @param value pre-defined
     */
    public void updateBottom(float value) {
        this.setBottom(this.bottom - value);
    }

}
