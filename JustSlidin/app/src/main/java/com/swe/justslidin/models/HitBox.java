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

    boolean collide(HitBox hb){
        return !(this.top >= hb.bottom ||
                this.bottom <= hb.top ||
                this.right <= hb.left ||
                this.left >= hb.right);
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    void updateLeft(float value) {
        this.setLeft(this.left - value);
    }

    void updateRight(float value) {
        this.setRight(this.right + value);
    }

    void updateTop(float value) {
        this.setTop(this.top - value);
    }

    void updateBottom(float value) {
        this.setBottom(this.bottom + value);
    }

}
