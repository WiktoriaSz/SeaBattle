package com.tests;

import com.Player;
import com.SetUp;
import com.Ship;
import org.junit.Test;

import static org.junit.Assert.*;

public class SettingTest {

    @Test
    public void initialSettingTest() {
        Player p1 = new Player();

        Ship ship = new Ship(3, 3, 4, 1);
        SetUp.shipPlacement(p1, ship);

        // are too close and parallel
        assertFalse(SetUp.checkPosition(4, 4, p1.getSea(), 2, 1));
        assertFalse(SetUp.checkPosition(7, 3, p1.getSea(), 1, 1));

        // are further up or along
        assertTrue(SetUp.checkPosition(5, 5, p1.getSea(), 4, 1));
        assertTrue(SetUp.checkPosition(8, 3, p1.getSea(), 1, 1));

        assertEquals(3, p1.getPlayerArmada().get(0).getStartingPositionX());
        assertSame("o", p1.getSea()[3][3]);
        assertSame("o", p1.getSea()[5][3]);
        assertNotSame("o", p1.getSea()[5][4]);

    }

    @Test
    public void virtualSeaSettingTest() {
        Player p2 = new Player();

        SetUp.virtualPlayerSeaSetUp(p2);

        for (Ship ship : p2.getPlayerArmada()) {
            assertTrue(SetUp.checkPosition(
                    ship.getStartingPositionX(), ship.getStartingPositionY(),
                    p2.getSea(), ship.getSize(), ship.getPosition()));
        }

        for (Ship ship : p2.getPlayerArmada()) {
            assertSame("o", p2.getSea()
                    [ship.getStartingPositionX()][ship.getStartingPositionY()]);
        }
    }
}
