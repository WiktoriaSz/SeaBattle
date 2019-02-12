package com;

public class Check {

    public boolean checkIfWithinSeaBoundary(int x, int y, String[][] sea, int size, int position) {
        int dimension = ((position == 1) ? x : y) + (size - 1);
        return dimension <= (sea.length - 1);
    }

    public boolean checkIfExactPositionIsViable(int x, int y, String[][] sea, int size, int position) {
        for (int i = 0; i < size; i++) {
            if (position == 1) {
                if ((sea[y][x + i] != null)) {  // initial placement + full ship placement
                    return false;
                }
            } else {
                if ((sea[y + i][x] != null)) {  // initial placement + full ship placement
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkAroundPlacementOfHorizontalShip(int x, int y, String[][] sea, int size) {
        if ((y - 1) >= 0) {
            for (int i = 0; i < size; i++) {
                if ((sea[y - 1][x + i] != null)) {
                    return false;
                }
            }
        }
        if ((y + 1) <= (sea.length - 1)) {
            for (int i = 0; i < size; i++) {
                if ((sea[y + 1][x + i] != null)) {
                    return false;
                }
            }
        }
        if ((x - 1) >= 0) {
            if (sea[y][x - 1] != null) {
                return false;
            }
        }
        if ((x + size) <= (sea.length - 1)) {
            if ((sea[y][x + size]) != null) {
                return false;
            }
        }
        return true;
    }

    public boolean checkAroundPlacementOfVerticalShip(int x, int y, String[][] sea, int size) {
        if ((x - 1) >= 0) {
            for (int i = 0; i < size; i++) {
                if ((sea[y + i][x - 1]) != null) {
                    return false;
                }
            }
        }
        if ((x + 1) <= (sea.length - 1)) {
            for (int i = 0; i < size; i++) {
                if ((sea[y + i][x + 1]) != null) {
                    return false;
                }
            }
        }
        if ((y - 1) >= 0) {
            if ((sea[y - 1][x]) != null) {
                return false;
            }
        }
        if ((y + size) <= (sea.length - 1)) {
            if ((sea[y + size][x]) != null) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPosition(int x, int y, String[][] sea, int size, int position) {
        if (checkIfWithinSeaBoundary(x, y, sea, size, position)) {
            return checkIfExactPositionIsViable(x, y, sea, size, position);
        }
        return false;
    }
}
