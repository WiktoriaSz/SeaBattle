package com.tests;


import com.Player;
import com.SetUp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettingTest {

    Player p1 = new Player();

    @Before
    public void beforeTest() {
        SetUp.shipPlacement(3, 3, p1.getSea(), 4, 1, p1);
    }

    @Test
    public void testSetting() {
        // are too close and parallel
        assertFalse(SetUp.checkPosition(4, 4, p1.getSea(), 2, 1));
        assertFalse(SetUp.checkPosition(7, 3, p1.getSea(), 1, 1));

        // are further up or along
        assertTrue(SetUp.checkPosition(5, 5, p1.getSea(), 4, 1));
        assertTrue(SetUp.checkPosition(8, 3, p1.getSea(), 1, 1));

    }


}
