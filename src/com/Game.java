package com;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public Boolean attack(int x, int y, Player attacker, Player enemy, String[][] enemySea) { // todo jeśli atakuje komputer + symulację inteligencji
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

    private Ship consequencesOfSuccessfulAttack(int x, int y, Player enemy) {
        Ship ship = enemy.searchByPosition(x, y);
        for (BodyPosition placement : ship.getFullPlacement()) {
            if (placement.getX() == x && placement.getY() == y) {
                placement.setStatus(Status.DAMAGED);
            }
        }
        return ship;
    }

    private void attackMapNotation(int x, int y, String[][] attackBoard, String[][] enemySea, boolean missed) {
        if (missed) {
            attackBoard[y][x] = ".";
            enemySea[y][x] = ".";
        } else {
            attackBoard[y][x] = "X";
            enemySea[y][x] = "X";
        }
    }

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

    public Boolean playerAttack(Player player, Player computer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPodaj pozycję poziomą (x) od 0 do 9.");
        int x = scanner.nextInt();
        System.out.println("Podaj pozycję pionową (y) od 0 do 9.");
        int y = scanner.nextInt();
        return attack(x, y, player, computer, computer.getSea());
    }

    public Boolean computerAttack(Player player, Player computer) {
        Random random = new Random();
        int x = random.nextInt(computer.getSea().length);
        int y = random.nextInt(computer.getSea().length);
        return attack(x, y, computer, player, player.getSea());
    }

    private Boolean advancedComputerAttack(Player player, Player computer, String[][] attackBoard) {
        for (int i = 0; i < attackBoard.length; i++) {
            for (int j = 0; j < attackBoard[i].length; j++) {
                if (attackBoard[i][j].equals("X") && player.searchByPosition(j, i).getStatus() != Status.SUNK) {
                    // switch(checkAround(j, i, attackBoard){
                    // case 1, 3:
                    // return attack((j + 1), i, computer, player, player.getSea());
                    // break;
                    // case 2:
                    // return attack(j, (i + 1), computer, player, player.getSea());
                    // break;
                    // case 4, 6: // sprawdza dalej w prawo - można nic nie robić
                    // break;
                    // case 5:
                    // continue;
                    // break;}

                }
            }
        }
        return computerAttack(player, computer);
    }

    private int checkAround(int x, int y, String[][] attackBoard) {
        int check = 0;
        if (x + 1 < attackBoard.length) {
            if (attackBoard[y][x + 1] == null) { // sprawdź w dół + jakiś oznacznik (check)
                check += 1;
            }
            if (attackBoard[y][x + 1].equals("X")){
                check += 4;
            }
        }
        if (y + 1 < attackBoard.length) { // sprawdź w dół}
            if (attackBoard[y + 1][x] == null) {
                check += 2;
            }
            if (attackBoard[y + 1][x].equals("X")) {
                check += 5;
            }
        }
        return check;
    }
    // check = 6 -> sprawdź w prawo
    // check = 5 -> sprawdź w dół
    // check = 4 -> sprawdzać w prawo
    // check = 3 -> współrzędne z j +1
    // check = 2 ->  współrzędne z i +1
    // check = 1 -> współrzędne z j +1
    // check = 0 -> nic nie robić, sprawdzać dalej

    // switch(checkAround){ case 1, 3: break; case 2: break; case 4, 6: break; case 5: break;}

    public void gameSequence(Player player, Player computer) {
        Boolean attackOrder = true;

        while (!winningCondition(player, computer) || !winningCondition(computer, player)) {

            while (attackOrder) {
                attackOrder = playerAttack(player, computer);
            }

            do {
                attackOrder = computerAttack(player, computer);

                // todo:
//                if(attackOrder == true){
                // while(attackOrder){
//                    attackOrder = advancedComputerAttack;
//                }
            } while (attackOrder);
        }

    }

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
}
