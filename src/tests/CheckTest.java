package tests;

import com.Check;
import com.Player;
import com.SetUp;
import com.Ship;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckTest {

    private Check check;
    private Player p1;
    private Ship ship;

    @Before
    public void setUp(){
        check = new Check();
        p1 = new Player(true);
        SetUp setUp = new SetUp();
        ship = new Ship(2, 3, 4, 1);
        p1.setShip(ship);
        setUp.shipPlacement(p1, ship, p1.getSea());
    }

    @Test
    public void initialPositioningTest() {

        //ship placement
        assertEquals(2, p1.getPlayerArmada().get(0).getStartingPositionX());
        assertSame("o", p1.getSea()[3][3]);
        assertSame("o", p1.getSea()[3][5]);
        assertNotSame("o", p1.getSea()[4][5]);

        //sea boundary
        assertTrue(check.checkIfWithinSeaBoundary(ship.getStartingPositionX(), ship.getStartingPositionY(),
                p1.getSea(), ship.getSize(), ship.getPosition()));
        assertFalse(check.checkIfWithinSeaBoundary(9, 9, p1.getSea(), 3, 1));

        //viable position
        assertTrue(check.checkIfExactPositionIsViable(1, 6, p1.getSea(), 3, 1));
        assertFalse(check.checkIfExactPositionIsViable(5, 2, p1.getSea(), 3, 2));
    }

    @Test
    public void surroundingPositionsTest() {

        //adjacent position
        assertTrue(check.checkAroundPlacementOfVerticalShip(2, 5, p1.getSea(), 2));
        assertFalse(check.checkAroundPlacementOfVerticalShip(5, 1, p1.getSea(), 3));
        assertFalse(check.checkAroundPlacementOfHorizontalShip(6,3, p1.getSea(), 1));
        assertFalse(check.checkAroundPlacementOfHorizontalShip(0,4, p1.getSea(), 3));
    }

}
