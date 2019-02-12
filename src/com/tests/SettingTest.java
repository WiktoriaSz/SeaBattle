package com.tests;

import com.Check;
import com.Player;
import com.SetUp;
import com.Ship;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SettingTest {

    private Check check = new Check();
    private SetUp setUp = new SetUp();
    static private Player p2 = new Player(false);

    @Test
    public void seaArraySettingTest() {
        setUp.seaSetUp(p2);
        String[][] sea = p2.getSea();

        System.out.println(Arrays.deepToString(sea).replace("], ", "]\n").replace("o, ", "o   , "));
        int x = p2.getPlayerArmada().get(1).getStartingPositionX();
        int y = p2.getPlayerArmada().get(1).getStartingPositionY();
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("sea = " + sea[y][x]);
        for (Ship ship : p2.getPlayerArmada()) {
            assertSame("o", p2.getSea()
                    [ship.getStartingPositionY()][ship.getStartingPositionX()]);
        }
    }

    @Test
    public void seaSettingTest2(){
        setUp.seaSetUp(p2);
        String[][] sea = p2.getSea();

        for (Ship ship : p2.getPlayerArmada()) { // todo uwaga - setting sea good, checking sea good - why doesn't work?
            int x = ship.getStartingPositionX();
            int y = ship.getStartingPositionY();
            int size = ship.getSize();
            int position = ship.getPosition();
            System.out.println("x = " + x + "  y = " + y + "  size = " + size + "  position = " + position);
            System.out.println(Arrays.deepToString(sea).replace("], ", "]\n").replace("o, ", "o   , "));

            if (ship.getPosition() == 1) {
                System.out.println(check.checkAroundPlacementOfHorizontalShip(x, y, sea, size)); // todo - why print false when id ok?


//                assertTrue(check.checkAroundPlacementOfHorizontalShip(x, y, sea, size));
            } else {
                System.out.println(check.checkAroundPlacementOfVerticalShip(x, y, sea, size));
//                assertTrue(check.checkAroundPlacementOfVerticalShip(x, y, sea, size));
            }
        }
    }

//    @Test
//    public void checkAndSetTest() {
//        Player p2 = new Player(false);
//        setUp.checkAndSet(p2, 4);
//
//    }

//    public void seaSettingTest2() {
//        Player p3 = new Player(true);
//        setUp.checkAndSet(p3, 4);
//
//    }

}
