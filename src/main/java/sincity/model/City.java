package sincity.model;

public class City {
    public RoadPuzzle[][] puzzleBoard;
    private int padding;
    private double tileSize;

    public City(int verticalPuzzles, int horizontalPuzzles, int padding, double tileSize) {
        puzzleBoard = new RoadPuzzle[horizontalPuzzles + padding * 2][verticalPuzzles + padding * 2];
        this.padding = padding;
        this.tileSize = tileSize;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int x = 0; x < puzzleBoard.length; x++) {
            for (int y = 0; y < puzzleBoard[x].length; y++) {
                double coX = (x * tileSize - (padding * tileSize));
                double coY = (x * tileSize - (padding * tileSize));
                if (y == 3) {
                    puzzleBoard[x][y] = new RoadPuzzle(coX, coY, tileSize, RoadType.EW);
                }
            }
        }
    }

    public RoadType getRoadType(int x, int y) {
        return puzzleBoard[x][y].getRoadType();
    }
}
