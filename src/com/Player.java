package com;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Ship> playerArmada = new ArrayList<>();
    private int points;
    private String[][] sea;
    private String[][] attackBoard;
    private boolean whichPlayer;

    public Player(boolean whichPlayer) {
        this.whichPlayer = whichPlayer;
        this.points = 0;
        this.sea = new String[10][10];
        this.attackBoard = new String[10][10];
        if(whichPlayer){
            name = "Gracz";
        } else {
            name = "Komputer";
        }
    }

    public List<Ship> getPlayerArmada() {
        return playerArmada;
    }

    public void setShip(Ship ship) {
        getPlayerArmada().add(ship);
    }

    public boolean isWhichPlayer() {
        return whichPlayer;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        this.points += 1;
    }

    public String[][] getSea() {
        return sea;
    }

    public String[][] getAttackBoard() {
        return attackBoard;
    }

    public void setSea(String[][] sea) {
        this.sea = sea;
    }

    public Ship searchByPosition(int x, int y) {
        for (Ship ship : playerArmada) {
            for (int i = 0; i < ship.getFullPlacement().size(); i++) {
                if (ship.getFullPlacement().get(i).getX() == x &&
                        ship.getFullPlacement().get(i).getY() == y) {
                    return ship;
                }
            }
        }
        return null;
    }
}
