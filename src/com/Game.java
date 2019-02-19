package com;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public Boolean attack(int x, int y, Player attacker, Player enemy, String[][] enemySea) { // todo jeśli atakuje komputer + symulację inteligencji
        // checking for repeated moves, especially for computer input
        if (enemySea[y][x] == null) {
            attacker.getAttackBoard()[y][x] = ".";
            enemySea[y][x] = ".";
            System.out.println("\nPlansza ataku " + attacker.getName() +"a: \n" + board(attacker.getAttackBoard()));
            System.out.println("Atakuje " + attacker.getName() + ", pozycje x = " + x + ", y = " + y + " : Pudło!");
            return false;

        } else if ((enemySea[y][x].equals(".")) || (enemySea[y][x].equals("X"))) {
            if (attacker.isWhichPlayer()) {
                System.out.println("\nTa pozycja była zaatakowana już wcześniej. Spróbuj jeszcze raz.");
            }
            return true;

        } else {
            Ship ship = enemy.searchByPosition(x, y);
            attacker.getAttackBoard()[y][x] = "X";
            enemySea[y][x] = "X";
            for (BodyPosition placement : ship.getFullPlacement()) {
                if (placement.getX() == x && placement.getY() == y) {
                    placement.setStatus(Status.DAMAGED);
                }
            }
            System.out.println("\nPlansza ataku " + attacker.getName() +"a: \n" + board(attacker.getAttackBoard()));
            System.out.print("Atakuje " + attacker.getName() + ", pozycje x = " + x + ", y = " + y + " : ");
            shipCondition(ship);

            if (winningCondition(attacker, enemy)) {
                return null;
            } else {
                return true;
            }
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

    public void gameSequence(Player player, Player computer) {  // todo --> + zakryć wyświetlanie planszy gracza
        Boolean attackOrder = true;

        while (!winningCondition(player, computer) || !winningCondition(computer, player)) {
            if (attackOrder == null){ // todo
                break;
            }

            do {
                attackOrder = playerAttack(player, computer);
            } while (attackOrder);

//            attackOrder = computerAttack(player, computer);

            do {
                attackOrder = computerAttack(player, computer);
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
        return "  0x,  1x,  2x,  3x,  4x,  5x,  6x,  7x,  8x,  9x \n" +
                Arrays.deepToString(sea)
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
    }
}
