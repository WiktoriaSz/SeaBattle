package tests;

import com.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class GameTest {
    private Player computer;
    private String[][] sea;
    private Player player;
    private Game game;
    private String[][] playerTestSea;

    @Before
    public void setUp() {
        SetUp setUp = new SetUp();
        computer = new Player(false);
        sea = setUp.seaSetUp(computer);
        player = new Player(true);
        game = new Game();
        List<Ship> temporary = new ArrayList<>(Arrays.asList(
                new Ship(6, 6, 4, 2),
                new Ship(6, 3, 3, 1),
                new Ship(2, 5, 3, 2),
                new Ship(0, 2, 2, 1),
                new Ship(4, 1, 2, 2),
                new Ship(9, 7, 2, 2),
                new Ship(9, 0, 1, 2),
                new Ship(4, 5, 1, 2),
                new Ship(1, 0, 1, 2),
                new Ship(0, 5, 1, 2)));
        for (Ship ship : temporary) {
            setUp.shipPlacement(player, ship, player.getSea());
        }
        playerTestSea = player.getSea();

    }

    @Test
    public void attackTestForBigShip() {
        System.out.println(game.board(sea));
        // attack 4-mast ship
        assertTrue(game.attack(computer.getPlayerArmada().get(0).getStartingPositionX(),
                computer.getPlayerArmada().get(0).getStartingPositionY(), player, computer, sea));
        assertSame(Status.DAMAGED, computer.getPlayerArmada().get(0).getStatus());
        System.out.println("Status = " + computer.getPlayerArmada().get(0).getStatus());
        System.out.println(game.board(sea));
    }

    @Test
    public void attackTestForSmallestShip() {
        System.out.println(game.board(sea));
        assertTrue(game.attack(computer.getPlayerArmada().get(9).getStartingPositionX(),
                computer.getPlayerArmada().get(9).getStartingPositionY(), player, computer, sea));
        assertSame(Status.SUNK, computer.getPlayerArmada().get(9).getStatus());
        System.out.println("Status = " + computer.getPlayerArmada().get(9).getStatus());

    }

    @Test
    public void attackTestOnPlayerSeaSuccess() {
        assertTrue(game.attack(7, 3, computer, player, playerTestSea));
        assertSame(Status.DAMAGED, player.searchByPosition(7, 3).getStatus());
        System.out.println("Status = " + player.searchByPosition(7, 3).getStatus());
        System.out.println(game.board(playerTestSea));
    }

    @Test
    public void attackTestOnPlayerSeaFailed() {
        assertSame(null, playerTestSea[1][6]);
        assertFalse(game.attack(6, 1, computer, player, playerTestSea));
        assertSame(".", playerTestSea[1][6]);
        System.out.println(game.board(playerTestSea));
    }

    @Test
    public void attackTestOnPlayerSeaRepeatedPlaceOfAttack() {
        playerTestSea[1][6] = ".";
        assertTrue(game.attack(6, 1, computer, player, playerTestSea));
        playerTestSea[8][4] = "X";
        assertTrue(game.attack(4, 8, computer, player, playerTestSea));
        System.out.println(game.board(playerTestSea));
    }
}

// todo:
//    @Test
//    public void ameSequenceTest() {
//        game.gameSequence(player, computer);
//    }
//}
