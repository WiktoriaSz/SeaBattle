package tests;

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
    private String[][] sea = setUp.seaSetUp(p2);

    @Test
    public void seaArraySettingTest() {

        System.out.println(Arrays.deepToString(sea)
                .replace("], ", "]\n").replace("o, ", "o   , "));
        int x = p2.getPlayerArmada().get(1).getStartingPositionX();
        int y = p2.getPlayerArmada().get(1).getStartingPositionY();
        System.out.println("x = " + x + "  y = " + y);
        for (Ship ship : p2.getPlayerArmada()) {
            assertSame("o", sea
                    [ship.getStartingPositionY()][ship.getStartingPositionX()]);
        }
    }

    @Test
    public void seaSettingTest2() { // todo in some cases it lags
        System.out.println("Armada's size = " + p2.getPlayerArmada().size());
        System.out.println(Arrays.deepToString(sea)
                .replace("], ", "]\n").replace("o, ", "o   , "));

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
