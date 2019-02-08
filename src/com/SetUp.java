package com;

import java.util.*;

public class SetUp {

    private static List<Integer> shipSizes = new ArrayList<>();
    private Check check = new Check();

    static {
        shipSizes.addAll(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));
    }

    private static Ship playerInputForSetUp(int size, String[][] sea) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj pozycję statku (x, y) - od 0 do " + (sea.length - 1) + " dla " + size + "-masztowca.");
        int x = sc.nextInt();
        int y = sc.nextInt();

        while ((x < 0 || x >= sea.length) ||
                (y < 0 || y >= sea.length)) {
            System.err.println("Niepoprawne dane. Spróbuj ponownie.");
            x = sc.nextInt();
            y = sc.nextInt();
        }

        System.out.println("Podaj położenie statku:" + "\n" + "1 - horyzontalna (x)," + "\n" + "2 - pionowa (y).");
        int position = sc.nextInt();

        while (position > 2 || position < 1) {
            System.err.println("Niepoprawne dane. Spróbuj ponownie.");
            position = sc.nextInt();
        }

        return new Ship(x, y, size, position);
    } // for a player

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

    public static Ship choseInput(boolean whichPlayer, int size, String[][] sea) {
        if (whichPlayer) {
            return playerInputForSetUp(size, sea);
        } else {
            return shipGenerator(size, sea);
        }
    }

    public Ship checkAndSet(Player player, int size) {
        int x = -1;
        int y = -1;
        int position = -1;
        boolean badPlacement = true;
        while (badPlacement) {
            Ship ship = choseInput(player.isWhichPlayer(), size, player.getSea());
            x = ship.getStartingPositionX();
            y = ship.getStartingPositionY();
            position = ship.getPosition();
// ------------------- for user player only
//            if (x < 0 || x >= player.getSea().length) {
//                System.out.println("Niepoprawne dane. Spróbuj ponownie.");
//                continue;
//            }
//            if (y < 0 || y >= player.getSea().length) {
//                System.out.println("Niepoprawne dane. Spróbuj ponownie.");
//                continue;
//        }
//        if (position < 1 || position > 2) {
//            System.out.println("Niepoprawne dane. Spróbuj ponownie.");
//        }
//        //----------------------
//    }
            badPlacement = !(this.check.checkPosition(x, y, player.getSea(), size, position));
        }
        Ship approvedShip = new Ship(x, y, size, position); // todo: dane z while?
        player.setShip(approvedShip);

        return approvedShip;
    }

    public static void shipPlacement(Player player, Ship ship) {
        int x = ship.getStartingPositionX();
        int y = ship.getStartingPositionY();
        switch (ship.getPosition()) {
            case 1:
                for (int i = 0; i < ship.getSize(); i++) {
                    player.getSea()[y][x] = "o";
                    ++x;
                }
                break;

            case 2:
                for (int i = 0; i < ship.getSize(); i++) {
                    player.getSea()[y][x] = "o";
                    ++y;
                }
                break;
        }
    }

    public void seaSetUp(Player player) {
        for (Integer shipType : shipSizes) {
            shipPlacement(player,
                    checkAndSet(player, shipType));
        }
    }

}
