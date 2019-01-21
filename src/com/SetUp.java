package com;

import java.util.Scanner;

public class SetUp {

    private static boolean checkIfWithinSeaBoundary(int x, int y, String[][] sea, int size, int position) {
        switch (position) {
            case 1:
                if (x + (size - 1) > (sea.length - 1)) {
                    return false;
                }
            case 2:
                if (y + (size - 1) > (sea.length - 1)) {
                    return false;
                }
        }
        return true;
    }

    private static boolean checkIfExactPositionIsViable(int x, int y, String[][] sea, int size, int position) {
        switch (position) {
            case 1:
                for (int i = 0; i < size; i++) {
                    if ((sea[x + i][y] != null)) {  // initial placement + full ship placement
                        return false;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < size; i++) {
                    if ((sea[x][y + i] != null)) {  // initial placement + full ship placement
                        return false;
                    }
                    break;
                }
        }
        return true;
    }

    private static boolean checkAdjacentPlaces(int x, int y, String[][] sea, int size, int position) {
        switch (position) {
            case 1:
                if ((y - 1) >= 0) {
                    for (int i = 0; i < size; i++) {
                        if ((sea[x + i][y - 1] != null)) {
                            return false;
                        }
                    }
                }
                if ((y + 1) <= 9) {
                    for (int i = 0; i < size; i++) {
                        if ((sea[x + i][y + 1] != null)) {
                            return false;
                        }
                    }
                }
                break;
            case 2:
                if ((x - 1) >= 0) {
                    for (int i = 0; i < size; i++) {
                        if ((sea[x - 1][y + i] != null)) {
                            return false;
                        }
                    }
                }
                if ((x + 1) <= 9) {
                    for (int i = 0; i < size; i++) {
                        if ((sea[x + 1][y + i] != null)) {
                            return false;
                        }
                    }
                }
                break;
        }
        return true;
    }

    private static boolean checkBehind(int x, int y, String[][] sea, int position) {
        switch (position) {
            case 1:
                if ((x - 1 >= 0)) {
                    if (y - 1 >= 0) {
                        if (sea[x - 1][y - 1] != null) {
                            return false;
                        }
                    }
                    if (y + 1 <= (sea.length - 1)) {
                        if (sea[x - 1][y + 1] != null) {
                            return false;
                        }
                    }
                    if (sea[x - 1][y] != null) {
                        return false;
                    }
                }

            case 2:
                if ((y - 1 >= 0)) {
                    if (x - 1 >= 0) {
                        if (sea[x - 1][y - 1] != null) {
                            return false;
                        }
                    }
                    if (x + 1 <= (sea.length - 1)) {
                        if (sea[x + 1][y - 1] != null) {
                            return false;
                        }
                    }
                    if (sea[x][y - 1] != null) {
                        return false;
                    }
                }
        }
        return true;
    }

    private static boolean checkBefore(int x, int y, String[][] sea, int position) {
        switch (position) {
            case 1:
                if ((x + 1 <= (sea.length - 1))) {
                    if (y - 1 >= 0) {
                        if (sea[x + 1][y - 1] != null) {
                            return false;
                        }
                    }
                    if (y + 1 <= (sea.length - 1)) {
                        if (sea[x + 1][y + 1] != null) {
                            return false;
                        }
                    }
                    if (sea[x + 1][y] != null) {
                        return false;
                    }
                }

            case 2:
                if ((y + 1 <= (sea.length - 1))) {
                    if (x - 1 >= 0) {
                        if (sea[x - 1][y + 1] != null) {
                            return false;
                        }
                    }
                    if (x + 1 <= (sea.length - 1)) {
                        if (sea[x + 1][y + 1] != null) {
                            return false;
                        }
                    }
                    if (sea[x][y + 1] != null) {
                        return false;
                    }
                }
        }
        return true;
    }

    private static boolean checkAround(int x, int y, String[][] sea, int size, int position) {  // dla x, dla pos = 1, poziom
        if (checkBefore(x, y, sea, position)) {
            if (checkBehind(x, y, sea, position)) {
                if (checkAdjacentPlaces(x, y, sea, size, position)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkPosition(int x, int y, String[][] sea, int size, int position) {
        if (checkIfWithinSeaBoundary(x, y, sea, size, position)) {
            if (checkIfExactPositionIsViable(x, y, sea, size, position)) {
                if (checkAround(x, y, sea, size, position)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void initialSetUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj pozycję statku (x, y) - od 0 do 9.");
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println("Podaj położenie statku:" + "\n" + "1 - poprzeczna (x)," + "\n" + "2 - pionowa (y).");
        int position = sc.nextInt();
        System.out.println("Podaj wielkość statku - od 1 do 4.");
        int size = sc.nextInt();
    }

    public static void secondarySetUp(String[][] sea) {
        int x = -1;
        int y = -1;
        int size = -1;
        int position = -1;
        while (!SetUp.checkPosition(x, y, sea, size, position)) {
            SetUp.initialSetUp();
        }
    } // for a player

    public static void shipPlacement(int x, int y, String[][] sea, int size, int position, Player player) {
        Ship ship = new Ship(x, y, size, position);
        player.setShip(ship);
        switch (position) {
            case 1:
                for (int i = 0; i < size; i++) {
                    sea[x][y] = "o";
                    ++x;
                }
                break;

            case 2:
                for (int i = 0; i < size; i++) {
                    sea[x][y] = "o";
                    ++y;
                }
                break;
        }
    }
}
