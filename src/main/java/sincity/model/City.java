package sincity.model;

public class City {
    public String[][] puzzleBoard;

    public City(int verticalPuzzles, int horizontalPuzzles, int padding, double tileSize) {
        // TODO
        // temporary board for renderer to fetch, String will be changed to RoadPuzzle
        puzzleBoard = new String[horizontalPuzzles + padding * 2][verticalPuzzles + padding * 2];
        initializeBoard();
    }

    private void initializeBoard() {
        // fill initial board with road puzzles
        for (int x = 0; x < puzzleBoard.length; x++) {
            for (int y = 0; y < puzzleBoard[x].length; y++) {
                // puzzleBoard[x][y] = new PUZZEL
            }
        }
    }
}
