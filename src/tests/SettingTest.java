package tests;

import com.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SettingTest {

    private String[][] sea;
    private Game game;
    private Check check;
    private Player p2;

    @Before
    public void setUp(){
        check = new Check();
        p2 = new Player(false);
        SetUp setUp = new SetUp();
        sea = setUp.seaSetUp(p2);
        game = new Game();
    }

    @Test
    public void seaArraySettingTest() {

        System.out.println(game.board(sea));
        int x = p2.getPlayerArmada().get(1).getStartingPositionX();
        int y = p2.getPlayerArmada().get(1).getStartingPositionY();
        System.out.println("x = " + x + "  y = " + y);
        for (Ship ship : p2.getPlayerArmada()) {
            assertSame("o", sea
                    [ship.getStartingPositionY()][ship.getStartingPositionX()]);
        }
    }

    @Test
    public void seaSettingTest2() {
        System.out.println("Armada's size = " + p2.getPlayerArmada().size());
        System.out.println(game.board(sea));

        for (Ship ship : p2.getPlayerArmada()) {
            int x = ship.getStartingPositionX();
            int y = ship.getStartingPositionY();
            int size = ship.getSize();
            int position = ship.getPosition();
            System.out.println("x = " + x + "  y = " + y + "  size = " + size + "  position = " + position);
            if (position == 1) {
                assertTrue(check.checkAroundPlacementOfHorizontalShip(x, y, sea, size));
            } else {
                assertTrue(check.checkAroundPlacementOfVerticalShip(x, y, sea, size));
            }
        }
    }

}
