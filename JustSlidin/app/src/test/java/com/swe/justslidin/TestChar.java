package com.swe.justslidin;

import com.swe.justslidin.models.Character;

import org.junit.Assert;
import org.junit.Test;


public class TestChar {
    @Test
    //TODO: MORE TESTS
    public void test_add() {
        //System.out.println(u.getPosition());
        Character b = new Character(540, 100, 50f);
        b.moveLeft(10f);
        System.out.println(b.toString());
        //System.out.println(u.getPosition());
        b.moveRight(10f);
        System.out.println(b.toString());
        //System.out.println(u.getPosition());
    }
}

