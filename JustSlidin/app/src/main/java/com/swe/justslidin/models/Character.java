package com.swe.justslidin.models;

import com.swe.justslidin.MainController;

/*
*
*
*
* @author:
*
 */

public class Character extends Elements {

    private float rad;
    private Position pos;
    private HitBox hitBox;
//    private MainController mc;

    public Character(float x, float y, float rad) {
        super();
        this.pos = new Position(x,y);
        this.hitBox =
                new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
    }

    /*
    * Moves the character to the left and updates hitbox to reflect the new position.
    *
     */
    public void moveLeft() {
        this.pos.left(10f); // Move character by 10 units to left. Can be changed accordingly.
        this.hitBox.updateLeft(-10f);
        this.hitBox.updateRight(-10f); // Updates hitbox to reflect new player position
    }

    /*
    * Moves the character to the right and updates character's hitbox w/ new coordinates.
    *
     */
    public void moveRight() {
        this.pos.right(10f); // Move character by 10 units to right. Can be changed accordingly.
        this.hitBox.updateLeft(10f);
        this.hitBox.updateRight(10f);
    }

    /*
    * @return 
     */
    public Position getPosition () {return this.pos;}



    public HitBox getHitBox() {
        return this.hitBox;
    }


    public float getRadius() {
        return this.rad;
    }

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
