package com;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(true);
        Player computer = new Player(false);
        SetUp setUp = new SetUp();
        setUp.checkAndSet(player, 4);


    }
}
