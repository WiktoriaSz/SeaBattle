package com;

public class BodyPosition {
    final private int x;
    final private int y;
    private Status status;

    public BodyPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = Status.WHOLE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
