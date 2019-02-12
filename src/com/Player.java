package com;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> playerArmada = new ArrayList<>();
    private int points;
    private String[][] sea;
    private boolean whichPlayer;

    public Player(boolean whichPlayer) {
        this.whichPlayer = whichPlayer;
        this.points = 0;
        this.sea = new String[10][10];
    }

    public List<Ship> getPlayerArmada() {
        return playerArmada;
    }

    public void setShip(Ship ship){
        getPlayerArmada().add(ship);
    }

    public boolean isWhichPlayer() {
        return whichPlayer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String[][] getSea() {
        return sea;
    }

    public void setSea(String[][] sea) {
        this.sea = sea;
    }
}
