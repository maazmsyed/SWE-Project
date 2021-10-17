package com.swe.justslidin.models;

import com.swe.justslidin.MainController;

public class Character extends Elements {

    private float rad;
    private Position pos;
    private HitBox hitBox;
//    private MainController mc;

    public Character(float x, float y, float rad) {
        super(x, y);
        this.pos = new Position(x,y);
        this.hitBox =
                new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
    }

    public void moveLeft(float by) {
        this.pos.left(by);
        this.hitBox.updateLeft(by);
        this.hitBox.updateRight(-by);
    }

    public void moveRight(float by) {
        this.pos.right(by);
        this.hitBox.updateLeft(-by);
        this.hitBox.updateRight(by);
    }

    public float getRadius() {
        return this.rad;
    }

    @Override
    public String toString() {
        return "Character{" +
                "rad=" + rad +
                ", pos=" + pos +
                ", hitBox=" + hitBox +
                '}';
    }

}
