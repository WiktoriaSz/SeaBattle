package com;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SetUp setUp = new SetUp();
        Game game = new Game();
        Player computer = new Player(false);
        computer.setSea(setUp.seaSetUp(computer));

        Player player = new Player(true);

        // player board simulation for development:
        List<Ship> temporary = new ArrayList<>();
        temporary.add(new Ship(6,6,4,2));
        temporary.add(new Ship(6,3,3,1));
        temporary.add(new Ship(2,5,3,2));
        temporary.add(new Ship(0,2,2,1));
        temporary.add(new Ship(4,1,2,2));
        temporary.add(new Ship(9,7,2,2));
        temporary.add(new Ship(9,0,1,2));
        temporary.add(new Ship(4,5,1,2));
        temporary.add(new Ship(1,0,1,2));
        temporary.add(new Ship(0,5,1,2));
        for(Ship ship : temporary){
            setUp.shipPlacement(player, ship, player.getSea());
        }
        // -----------------------should be:
        // player.setSea(setUp.seaSetUp(player));

        game.gameSequence(player, computer);
    }
}
