package com.swe.justslidin.models;

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

    public boolean collide(HitBox hb){
        return !(this.top >= hb.bottom ||
                this.bottom <= hb.top ||
                this.right <= hb.left ||
                this.left >= hb.right);
    }

    public float getLeft() {
        return this.left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return this.top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return this.bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getRight() {
        return this.right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public void updateLeft(float value) { this.setLeft(this.left + value); }

    public void updateRight(float value) {
        this.setRight(this.right + value);
    }

    public void updateTop(float value) { this.setTop(this.top - value); }

    public void updateBottom(float value) {
        this.setBottom(this.bottom - value);
    }

}
