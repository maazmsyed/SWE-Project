package com.swe.justslidin.models;

import com.swe.justslidin.MainController;

public class Coin extends Elements {

    private float rad;
    private Position pos;
    private HitBox hitBox;
//    private MainController mc;
    private char id = 'c';

    public Coin( float x, float y, float rad) {
        super(x, y);
//        this.mc = mc;
        this.pos = new Position(x,y);
        this.hitBox =
                new HitBox(x - rad, x + rad, y + rad, y - rad);
        this.rad = rad;
    }

    public char getId() {
        return this.id;
    }

    public float getRad() {
        return this.rad;
    }

    @Override public void moveUp(Motion m) {
        // float x = m.getX();
        float y = m.getY();
        this.pos.add(m);
        this.hitBox.updateTop(y);
        this.hitBox.updateBottom(y);
    }

}
