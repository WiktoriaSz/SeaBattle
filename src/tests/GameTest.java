package tests;

import com.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private SetUp setUp = new SetUp();
    private Player computer = new Player(false);
    private String[][] sea = setUp.seaSetUp(computer);
    private Player player = new Player(true);
    private Game game = new Game();


    @Test
    public void attackTestForBigShip() {
        System.out.println(game.board(sea));
        // attack 4-mast ship
        assertTrue(game.attack(computer.getPlayerArmada().get(0).getStartingPositionX(),
                computer.getPlayerArmada().get(0).getStartingPositionY(), player, computer));
        assertSame(Status.DAMAGED, computer.getPlayerArmada().get(0).getStatus());
        System.out.println("Status = " + computer.getPlayerArmada().get(0).getStatus());
        System.out.println(game.board(sea));
    }

    @Test
    public void attackTestForSmallestShip() {
        System.out.println(game.board(sea));
        assertTrue(game.attack(computer.getPlayerArmada().get(9).getStartingPositionX(),
                computer.getPlayerArmada().get(9).getStartingPositionY(), player, computer));
        assertSame(Status.SUNK, computer.getPlayerArmada().get(9).getStatus());
        System.out.println("Status = " + computer.getPlayerArmada().get(9).getStatus());

    }
}
