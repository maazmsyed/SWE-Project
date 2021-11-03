package com.swe.justslidin;

import com.swe.justslidin.models.Coin;
import com.swe.justslidin.models.Motion;

import org.junit.Test;

public class TestCoin {

    @Test
    public void test_hitBox() {
        Coin c = new Coin(50, 100, 5);

        //Debug
        //System.out.println(c.getHitBox());

        assert (c.getHitBox().getLeft() == 45.0f);
        assert (c.getHitBox().getRight() == 55.0f);
        assert (c.getHitBox().getTop() == 95.0f);
        assert (c.getHitBox().getBottom() == 105.0f);

        // x is 0 because we wouldn't be changing the x-value
        // although y is positive, moveUp is adjusted to go up
        c.moveUp(new Motion(0, 10));
        c.moveUp(new Motion(0, 10));

//        assert (c.getPosition().getX() == 50.0f);
//        assert (c.getPosition().getY() == 80.0f);
        assert (c.getHitBox().getLeft() == 45.0f);
        assert (c.getHitBox().getRight() == 55.0f);
        assert (c.getHitBox().getTop() == 75.0f);
        assert (c.getHitBox().getBottom() == 85.0f);

        c.moveUp(new Motion(50, 10));
        c.moveUp(new Motion(100, 10));

//        System.out.println(c.getHitBox().getLeft());
//        System.out.println(c.getHitBox().getRight());
        assert (c.getHitBox().getLeft() == 195.0f);
        assert (c.getHitBox().getRight() == 205.0f);
        assert (c.getHitBox().getTop() == 55.0f);
        assert (c.getHitBox().getBottom() == 65.0f);

    }

    @Test
    public void test_pos_and_id() {
        Coin c = new Coin(50, 100, 5);

        assert (c.getPosition().getX() == 50.0f);
        assert (c.getPosition().getY() == 100.0f);

        c.moveUp(new Motion(0, 10));
        c.moveUp(new Motion(0, 10));

        assert (c.getPosition().getX() == 50.0f);
        assert (c.getPosition().getY() == 80.0f);

    }

}
