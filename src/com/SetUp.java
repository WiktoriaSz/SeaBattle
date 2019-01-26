package com;

import java.util.*;

public class SetUp {

    private static List<Integer> shipTypes = new ArrayList<>();

    static {
        shipTypes.addAll(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));
    }
    //----------------------------check placement of the ship for viability-------------------------

    private static boolean checkIfWithinSeaBoundary(int x, int y, String[][] sea, int size, int position) {
        switch (position) {
            case 1:
                if (x + (size - 1) > (sea.length - 1)) {
                    return false;
                } else {
                    return true;
                }
            case 2:
                if (y + (size - 1) > (sea.length - 1)) {
                    return false;
                }
                else {
                    return true;
                }
        }
        return false;
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

    private static boolean checkBehindShipPlacement(int x, int y, String[][] sea, int position) {
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

    private static boolean checkBeforeShipPlacement(int x, int y, String[][] sea, int position) {
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

    private static boolean checkAroundPlacementOfShip(int x, int y, String[][] sea, int size, int position) {  // dla x, dla pos = 1, poziom
        if (checkBeforeShipPlacement(x, y, sea, position)) {
            if (checkBehindShipPlacement(x, y, sea, position)) {
                if (checkAdjacentPlaces(x, y, sea, size, position)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkPosition(int x, int y, String[][] sea, int size, int position) {
        if (checkIfWithinSeaBoundary(x, y, sea, size, position)) {
            if (checkIfExactPositionIsViable(x, y, sea, size, position)) {
                return checkAroundPlacementOfShip(x, y, sea, size, position);
            }
        }
        return false;
    }

    //-----------------------set up-------------------------------

    private static Ship playerInputForSetUp(int size, String[][] sea) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj pozycję statku (x, y) - od 0 do " + (sea.length - 1) + " dla " + size + "-masztowca.");
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println("Podaj położenie statku:" + "\n" + "1 - poprzeczna (x)," + "\n" + "2 - pionowa (y).");
        int position = sc.nextInt();
        return new Ship(x, y, size, position);

    } // for a player - will go into last method's argument

    private static Ship shipGenerator(int size, String[][] sea) {
        Random random = new Random();
        int x = random.nextInt(sea.length);
        int y = random.nextInt(sea.length);
        Random r = new Random();
        int position;
        if (r.nextBoolean()) {
            position = 1;
        } else {
            position = 2;
        }
        return new Ship(x, y, size, position);
    } // for virtual player - will go into last method's argument

    public static Ship choseInput(boolean whichPlayer, int size, String [][] sea){
        Ship ship;
        if (whichPlayer){
        return playerInputForSetUp(size, sea);
        } else {
        return shipGenerator(size, sea);
        }
    }

    public static Ship checkAndSet(Player player, int size) {
        int x = -1;
        int y = -1;
        int position = -1;

        while (!SetUp.checkPosition(x, y, player.getSea(), size, position)) {
            Ship ship = choseInput(player.isWhichPlayer(), size, player.getSea());
            x = ship.getStartingPositionX();
            if (x < 0 || x >= player.getSea().length){
                System.out.println("Niepoprawne dane. Spróbuj ponownie.");
                continue;
            }
            y = ship.getStartingPositionY();
            if (y < 0 || y >= player.getSea().length){
                System.out.println("Niepoprawne dane. Spróbuj ponownie.");
                continue;
            }
            position = ship.getPosition();
            if (position < 1 || position > 2){
                System.out.println("Niepoprawne dane. Spróbuj ponownie.");
            }
        }

        Ship approvedShip = new Ship(x, y, size, position);
        player.setShip(approvedShip);

        return approvedShip;
    }

    public static void shipPlacement(Player player, Ship ship) {
        int x = ship.getStartingPositionX();
        int y = ship.getStartingPositionY();
        switch (ship.getPosition()) {
            case 1:
                for (int i = 0; i < ship.getSize(); i++) {
                    player.getSea()[x][y] = "o";
                    ++x;
                }
                break;

            case 2:
                for (int i = 0; i < ship.getSize(); i++) {
                    player.getSea()[x][y] = "o";
                    ++y;
                }
                break;
        }
    }

    public static void seaSetUp(Player player) {
        for (Integer shipType : shipTypes) {
            shipPlacement(player,
                    checkAndSet(player, shipType));
        }
    }

}
