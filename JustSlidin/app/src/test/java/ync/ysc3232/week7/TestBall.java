package ync.ysc3232.week7;

import org.junit.Assert;
import org.junit.Test;

import ync.ysc3232.week7.models.Ball;
import ync.ysc3232.week7.models.Motion;
import ync.ysc3232.week7.models.Position;

public class TestBall {
    @Test
    public void test_add() {
        Ball b = new Ball(0,0);
        b.move(new Motion(0,1));
        Assert.assertEquals(new Position(0,1), b.getPosition());
    }
}
