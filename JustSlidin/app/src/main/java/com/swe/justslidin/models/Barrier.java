package com.swe.justslidin.models;

import com.swe.justslidin.MainController;

public class Barrier extends Elements {

    private float height;
    private float length;
    private Position pos;
    private HitBox hitBox;
    private MainController mc;
    private char id = 'b';

    public Barrier(MainController mc, float x, float y, float h, float l) {
        super(mc, x, y);
        this.mc = mc;
        this.pos = new Position(x,y);
        this.hitBox =
                new HitBox(x - (l / 2),
                        x + (l / 2),
                        y + (h / 2),
                        y - (h / 2));
        this.height = h;
        this.length = l;
    }

    public char getId() {
        return this.id;
    }

    public float getHeight() {
        return this.height;
    }

    public float getLength() {
        return this.length;
    }

    @Override public void moveUp(Motion m) {
        // float x = m.getX();
        float y = m.getY();
        this.pos.add(m);
        this.hitBox.updateTop(y);
        this.hitBox.updateBottom(y);
    }

}
