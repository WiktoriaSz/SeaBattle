package com;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Player player = new Player(true);
        Player computer = new Player(false);

        Random random = new Random();
        int x = random.nextInt(10);
        System.out.println(x);
        x = random.nextInt(10);
        System.out.println(x);
        x = random.nextInt(10);
        System.out.println(x);
        x = random.nextInt(10);
        System.out.println(x);
        x = random.nextInt(10);
        System.out.println(x);
        x = random.nextInt(10);
        System.out.println(x);

//        String[][] array = computer.getSea();
//        for (String[] row : array){
//            System.out.println(Arrays.toString(row));
//        }

    }
}
