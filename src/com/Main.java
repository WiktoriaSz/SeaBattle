package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // pomoc do robieia switcha
//        List<Integer> list1 = new ArrayList<>();
//        list1.addAll(Arrays.asList(35, 25, 27, 17, 20, 6, 12, 24, 7, 14));
//        List<Integer> ceiling = new ArrayList<>();
//        for (Integer i : list1) {
//            ceiling.add(i + 22);
//        }
//        ceiling.remove(ceiling.indexOf(57));
//        ceiling.remove(ceiling.indexOf(49));
//        ceiling.remove(ceiling.indexOf(46));
//        List<Integer> upnull = new ArrayList<>();
//        for (Integer i : list1) {
//            upnull.add(i + 31);
//        }
//        List<Integer> xUPx = new ArrayList<>();
//        for (Integer i : list1) {
//            xUPx.add(i + 53);
//        }
//        xUPx.remove(xUPx.indexOf(60));
//        xUPx.remove(xUPx.indexOf(67));
//        xUPx.remove(xUPx.indexOf(77));
//
//        List<Integer> xUpDOT = new ArrayList<>();
//        for (Integer i : list1) {
//            xUpDOT.add(i + 62);
//        }
//        Collections.sort(ceiling);
//        Collections.sort(upnull);
//        Collections.sort(xUPx);
//        Collections.sort(xUpDOT);
//        Collections.sort(list1);
//
//        System.out.print("\nFirst is: ");
//        for (Integer i : list1) {
//            System.out.print(i + ", ");
//        }
//        System.out.print("\nCeiling is: ");
//        for (Integer i : ceiling) {
//            System.out.print(i + ", ");
//        }
//        System.out.print("\nUp null is: ");
//        for (Integer i : upnull) {
//            System.out.print(i + ", ");
//        }
//        System.out.print("\nX up x is: ");
//        for (Integer i : xUPx) {
//            System.out.print(i + ", ");
//        }
//        System.out.print("\nX up dot is: ");
//        for (Integer i : xUpDOT) {
//            System.out.print(i + ", ");
//        }
        List<Integer> listMain2 = new ArrayList<>();
        listMain2.addAll(Arrays.asList(29, 36, 38, 45, 48, 55, 56, 58, 66, 69, 86, 76));

        List<Integer> leftSideBoard = new ArrayList<>();
        for (Integer i : listMain2) {
            leftSideBoard.add(i + 4);
        }
        List<Integer> leftNull = new ArrayList<>();
        for (Integer i : listMain2) {
            leftNull.add(i + 36);
        }
        List<Integer> xLEFTx = new ArrayList<>();
        for (Integer i : listMain2) {
            xLEFTx.add(i + 59);
        }

        List<Integer> dotLEFTx = new ArrayList<>();
        for (Integer i : listMain2) {
            dotLEFTx.add(i + 82);
        }
        Collections.sort(leftSideBoard);
        Collections.sort(leftNull);
        Collections.sort(xLEFTx);
        Collections.sort(dotLEFTx);
        Collections.sort(listMain2);

        System.out.print("\nFirst is: ");
        for (Integer i : listMain2) {
            System.out.print(i + ", ");
        }
        System.out.print("\nLeft sideboard is: ");
        for (Integer i : leftSideBoard) {
            System.out.print(i + ", ");
        }
        System.out.print("\nLeft null is: ");
        for (Integer i : leftNull) {
            System.out.print(i + ", ");
        }
        System.out.print("\nX left x is: ");
        for (Integer i : xLEFTx) {
            System.out.print(i + ", ");
        }
        System.out.print("\ndot left X is: ");
        for (Integer i : dotLEFTx) {
            System.out.print(i + ", ");
        }
//        SetUp setUp = new SetUp();
//        Game game = new Game();
//        Player computer = new Player(false);
//        computer.setSea(setUp.seaSetUp(computer));
//
//        Player player = new Player(true);
//
//        // player board simulation for development:
//        List<Ship> temporary = new ArrayList<>();
//        temporary.add(new Ship(6,6,4,2));
//        temporary.add(new Ship(6,3,3,1));
//        temporary.add(new Ship(2,5,3,2));
//        temporary.add(new Ship(0,2,2,1));
//        temporary.add(new Ship(4,1,2,2));
//        temporary.add(new Ship(9,7,2,2));
//        temporary.add(new Ship(9,0,1,2));
//        temporary.add(new Ship(4,5,1,2));
//        temporary.add(new Ship(1,0,1,2));
//        temporary.add(new Ship(0,5,1,2));
//        for(Ship ship : temporary){
//            setUp.shipPlacement(player, ship, player.getSea());
//        }
//        // -----------------------should be:
//        // player.setSea(setUp.seaSetUp(player));
//
//        // game api:
//        // 1. my board
//        // 2. my attack board
//        // 3. attack
//        // 4. check status of enemy ship
//        // 5. check status of a ship ? - można sprawdzić na swojej planszy
//
//
//        game.gameSequence(player, computer);
    }
}
