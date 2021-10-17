package com.swe.justslidin;

import com.swe.justslidin.models.Barrier;
import com.swe.justslidin.models.Motion;

import org.junit.Test;

public class TestBarrier {

    @Test
    public void test_hitBox() {
        Barrier b = new Barrier(50, 100, 10, 50);

        assert (b.getHitBox().getLeft() == 25.0f);
        assert (b.getHitBox().getRight() == 75.0f);
        assert (b.getHitBox().getTop() == 95.0f);
        assert (b.getHitBox().getBottom() == 105.0f);

        // x is 0 because we wouldn't be changing the x-value
        // although y is positive, moveUp is adjusted to go up
        b.moveUp(new Motion(0, 10));
        b.moveUp(new Motion(0, 10));

        assert (b.getHitBox().getLeft() == 25.0f);
        assert (b.getHitBox().getRight() == 75.0f);
        assert (b.getHitBox().getTop() == 75.0f);
        assert (b.getHitBox().getBottom() == 85.0f);

        // Still changing x just for testing purposes
        b.moveUp(new Motion(50, 10));
        b.moveUp(new Motion(100, 10));

//        System.out.println(c.getHitBox().getLeft());
//        System.out.println(c.getHitBox().getRight());
        assert (b.getHitBox().getLeft() == 175.0f);
        assert (b.getHitBox().getRight() == 225.0f);
        assert (b.getHitBox().getTop() == 55.0f);
        assert (b.getHitBox().getBottom() == 65.0f);

    }

    @Test
    public void test_pos_and_id() {
        Barrier b = new Barrier(50, 100, 10, 30);

        assert (b.getPosition().getX() == 50.0f);
        assert (b.getPosition().getY() == 100.0f);

        b.moveUp(new Motion(0, 10));
        b.moveUp(new Motion(0, 10));

        assert (b.getPosition().getX() == 50.0f);
        assert (b.getPosition().getY() == 80.0f);

        b.moveUp(new Motion(30, 10));
        b.moveUp(new Motion(-20, -20));

        assert (b.getPosition().getX() == 60.0f);
        assert (b.getPosition().getY() == 90.0f);

        // id test
        assert (b.getId() == 'b');

    }

}