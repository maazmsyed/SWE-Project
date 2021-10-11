package ync.ysc3232.week7;

import org.junit.Assert;
import org.junit.Test;

import ync.ysc3232.week7.models.Ball;
import ync.ysc3232.week7.models.Position;
import ync.ysc3232.week7.models.Universe;

public class TestUniverse {
    @Test
    public void test_add_and_step() {
        Universe u = new Universe();
        u.addBall(new Position(0,1));
        u.addBall(new Position(2,1));
        u.step();
    }
}
