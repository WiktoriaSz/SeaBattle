package com;

import java.util.*;

public class SetUp {

    private static List<Integer> shipSizes = new ArrayList<>();
    private Check check = new Check();
    private Game game = new Game();

    static {
        shipSizes.addAll(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));
    }

    private static Ship playerInputForSetUp(int size, String[][] sea) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj pozycję statku (x, y) - od 0 do " + (sea.length - 1)
                + " dla " + size + "-masztowca.");
        int x = sc.nextInt();
        int y = sc.nextInt();

        while ((x < 0 || x >= sea.length) ||
                (y < 0 || y >= sea.length)) {
            System.err.println("Niepoprawne dane. Spróbuj ponownie.");
            x = sc.nextInt();
            y = sc.nextInt();
        }

        System.out.println("Podaj położenie statku:" + "\n"
                + "1 - horyzontalna (x)," + "\n" + "2 - pionowa (y).");
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
    } // for virtual player - aka computer

    public static Ship choseInput(boolean whichPlayer, int size, String[][] sea) {
        if (whichPlayer) {
            return playerInputForSetUp(size, sea);
        } else {
            return shipGenerator(size, sea);
        }
    }

    public Ship checkAndSet(Player player, int size, String[][] sea) {
        int x, y, position;
        boolean badPlacement = false;
        boolean isRealPlayer = player.isWhichPlayer();

        do {
            Ship ship = choseInput(isRealPlayer, size, sea);
            x = ship.getStartingPositionX();
            y = ship.getStartingPositionY();
            position = ship.getPosition();
            if (check.checkPosition(x, y, sea, size, position)) {
                if (position == 1) {
                    badPlacement = check.checkAroundPlacementOfHorizontalShip(x, y, sea, size);
                } else if (position == 2) {
                    badPlacement = check.checkAroundPlacementOfVerticalShip(x, y, sea, size);
                }
            }
        } while (!badPlacement);

        return new Ship(x, y, size, position);
    }

    public void shipPlacement(Player player, Ship ship, String[][] sea) {
        List<BodyPosition> placement = new ArrayList<>();
        int x = ship.getStartingPositionX();
        int y = ship.getStartingPositionY();
        int position = ship.getPosition();
        for (int i = 0; i < ship.getSize(); i++) {
            sea[y][x] = "o";
            placement.add(new BodyPosition(x, y));
            if (position == 1) {
                ++x;
            } else {
                ++y;
            }
        }
        ship.setFullPlacement(placement);
        player.setShip(ship);
        if (player.isWhichPlayer()) {
            System.out.println("Statek został umieszczony.\n");
            System.out.println(game.board(sea));
        }
    }

    public String[][] seaSetUp(Player player) {
        String[][] sea = player.getSea();
        for (Integer shipType : shipSizes) {
            shipPlacement(player,
                    checkAndSet(player, shipType, sea), sea);
            sea = player.getSea();
        }
        return sea;
    }

}
