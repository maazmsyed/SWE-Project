package com.swe.justslidin;

import com.swe.justslidin.models.Character;
import com.swe.justslidin.models.Motion;
import com.swe.justslidin.models.Position;

import org.junit.Assert;
import org.junit.Test;


public class TestChar {

    @Test
    public void test_add() {
        Character b = new Character(0,0);
        b.move(new Motion(0,1));
        Assert.assertEquals(new Position(0,1), b.getPosition());
    }

}
