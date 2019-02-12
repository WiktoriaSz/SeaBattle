package com;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(true);
        Player computer = new Player(false);
        SetUp setUp = new SetUp();
        player.setSea(setUp.seaSetUp(player));
        computer.setSea(setUp.seaSetUp(computer));

    }
}
