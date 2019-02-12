package com;

public class Ship {
    private int startingPositionX;
    private int startingPositionY;
    private int size;
    private int position;
    private Status status;

    public Ship(int startingPositionX, int startingPositionY, int size, int position) {
        this.startingPositionX = startingPositionX;
        this.startingPositionY = startingPositionY;
        this.size = size;
        this.position = position;
        this.status = Status.WHOLE;
    }

    public int getPosition() {
        return position;
    }

    public int getStartingPositionX() {
        return startingPositionX;
    }

    public int getStartingPositionY() {
        return startingPositionY;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "X = " + startingPositionX +
                ", Y = " + startingPositionY +
                ", size = " + size +
                ", position = " + position +
                ", status = " + status +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
