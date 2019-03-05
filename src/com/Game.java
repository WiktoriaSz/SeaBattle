package com;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    /**
     * Main attack method taking parameters from player or computer inputs.
     *
     * @param x        is a horizontal placement, latitude of String object on a game board - a String array.
     * @param y        is a vertical placement, longitude of String object on a game board - a String array.
     * @param attacker is a Player object representing real or computer player.
     * @param enemy    is a second Player object representing real or computer player.
     * @param enemySea is a personal game board - String array, a field - of attacked player.
     * @return depends on value of String object indicated by x and y.
     * - If that String has null value return will be Boolean false. Attack will be notified on attacker's
     * attack board as missed - "." and attack board will be presented.
     * - If that String has "." value it means that place was already attacked earlier
     * and current attack is invalid. Return will be Boolean true and repeated attack will be allowed.
     * Attack board will be presented if attacker is a human player.
     * - If that String has value other than null or "." it means attack was successful.
     * Attack will be notified and shown on the attacker's attack board and Ship object holding that BodyPosition
     * will be evaluated for it's status, which will be changed to DAMAGED or SUNK and corresponding output will be printed.
     * After this winningCondition method will be invoked to determine if the game will continue.
     * If winning condition was met return will be null. If not the return will be true and another attack will be allowed.
     * @see com.Game#attackMapNotation(int, int, String[][], String[][], boolean)
     * @see com.Game#showAttackView(int, int, String, String, int)
     * @see com.Game#shipCondition(Ship)
     * @see com.Game#winningCondition(Player, Player)
     */
    public Boolean attack(int x, int y, Player attacker, Player enemy, String[][] enemySea) {
        if (enemySea[y][x] == null) {
            attackMapNotation(x, y, attacker.getAttackBoard(), enemySea, true);
            showAttackView(x, y, attacker.getName(), board(attacker.getAttackBoard()), 1);
            return false;

        } else if ((enemySea[y][x].equals(".")) || (enemySea[y][x].equals("X"))) {
            if (attacker.isWhichPlayer()) {
                showAttackView(x, y, attacker.getName(), board(attacker.getAttackBoard()), 2);
            }
            return true;

        } else {
            attackMapNotation(x, y, attacker.getAttackBoard(), enemySea, false);
            showAttackView(x, y, attacker.getName(), board(attacker.getAttackBoard()), 3);
            shipCondition(consequencesOfSuccessfulAttack(x, y, enemy));

            if (winningCondition(attacker, enemy)) {
                return null;
            } else {
                return true;
            }
        }
    }

    /**
     * A support method for shipCondition. It uses another method to search for an object holding
     * BodyPosition object with such parameters and when found changes that BodyPosition object's Status to DAMAGED.
     *
     * @param x     one of BodyPosition's field.
     * @param y     second of BodyPosition's field.
     * @param enemy the attacked Player object.
     * @return is a Ship object holding a BodyPosition object with x and y parameters.
     * @see com.Game#shipCondition(Ship)
     * @see com.Player#searchByPosition(int, int)
     */
    private Ship consequencesOfSuccessfulAttack(int x, int y, Player enemy) {
        Ship ship = enemy.searchByPosition(x, y);
        for (BodyPosition placement : ship.getFullPlacement()) {
            if (placement.getX() == x && placement.getY() == y) {
                placement.setStatus(Status.DAMAGED);
            }
        }
        return ship;
    }

    /**
     * A method for changing a String object's state according to the result of main attack method
     * in two corresponding arrays - game boards.
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param attackBoard attacking Player's view of second Player's personal board - sea.
     * @param enemySea    attacked Player's personal sea board.
     * @param missed      boolean variable passed from main attack method.
     * @see com.Game#attack(int, int, Player, Player, String[][])
     */
    private void attackMapNotation(int x, int y, String[][] attackBoard, String[][] enemySea, boolean missed) {
        if (missed) {
            attackBoard[y][x] = ".";
            enemySea[y][x] = ".";
        } else {
            attackBoard[y][x] = "X";
            enemySea[y][x] = "X";
        }
    }

    /**
     * A method gathering various outputs printed according to switch value.
     *
     * @param x             latitude of String object in an array matrix.
     * @param y             longitude of String object in an array matrix.
     * @param attackersName name of attacking Player object.
     * @param board         an array translated to string.
     * @param switchNumber  parameter of chosen print output.
     */
    private void showAttackView(int x, int y, String attackersName, String board, int switchNumber) {
        switch (switchNumber) {
            case 1:
                System.out.println("\nPlansza ataku " + attackersName + "a: \n" + board);
                System.out.println("Atakuje " + attackersName + ", pozycje x = " + x + ", y = " + y + " : Pudło!");
                break;
            case 2:
                System.out.println("\nTa pozycja była zaatakowana już wcześniej. Spróbuj jeszcze raz.");
                break;
            case 3:
                System.out.println("\nPlansza ataku " + attackersName + "a: \n" + board);
                System.out.print("Atakuje " + attackersName + ", pozycje x = " + x + ", y = " + y + " : ");
                break;
        }
    }

    /**
     * Standard input for human player attack. The input is passed to the main attack method and evaluated.
     *
     * @param player   a Player object representing the human player.
     * @param computer a Player object representing the computer player.
     * @return delegated to the main attack method.
     * @see com.Game#attack(int, int, Player, Player, String[][])
     */
    public Boolean playerAttack(Player player, Player computer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPodaj pozycję poziomą (x) od 0 do 9.");
        int x = scanner.nextInt();
        System.out.println("Podaj pozycję pionową (y) od 0 do 9.");
        int y = scanner.nextInt();
        return attack(x, y, player, computer, computer.getSea());
    }

    /**
     * A method responsible for managing the attack sequence for a whole game.
     *
     * @param player   a Player object representing the human player.
     * @param computer a Player object representing the computer player.
     */
    public void gameSequence(Player player, Player computer) {
        Boolean attackOrder = true;

        while (!winningCondition(player, computer) || !winningCondition(computer, player)) {

            while (attackOrder) {
                attackOrder = playerAttack(player, computer);
            }

            attackOrder = computerAttack(player, computer);
            if (attackOrder) {
                while (attackOrder) {
                    attackOrder = advancedComputerAttack(player, computer, computer.getAttackBoard());
                }
            }
        }
    }

    /**
     * A method responsible for changing a state of BodyPosition objects and a Ship object.
     * Proper response is then printed.
     *
     * @param ship Ship object whose State is changed according to successful attack and its previous state.
     * @see com.Game#attack(int, int, Player, Player, String[][])
     * @see com.Ship
     * @see com.BodyPosition
     * @see com.Status
     */
    private void shipCondition(Ship ship) {
        int damaged = 0;
        for (BodyPosition b : ship.getFullPlacement()) {
            if (b.getStatus() == Status.DAMAGED) {
                ++damaged;
            }
        }
        if (damaged == ship.getSize()) {
            ship.setStatus(Status.SUNK);
            System.out.println("Zatopiony!");
        }
        if (damaged < ship.getSize()) {
            ship.setStatus(Status.DAMAGED);
            System.out.println("Trafiony!");
        }
    }

    /**
     * A method for evaluating the game status and bringing its end if conditions are met.
     * If a player is a human player there will be printed a proper notification upon the end of the game.
     *
     * @param attacker a Player object currently attacking.
     * @param enemy    a Player object whose sea currently is being attacked.
     * @return a boolean value representing continuation or end of the game.
     * @see com.Game#attack(int, int, Player, Player, String[][])
     * @see com.Game#gameSequence(Player, Player)
     */
    private boolean winningCondition(Player attacker, Player enemy) {
        int sunk = 0;
        for (Ship ship : enemy.getPlayerArmada()) {
            if (ship.getStatus() == Status.SUNK) {
                ++sunk;
            }
        }
        if (sunk == enemy.getPlayerArmada().size()) {
            if (attacker.isWhichPlayer()) {
                System.out.println("Gratulacje! Wygrałeś!");
            } else {
                System.out.println("Przegrałeś. Koniec gry.");
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method translating a String array into readable String object.
     *
     * @param sea String array - a game board.
     * @return readable to human player String object.
     */
    public String board(String[][] sea) {
        int y = 0;
        StringBuilder temporBoard = new StringBuilder();
        for (String[] row : sea) {
            temporBoard = temporBoard.append(y).append("y ").append(Arrays.toString(row))
                    .append(" ").append(y++).append("y\n");
        }
        String board = temporBoard.toString()
                .replace("], ", "]\n")
                .replace("[[", "[")
                .replace("]]", "]")
                .replace("null", "---")
                .replace("o, ", " o , ")
                .replace("o]", " o ]")
                .replace("., ", " . , ")
                .replace(".]", " . ]")
                .replace("X]", " X ]")
                .replace("X, ", " X , ");

        return "    0x,  1x,  2x,  3x,  4x,  5x,  6x,  7x,  8x,  9x \n" + board +
                "    0x,  1x,  2x,  3x,  4x,  5x,  6x,  7x,  8x,  9x";
    }

    /**
     * The simplest of computer attack. Randomised parameters passed to main attack method.
     *
     * @param player   a Player object representing the human player.
     * @param computer a Player object representing the computer player.
     * @return delegated to the main attack method.
     * @see com.Game#attack(int, int, Player, Player, String[][])
     */
    public Boolean computerAttack(Player player, Player computer) {
        Random random = new Random();
        int x = random.nextInt(computer.getSea().length);
        int y = random.nextInt(computer.getSea().length);
        return attack(x, y, computer, player, player.getSea());
    }

    //*******************************************************************************************************
    //*******************************************************************************************************
    //*******************************************************************************************************
    public Boolean advancedComputerAttack(Player player, Player computer, String[][] attackBoard) {
        for (int i = 0; i < attackBoard.length; i++) {
            for (int j = 0; j < attackBoard[i].length; j++) {
                if (attackBoard[i][j] == null || attackBoard[i][j].equals(".")) {
                    continue;
                }
                if (attackBoard[i][j].equals("X") && (player.searchByPosition(j, i).getStatus() != Status.SUNK)) {
                    int switchInput = switchInputDependingOnSituationAround(j, i, attackBoard);
                    if (switchInput > 7) {
                        continue;
                    }
                    return switchRedirectionForComputerAttack(j, i, attackBoard, player, computer, switchInput);
                }
            }
        }
        return computerAttack(player, computer);
    }

    /**
     * The method is one of support methods to the advancedComputerAttack.
     * It checks if places up and to the left of previous successful attack are free and if so
     * it chooses random direction and passes its parameters to the main attack method.
     * If one of directions isn't free or is outside of the board it chooses the other direction to attack.
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param attackBoard attacking Player's (computer's) view of second Player's personal board - sea.
     * @param player      a Player object representing the human player.
     * @param computer    a Player object representing the computer player.
     * @return redirected to the advancedComputerAttack method and further to the main attack method.
     * @see com.Game#attack(int, int, Player, Player, String[][])
     * @see com.Game#advancedComputerAttack(Player, Player, String[][])
     */
    private Boolean randomDirectionAttack(int x, int y, String[][] attackBoard, Player player, Player computer) {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            System.out.println("Random atak na = " + attackBoard[y - 1][x] + ", x = " + x + ", y = " + (y - 1));
            return attack(x, (y - 1), computer, player, player.getSea());
        } else {
            System.out.println("Random atak na = " + attackBoard[y][x - 1] + ", x = " + (x - 1) + ", y = " + y);
            return attack((x - 1), y, computer, player, player.getSea());
        }
    }

    // todo: podzielić na 2
//    private Boolean directedComputerAttack(int x, int y, String[][] attackBoard, Player player, Player computer, int switchInput) {
//        if (switchInput == 6 || switchInput == 12) {
//            if ((y - 1) > 0 && attackBoard[y - 1][x] == null) {
//                System.out.println("Directed atak na = " + attackBoard[y - 1][x] + ", x = " + x +
//                        ", y = " + (y - 1));
//                return attack(x, (y - 1), computer, player, player.getSea());
//            } else {
//                if (attackBoard[y + 2][x] == null) {
//                    System.out.println("Directed atak na = " + attackBoard[y + 2][x] + ", x = " + x +
//                            ", y = " + (y + 2));
//                    return attack(x, (y + 2), computer, player, player.getSea());
//                } else if (attackBoard[y + 2][x].equals("X")) {
//                    System.out.println("Directed atak na = " + attackBoard[y + 3][x] + ", x = " + x +
//                            ", y = " + (y + 3));
//                    return attack(x, (y + 3), computer, player, player.getSea());
//                }
//            }
//        } else if (switchInput == 7 || switchInput == 11) {
//            if ((x - 1) > 0 && attackBoard[y][x - 1] == null) {
//                System.out.println("Directed atak na = " + attackBoard[y][x - 1] + ", x = " + (x - 1) +
//                        ", y = " + y);
//                return attack((x - 1), y, computer, player, player.getSea());
//            } else {
//                if (attackBoard[y][x + 2] == null) {
//                    System.out.println("Directed atak na = " + attackBoard[y][x + 2] + ", x = " + (x + 2) +
//                            ", y = " + y);
//                    return attack((x + 2), y, computer, player, player.getSea());
//                } else if (attackBoard[y][x + 2].equals("X")) {
//                    System.out.println("Directed atak na = " + attackBoard[y][x + 3] + ", x = " + (x + 3) +
//                            ", y = " + y);
//                    return attack((x + 3), y, computer, player, player.getSea());
//                }
//            }
//        }
//        return randomDirectionAttack(x, y, attackBoard, player, computer);
//    }

    /**
     * A method responsible for checking the position up of the indicated. Part of grater method
     * switchInputDependingOnSituationAround
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param attackBoard attacking Player's (computer's) view of second Player's personal board - sea.
     * @return Is a number for further switch
     * @see com.Game#switchInputDependingOnSituationAround
     */
    private int checkUp(int x, int y, String[][] attackBoard) { // trzeba poprawić
        int check = 0;
        if (y - 1 < 0) { // x is beside the end of board
            check += 22;
        } else if (x - 1 == 0) {
            if (attackBoard[y - 1][x] == null) {
                check += 31;
            } else if (attackBoard[y - 1][x].equals("X")) {
                check += 53;
            } else if (attackBoard[y - 1][x].equals(".")) {
                check += 62;
            }
        }
        return check;
    }

    /**
     * A method responsible for checking the position to the right of indicated. Part of grater method
     * switchInputDependingOnSituationAround
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param attackBoard attacking Player's (computer's) view of second Player's personal board - sea.
     * @return Is a number for further switch
     * @see com.Game#switchInputDependingOnSituationAround
     */
    private int checkRight(int x, int y, String[][] attackBoard) {
        int check = 0;
        if (x + 1 == attackBoard.length) {
            check += 15;
        } else if (x + 1 < attackBoard.length) {
            if (attackBoard[y][x + 1] == null) {
                check += 1;
            } else if (attackBoard[y][x + 1].equals("X")) {
                check += 4;
            } else if (attackBoard[y][x + 1].equals(".")) {
                check += 7;
            }
        }
        return check;
    }

    /**
     * A method responsible for checking the position down of indicated. Part of grater method
     * switchInputDependingOnSituationAround
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param attackBoard attacking Player's (computer's) view of second Player's personal board - sea.
     * @return Is a number for further switch
     * @see com.Game#switchInputDependingOnSituationAround
     */
    private int checkDown(int x, int y, String[][] attackBoard) {
        int check = 0;
        if (y + 1 == attackBoard.length) {
            check += 20;
        } else if (y + 1 < attackBoard.length) {
            if (attackBoard[y + 1][x] == null) {
                check += 3;
            } else if (attackBoard[y + 1][x].equals("X")) {
                check += 5;
            } else if (attackBoard[y + 1][x].equals(".")) {
                check += 10;
            }
        }
        return check;
    }

    /**
     * A method responsible for checking the position to the left of indicated. Part of grater method
     * switchInputDependingOnSituationAround
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param attackBoard attacking Player's (computer's) view of second Player's personal board - sea.
     * @return Is a number for further switch
     * @see com.Game#switchInputDependingOnSituationAround
     */
    private int checkLeft(int x, int y, String[][] attackBoard) {
        int check = 0;
        if (x - 1 < 0) { // x is beside the end of board
            check += 4;
        } else if (x - 1 == 0) {
            if (attackBoard[y][x - 1] == null) {
                check += 36;
            } else if (attackBoard[y][x - 1].equals("X")) {
                check += 59;
            } else if (attackBoard[y][x - 1].equals(".")) {
                check += 82;
            }
        }
        return check;
    }

    private int switchInputDependingOnSituationAround(int x, int y, String[][] attackBoard) {
        int check = (checkRight(x, y, attackBoard)) + (checkDown(x, y, attackBoard));

        if (check == 21 || check == 4 || check == 11) {
            return 1;  // attack ->
        } else if (check == 10 || check == 18) {
            return 2; // attack down
        } else {
            check += checkUp(x, y, attackBoard);
            if (check == 47 || check == 39 || check == 97 || check == 87 || check == 89 || check == 79) {
                return 3; // attack <-
            } else if (check == 51 || check == 37 || check == 43) {
                return 4; // attack up
            }
        }
        check += checkLeft(x, y, attackBoard);
        if (check == 91 || check == 74 || check == 81 || check == 122 || check == 112 || check == 65
                || check == 105 || check == 72) {
            return 5; // attack <-
        } else if (check == 148 || check == 130 || check == 52 || check == 140 || check == 62 || check == 138) {
            return 6; // attack up
        } else if (check == 92 || check == 94 || check == 84 || check == 102) {
            return 7;
        }
        return 8;
    }

    /**
     * Method redirecting attack to nearby positions depending on switchInput.
     *
     * @param x           latitude of String object in an array matrix.
     * @param y           longitude of String object in an array matrix.
     * @param player      a Player object representing the human player.
     * @param computer    a Player object representing the computer player.
     * @param attackBoard attacking Player's (computer's) view of second Player's personal board - sea.
     * @param switchInput input from switchInputDependingOnSituationAround method.
     * @return redirects to main attack method with proper directions.
     * @see com.Game#switchInputDependingOnSituationAround
     */
    // todo: potem usunąć sout i attackBoard z parametrów, dokończyć return
    private Boolean switchRedirectionForComputerAttack(int x, int y, String[][] attackBoard,
                                                       Player player, Player computer, int switchInput) {
        if (switchInput == 1) {
            System.out.println("atak na = " + attackBoard[y][x + 1] + ", x = " + (x + 1) + ", y = " + y);
            return attack((x + 1), y, computer, player, player.getSea());
        } else if (switchInput == 2) {
            System.out.println("atak na = " + attackBoard[y + 1][x] + ", x = " + x + ", y = " + (y + 1));
            return attack(x, (y + 1), computer, player, player.getSea());
        } else if (switchInput == 3 || switchInput == 5) {
            System.out.println("atak na = " + attackBoard[y][x - 1] + ", x = " + (x - 1) + ", y = " + y);
            return attack((x - 1), y, computer, player, player.getSea());
        } else if (switchInput == 4 || switchInput == 6) {
            System.out.println("atak na = " + attackBoard[y - 1][x] + ", x = " + x + ", y = " + (y - 1));
            return attack(x, (y - 1), computer, player, player.getSea());
        } else if (switchInput == 7) {
            return randomDirectionAttack(x, y, attackBoard, player, computer);
        }

        return null; // never returned
    }

}
