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

        game.attack(4, 1, computer, player, playerTestSea);
        game.attack(4, 4, computer, player, playerTestSea);
        game.attack(2, 7, computer, player, playerTestSea);
        game.attack(3, 7, computer, player, playerTestSea);
        game.attack(0, 2, computer, player, playerTestSea);
        game.attack(0, 3, computer, player, playerTestSea);
        game.attack(7, 3, computer, player, playerTestSea);
        game.attack(8, 3, computer, player, playerTestSea);
        game.attack(9, 8, computer, player, playerTestSea);
        game.attack(9, 9, computer, player, playerTestSea);
        game.attack(6, 5, computer, player, playerTestSea);
        game.attack(7, 6, computer, player, playerTestSea);
    }

    @Test
    public void attackTestForBigShip() {
        System.out.println("****************************************************************************");
        // attack 4-mast ship
        assertTrue(game.attack(computer.getPlayerArmada().get(0).getStartingPositionX(),
                computer.getPlayerArmada().get(0).getStartingPositionY(), player, computer, sea));
        assertSame(Status.DAMAGED, computer.getPlayerArmada().get(0).getStatus());
        System.out.println("Status = " + computer.getPlayerArmada().get(0).getStatus());
        System.out.println(game.board(sea));
    }

    @Test
    public void attackTestForSmallestShip() {
        System.out.println("****************************************************************************");
        System.out.println(game.board(sea));
        assertTrue(game.attack(computer.getPlayerArmada().get(9).getStartingPositionX(),
                computer.getPlayerArmada().get(9).getStartingPositionY(), player, computer, sea));
        assertSame(Status.SUNK, computer.getPlayerArmada().get(9).getStatus());
        System.out.println("Status = " + computer.getPlayerArmada().get(9).getStatus());

    }

    @Test
    public void attackTestOnPlayerSeaSuccess() {
        System.out.println("****************************************************************************");
        assertTrue(game.attack(7, 3, computer, player, playerTestSea));
        assertSame(Status.DAMAGED, player.searchByPosition(7, 3).getStatus());
        System.out.println("Status = " + player.searchByPosition(7, 3).getStatus());
        System.out.println(game.board(playerTestSea));
    }

    @Test
    public void attackTestOnPlayerSeaFailed() {
        System.out.println("****************************************************************************");
        assertSame(null, playerTestSea[1][6]);
        assertFalse(game.attack(6, 1, computer, player, playerTestSea));
        assertSame(".", playerTestSea[1][6]);
        System.out.println(game.board(playerTestSea));
    }

    @Test
    public void attackTestOnPlayerSeaRepeatedPlaceOfAttack() {
        System.out.println("****************************************************************************");
        playerTestSea[1][6] = ".";
        assertTrue(game.attack(6, 1, computer, player, playerTestSea));
        playerTestSea[8][4] = "X";
        assertTrue(game.attack(4, 8, computer, player, playerTestSea));
        System.out.println(game.board(playerTestSea));
    }

    @Test
    public void advancedComputerAttackTest_AttackLeft() {
        System.out.println("*****************FAILED*************************************************");
        assertFalse(game.advancedComputerAttack(player, computer, computer.getAttackBoard()));
    }

//    @Test
//    public void advancedComputerAttackTest_AttackDown() {
//        game.attack(5, 1, computer, player, playerTestSea);
//        System.out.println("****************************************************************************");
//        assertTrue(game.advancedComputerAttack(player, computer, computer.getAttackBoard()));
//    }
//
//    private void secondarySetUp() {
//        game.attack(5, 1, computer, player, playerTestSea);
//        game.attack(4, 2, computer, player, playerTestSea);
//        game.attack(1, 2, computer, player, playerTestSea);
//    }
//
//    @Test
//    public void advancedComputerAttackTest_AttackRight() {
//        secondarySetUp();
//        System.out.println("****************************************************************************");
//        assertTrue(game.advancedComputerAttack(player, computer, computer.getAttackBoard()));
//    }
//
//    @Test
//    public void advancedComputerAttackTest_AttackRandomDirection() {
//        secondarySetUp();
//        game.attack(6, 3, computer, player, playerTestSea);
//        game.attack(2, 8, computer, player, playerTestSea);
//        System.out.println("****************************************************************************");
//        boolean condition = false;
//        game.advancedComputerAttack(player, computer, computer.getAttackBoard());
//        if (playerTestSea[6][2].equals("X") || playerTestSea[7][1].equals(".")) {
//            condition = true;
//        }
//        assertTrue(condition);
//    }
//
//    private void tertiarySetUp() {
//        secondarySetUp();
//        game.attack(6, 3, computer, player, playerTestSea);
//        game.attack(2, 8, computer, player, playerTestSea);
//        game.attack(2, 6, computer, player, playerTestSea);
//    }
//
//    @Test
//    public void advancedComputerAttackTest_AttackUp() {
//        tertiarySetUp();
//        System.out.println("****************************************************************************");
//        game.advancedComputerAttack(player, computer, computer.getAttackBoard());
////        assertTrue(game.advancedComputerAttack(player, computer, computer.getAttackBoard()));
//    }
//
//    @Test
//    public void advancedComputerAttackTest_AttackNext() {   // todo: poprawić, źle działa metoda!!!
//        tertiarySetUp();
//        game.attack(2, 5, computer, player, playerTestSea);
//        System.out.println("****************************************************************************");
//        assertFalse(game.advancedComputerAttack(player, computer, computer.getAttackBoard()));
//    }

}

// todo:
//    @Test
//    public void ameSequenceTest() {
//        game.gameSequence(player, computer);
//    }
//}
