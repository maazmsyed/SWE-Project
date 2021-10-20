package com.swe.justslidin;

import com.swe.justslidin.models.Position;
import com.swe.justslidin.models.Universe;

import org.junit.Assert;
import org.junit.Test;

public class TestUniverse {
    @Test
    public void test_add_and_step() {
        //TODO: TEST DOESN'T WORK
        Universe u = new Universe();
        System.out.println(u.getPosition());
        u.moveCharLeft();
        System.out.println(u.getPosition());
        u.moveCharRight();
        System.out.println(u.getPosition());
        u.step();
        }
    }

