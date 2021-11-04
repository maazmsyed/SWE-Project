package com.swe.justslidin.models;

/**
 * This class represents the position of an element. It carries the x- and y-coordinates
 * of the element on the screen.
 */
public class Position {

    private float x;
    private float y;

    /**
     * This method instantiates the position class when only one parameter is passed as input.
     * It places the element's position (both x- and y-coordinate) to be equal to the same
     * float value that was passed as input.
     *
     * @param v
     * The float value that is given as input. Both the x- and y-coordinates of the element
     * are set to be equal to this value.
     */
    public Position(float v) {
        this.x = v;
        this.y = v;
    }

    /**
     * This method instantiates the position class when two parameters are passed as input.
     * It sets the element's x-coordinate to be equal to the first inputted float value and
     * the element's y-coordinate to be equal to the second float value that was passed as input.
     * In doing so, this method declares the position of the element on the screen.
     *
     * @param x
     * This is the value that is set to be the x-coordinate of the element's position.
     * @param y
     * This is the value that is set to be the y-coordinate of the element's position.
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method takes as input an instantiation of the position class. This is basically
     * the change in position of the element. Therefore, the x-coordinate of the element's
     * position is added by the x value of the inputted position (to move the element to the
     * right on the screen). The y-coordinate of the element's position is subtracted by the
     * y value of the inputted position (to move the element towards the top on the screen).
     * We have to subtract the y-coordinate as the y-axis is inverted.
     *
     * @param p
     * This is the instantiation of the position class that represents the change in the
     * position of the element.
     */
    public void add(Position p) {
        this.x += p.x;
        this.y -= p.y;
    }

    /**
     * This method moves the element to the left of the screen by subtracting the x-coordinate
     * of the element by the inputted float value.
     *
     * @param by
     * This is the float value by which the x-coordinate of the element's position is being
     * subtracted (to move the element to the left).
     */
    public void left(float by) {
        this.x -= by;
    }

    /**
     * This method moves the element to the right of the screen by adding the x-coordinate
     * of the element by the inputted float value.
     *
     * @param by
     * This is the float value by which the x-coordinate of the element's position is being
     * added (to move the element to the right).
     */
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
