package com.swe.justslidin.models;

public class Position {

    private float x,y;

    public Position(float v) {
        this.x = v;
        this.y = v;
    }

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(Position p) {
        this.x += p.x;
        this.y -= p.y;
        // System.out.println(this.x);
        // System.out.println(this.y);
    }

    public void left(float by) {
        this.x -= by;
    }

    public void right(float by) {
        this.x += by;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Float.compare(position.x, x) == 0 && Float.compare(position.y, y) == 0;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

}
