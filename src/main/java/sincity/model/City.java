package sincity.model;

import java.util.ArrayList;
import java.util.List;

public class City {
    private RoadPuzzle[][] puzzleBoard;
    private RoadType[][] userMap;
    private int padding;
    private double tileSize;
    private int colNumb;
    private int rowNumb;
    private List<RoadType> nonLightsTypesOfPuzzles = createNonLightsList();

    public City(RoadType[][] userMap, int verticalPuzzles, int horizontalPuzzles, int padding, double tileSize) {
        puzzleBoard = new RoadPuzzle[verticalPuzzles + padding * 2][horizontalPuzzles + padding * 2];
        this.userMap = userMap;
        this.padding = padding;
        this.tileSize = tileSize;
        this.colNumb = horizontalPuzzles;
        this.rowNumb = verticalPuzzles;
        initializeBoard();
    }

    public RoadPuzzle[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    public RoadType getRoadType(int row, int col) {
        return puzzleBoard[row][col].getRoadType();
    }

    private void initializeBoard() {

        replaceEmptyPlacesWithBCG();

        for (int row = 0; row < rowNumb + padding * 2; row++) {
            for (int col = 0; col < colNumb + padding * 2; col++) {

                // set padding
                if (isOnCorner(row, col)) {
                    puzzleBoard[row][col] = new RoadPuzzle(row, col, padding, tileSize, RoadType.BCG1, false);
                } else if (isOnHorizontalEdge(row, rowNumb)) {
                    setForHorizontalPadding(row, col);
                } else if (isOnVerticalEdge(col, colNumb)) {
                    setForVerticalPadding(row, col);

                    // set user's element
                } else {
                    boolean hasLights = nonLightsTypesOfPuzzles.contains(userMap[row][col]) ? false : true;
                    puzzleBoard[row][col] = new RoadPuzzle(col, row,  padding, tileSize, userMap[row][col], hasLights);
                }
            }

            // tylko wyswietlanie na konsoli tabeli
            String string = "row " + row;
            for (int i = 0; i <colNumb + padding * 2 ; i++) {
                string = string + " " + puzzleBoard[row][i].getRoadType();
            }
            System.out.println(string);

        }
    }

    private void replaceEmptyPlacesWithBCG() {
        for (int r = 0; r < userMap.length; r++) {
            for (int c = 0; c < userMap[r].length; c++) {
                if (userMap[r][c] == null) {
                    userMap[r][c] = RoadType.BCG3;
                }
            }
        }
    }

    private boolean isOnVerticalEdge(int col, int colNumb) {
        return col == 0 || col == colNumb + padding;
    }

    private boolean isOnHorizontalEdge(int row, int rowNumb) {
        return row == 0 || row == rowNumb + padding;
    }

    private boolean isOnCorner(int row, int col) {
        return (row == 0 && col == 0) || (row == 0 && col == (colNumb + padding * 2)) ||
                (row == (rowNumb + padding * 2) && col == 0) || (row == (rowNumb + padding * 2) && col == (colNumb + padding * 2));
    }

    private void setForHorizontalPadding(int row, int col) {
        RoadType neighborType = row == 0 ? userMap[row + 1][col] : userMap[row - 1][col];

        if (row == 0) {
            puzzleBoard[row][col] = neighborType.checkPossibleDirection(Direction.N) ?
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.NS, false) :
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.BCG8, false);
        } else {
            puzzleBoard[row][col] = neighborType.checkPossibleDirection(Direction.S) ?
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.NS, false) :
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.BCG8, false);
        }
    }

    private void setForVerticalPadding(int row, int col) {
        RoadType neighborType = col == 0 ? userMap[row][col+1] : userMap[row][col-1];
        if (col == 0) {
            puzzleBoard[row][col] = neighborType.checkPossibleDirection(Direction.W) ?
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.EW, false) :
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.BCG1, false);
        } else {
            puzzleBoard[row][col] = neighborType.checkPossibleDirection(Direction.E) ?
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.EW, false) :
                    new RoadPuzzle(row, col, padding, tileSize, RoadType.BCG1, false);
        }
    }

    private List<RoadType> createNonLightsList() {
        List<RoadType> nonLightsPuzzles = new ArrayList<>();
        nonLightsPuzzles.add(RoadType.BCG1);
        nonLightsPuzzles.add(RoadType.BCG2);
        nonLightsPuzzles.add(RoadType.BCG3);
        nonLightsPuzzles.add(RoadType.BCG4);
        nonLightsPuzzles.add(RoadType.BCG5);
        nonLightsPuzzles.add(RoadType.BCG6);
        nonLightsPuzzles.add(RoadType.BCG7);
        nonLightsPuzzles.add(RoadType.BCG8);
        nonLightsPuzzles.add(RoadType.BCG9);
        nonLightsPuzzles.add(RoadType.BCG10);
        nonLightsPuzzles.add(RoadType.NS);
        nonLightsPuzzles.add(RoadType.EW);
        return nonLightsPuzzles;
    }
}
