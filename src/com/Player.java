package com;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Ship> playerArmada = new ArrayList<>();
    private List<Ship> successfulHits = new ArrayList<>();
    private int points;
    private String[][] sea = new String[10][10];

    public Player() {
        this.points = 0;
    }

    public List<Ship> getPlayerArmada() {
        return playerArmada;
    }

    public void setPlayerArmada(List<Ship> playerArmada) {
        this.playerArmada = playerArmada;
    }

    public void setShip(Ship ship){
        getPlayerArmada().add(ship);
    }

    public List<Ship> getSuccessfulHits() {
        return successfulHits;
    }

    public void setSuccessfulHits(List<Ship> successfulHits) {
        this.successfulHits = successfulHits;
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
