package com.swe.justslidin;

import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;

import org.junit.Assert;
import org.junit.Test;

public class TestUniverse {

    @Test
    public void test_add_and_step() {
        Universe u = new Universe();
        u.addChar(new Position(0,1));
        u.addChar(new Position(2,1));
        u.step();
    }

}
