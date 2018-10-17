package sincity.model;

public class City {
    private RoadPuzzle[][] puzzleBoard;
    private int padding;
    private double tileSize;
    public City(int verticalPuzzles, int horizontalPuzzles, int padding, double tileSize) {
        puzzleBoard = new RoadPuzzle[horizontalPuzzles + padding * 2][verticalPuzzles + padding * 2];
        this.padding = padding;
        this.tileSize = tileSize;
        initializeBoard();
    }

    public RoadPuzzle[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    public RoadType getRoadType(int x, int y) {
        return puzzleBoard[x][y].getRoadType();
    }

    private void initializeBoard() {
        for (int x = 0; x < puzzleBoard.length; x++) {
            for (int y = 0; y < puzzleBoard[x].length; y++) {
                // test intersection
                if ((y == 3 && x == 4) || (y == 5 && x == 2)) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ENW, true);
                } else if (y == 3 && x == 2){
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ESW, false);
                }else if (y == 3 && (x == 6 || x == 8)) {
                    if (x==6) {
                        puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ENSW, true);
                    } else {
                        puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ENSW, false);
                    }
                } else if (y == 3 || (y == 5 && x < 6)) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.EW, false);
                } else if (y == 2 && x == 8){
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.ENS, false);
                } else if (y == 2 && x > 8) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.EW, false);
                } else if (y == 5 && x == 6 ){
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.NSW, false);
                } else if ((x == 4 && y <= 3) || (x == 6 || x == 8) || (y ==4 && x == 2)) {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.NS, false);
                } else {
                    puzzleBoard[x][y] = new RoadPuzzle(x, y, padding, tileSize, RoadType.BCG, false);
                }
            }
        }
    }
}
