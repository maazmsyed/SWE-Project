package com.swe.justslidin.models;

public class Character {

    private float rad;
    private Position pos;
    private String name;
    private int id;


    public Character(float x, float y, String name, int id) {
        this.pos = new Position(x,y);
        this.rad = 30.0f; // TODO: Move the constant somewhere else!!!
        this.name = name;
        this.id = id;
    }

    public void move(Motion m) {
        this.pos.add(m);
    }

    public void moveLeft(float by) {
        this.pos.left(by);
    }

    public void moveRight(float by) {
        this.pos.right(by);
    }

    public Position getPosition() {
        return this.pos;
    }

    public float getRadius() {
        return this.rad;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Character{" +
                "rad=" + rad +
                ", pos=" + pos +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }


}
