package com;

public class Check {


    public boolean checkIfWithinSeaBoundary(int x, int y, String[][] sea, int size, int position) {
        switch (position) {
            case 1:
                return x + (size - 1) <= (sea.length - 1);
            case 2:
                return y + (size - 1) <= (sea.length - 1);
        }
        return false;
    }

    public boolean checkIfExactPositionIsViable(int x, int y, String[][] sea, int size, int position) {
        switch (position) {
            case 1:
                for (int i = 0; i < size; i++) {
                    if ((sea[y][x + i] != null)) {  // initial placement + full ship placement
                        return false;
                    }
                }
                break;
            case 2:
                for (int i = 0; i < size; i++) {
                    if ((sea[y + i][x] != null)) {  // initial placement + full ship placement
                        return false;
                    }
                }
                break;
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
        if ((x - 1 >= 0) && sea[y][x - 1] != null) {
                return false;
        }
        return (x + 1 > (sea.length - 1)) || sea[y][x + 1] == null;
    }

    public boolean checkAroundPlacementOfVerticalShip(int x, int y, String[][] sea, int size) {

        if ((x - 1) >= 0) {
            for (int i = 0; i < size; i++) {
                if ((sea[y + i][x - 1] != null)) {
                    return false;
                }
            }
        }
        if ((x + 1) <= (sea.length - 1)) {
            for (int i = 0; i < size; i++) {
                if ((sea[y + i][x + 1] != null)) {
                    return false;
                }
            }
        }
        if ((y - 1 >= 0) && (sea[y - 1][x] != null)) {
                return false;
        }

        return (y + 1 > (sea.length - 1)) || sea[y + 1][x] == null;
    }


//    public boolean checkAroundPlacementOfShip(int x, int y, String[][] sea, int size, int position) {
//
//        if ((y - 1) >= 0) {
//            for (int i = 0; i < size; i++) {
//                if ((sea[y - 1][x + i] != null)) {
//                    return false;
//                }
//            }
//        }
//        if ((y + 1) <= (sea.length - 1)) {
//            for (int i = 0; i < size; i++) {
//                if ((sea[y + 1][x + i] != null)) {
//                    return false;
//                }
//            }
//        }
//        if ((x - 1) >= 0) {
//            for (int i = 0; i < size; i++) {
//                if ((sea[y + i][x - 1] != null)) {
//                    return false;
//                }
//            }
//        }
//        if ((x + 1) <= (sea.length - 1)) {
//            for (int i = 0; i < size; i++) {
//                if ((sea[y + i][x + 1] != null)) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

//    public boolean checkBehindShipPlacement(int x, int y, String[][] sea, int position) {
//        switch (position) {
//            case 1:
//                if ((x - 1 >= 0)) {
////                    if (y - 1 >= 0) {
////                        if (sea[x - 1][y - 1] != null) {
////                            return false;
////                        }
////                    }
////                    if (y + 1 <= (sea.length - 1)) {
////                        if (sea[x - 1][y + 1] != null) {
////                            return false;
////                        }
////                    }
//                    if (sea[y][x - 1] != null) {
//                        return false;
//                    }
//                }
//
//            case 2:
//                if ((y - 1 >= 0)) {
////                    if (x - 1 >= 0) {
////                        if (sea[x - 1][y - 1] != null) {
////                            return false;
////                        }
////                    }
////                    if (x + 1 <= (sea.length - 1)) {
////                        if (sea[x + 1][y - 1] != null) {
////                            return false;
////                        }
////                    }
//                    if (sea[y - 1][x] != null) {
//                        return false;
//                    }
//                }
//        }
//        return true;
//    }
//
//    public boolean checkBeforeShipPlacement(int x, int y, String[][] sea, int position) {
//        switch (position) {
//            case 1:
//                if ((x + 1 <= (sea.length - 1))) {
////                    if (y - 1 >= 0) {
////                        if (sea[x + 1][y - 1] != null) {
////                            return false;
////                        }
////                    }
////                    if (y + 1 <= (sea.length - 1)) {
////                        if (sea[x + 1][y + 1] != null) {
////                            return false;
////                        }
////                    }
//                    if (sea[y][x + 1] != null) {
//                        return false;
//                    }
//                }
//
//            case 2:
//                if ((y + 1 <= (sea.length - 1))) {
////                    if (x - 1 >= 0) {
////                        if (sea[x - 1][y + 1] != null) {
////                            return false;
////                        }
////                    }
////                    if (x + 1 <= (sea.length - 1)) {
////                        if (sea[x + 1][y + 1] != null) {
////                            return false;
////                        }
////                    }
//                    if (sea[y + 1][x] != null) {
//                        return false;
//                    }
//                }
//        }
//        return true;
//    }

//    public boolean checkAroundPlacementOfShip(int x, int y, String[][] sea, int size, int position) {  // dla x, dla pos = 1, poziom
//        if (checkBeforeShipPlacement(x, y, sea, position)) {
//            if (checkBehindShipPlacement(x, y, sea, position)) {
//                return checkAdjacentPlaces(x, y, sea, size, position);
//            }
//        }
//        return false;
//    }

    public boolean checkPosition(int x, int y, String[][] sea, int size, int position) {
        if (checkIfWithinSeaBoundary(x, y, sea, size, position)) {
            if (checkIfExactPositionIsViable(x, y, sea, size, position)) {
                switch (position){
                    case 1:
                        return checkAroundPlacementOfHorizontalShip(x,y,sea,size);
                    case 2:
                        return checkAroundPlacementOfVerticalShip(x,y,sea,size);
                }
            }
        }
        return false;
    }
}
