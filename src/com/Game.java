package com;


import java.util.Arrays;

public class Game {

    public Boolean attack(int x, int y, Player attacker, Player enemy) { // todo jeśli atakuje komputer + symulację inteligencji
        // checking for repeated moves, especially for computer input
        if (enemy.getSea()[y][x].equals("m") || enemy.getSea()[y][x].equals("X")) {
            System.out.println("Ta pozycja była zaatakowana już wcześniej. Spróbuj jeszcze raz.");
            return true;
        }

        System.out.println("Zaatakowano pozycję x = " + x + ", y = " + y);
        Ship ship = enemy.searchByPosition(x, y);
        if (ship == null) { // szuka w armadzie przeciwnika
            System.out.println("Pudło!");
            attacker.getAttackBoard()[y][x] = "m";
            enemy.getSea()[y][x] = "m";
            return false;

        } else {
            attacker.getAttackBoard()[y][x] = "X";
            enemy.getSea()[y][x] = "X";
            for (BodyPosition placement : ship.getFullPlacement()) {
                if (placement.getX() == x && placement.getY() == y) {
                    placement.setStatus(Status.DAMAGED);
                }
            }
            shipCondition(ship);
            System.out.println(board(attacker.getAttackBoard()));

            if (winningCondition(attacker, enemy)) {
                return null;
            } else {
                return true;
            }
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
        return "  0x,  1x,  2x,  3x,  4x,  5x,  6x,  7x,  8x,  9x \n" +
                Arrays.deepToString(sea)
                        .replace("], ", "]\n")
                        .replace("[[", "[")
                        .replace("]]", "]")
                        .replace("null", "---")
                        .replace("o, ", " o , ")
                        .replace("o]", " o ]")
                        .replace("m, ", " m , ")
                        .replace("m]", " m ]")
                        .replace("X]", " X ]")
                        .replace("X, ", " X , ");
    }
}
