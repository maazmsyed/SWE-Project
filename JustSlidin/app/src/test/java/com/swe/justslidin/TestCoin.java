package com.swe.justslidin;

import com.swe.justslidin.models.Coin;
import com.swe.justslidin.models.Motion;

import org.junit.Test;

public class TestCoin {

    @Test
    public void test_pos_and_hitBox() {
        Coin c = new Coin(50, 100, 5);

        //Debug
        //System.out.println(c.getHitBox());

        assert(c.getHitBox().getLeft() == 45.0f);
        assert(c.getHitBox().getRight() == 55.0f);
        assert(c.getHitBox().getTop() == 95.0f);
        assert(c.getHitBox().getBottom() == 105.0f);

        c.moveUp(new Motion(0, 10));
        c.moveUp(new Motion(0, 10));

        assert(c.getHitBox().getLeft() == 45.0f);
        assert(c.getHitBox().getRight() == 55.0f);
        assert(c.getHitBox().getTop() == 75.0f);
        assert(c.getHitBox().getBottom() == 85.0f);

    }

}
