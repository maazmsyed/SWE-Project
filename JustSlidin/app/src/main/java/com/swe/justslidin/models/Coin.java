package com.swe.justslidin.models;

import com.swe.justslidin.MainController;


public class Coin extends Elements {

    private float rad;
    private Position pos;
    private HitBox hitBox;
//    private MainController mc;
    private char id = 'c';

    /**
     * Constructor of the Coin
     * @param x is the x coordinate of the coin
     * @param y is the y coordinate of the coin
     * @param rad is the radius of the coin
     */
    public Coin(float x, float y, float rad) {
        super();
//        this.mc = mc;
        this.pos = new Position(x,y);
        this.hitBox =
                new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
    }


    public HitBox getHitBox() {
        return this.hitBox;
    }

    public char getId() {
        return this.id;
    }

    public Position getPosition () { return this.pos; }

    public float getRad() {
        return this.rad;
    }

    /**
     * updates the position of the coin as well as its Hitbox by the given motion, moving the coin upwards
     * @param m given motion m
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
