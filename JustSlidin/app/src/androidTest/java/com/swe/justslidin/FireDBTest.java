package com.swe.justslidin;

import com.google.firebase.database.FirebaseDatabase;
import com.swe.justslidin.models.Position;

import org.junit.Assert;
import org.junit.Test;

public class FireDBTest {
    @Test
    public void test_FireDB(){
        FireDB.setPlayerPos("Danny", new Position(540,700));
        Position p = FireDB.getPlayerPos("Danny");
        Assert.assertEquals(700f,p.getY());
        Assert.assertEquals(540f,p.getX());


    }
}
