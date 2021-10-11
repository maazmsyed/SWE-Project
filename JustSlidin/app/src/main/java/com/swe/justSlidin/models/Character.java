package com.swe.justslidin.models;

public class Character {

    private float rad;
    private Position pos;

    public Character(float x, float y) {
        this.pos = new Position(x,y);
        this.rad = 30.0f; // TODO: Move the constant somewhere else!!!
    }

    public void move(Motion m) {
        this.pos.add(m);
    }

    public Position getPosition() {
        return this.pos;
    }

    public float getRadius() {
        return this.rad;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "rad=" + rad +
                ", pos=" + pos +
                '}';
    }

}
