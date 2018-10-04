package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Spawner {
    private List<RoadPuzzle> spawnPuzzles = new ArrayList<>();

    public Spawner(RoadPuzzle[][] puzzleBoard) {
        getSpawnPuzzles(puzzleBoard);
        spawnTimer(spawnPuzzles);
    }

    private void getSpawnPuzzles(RoadPuzzle[][] puzzleBoard) {
        for (int x = 0; x < puzzleBoard.length; x++) {
            for (int y = 0; y < puzzleBoard[x].length; y++) {
                boolean upperLeftPadding = (x == 0 || y == 0);
                boolean lowerRightPadding = (x == puzzleBoard.length - 1 || y == puzzleBoard[x].length - 1);
                boolean isNotBackground = !(puzzleBoard[x][y].getRoadType().equals(RoadType.BCG));
                if ((upperLeftPadding || lowerRightPadding) && isNotBackground) {
                    spawnPuzzles.add(puzzleBoard[x][y]);
                }
            }
        }
    }

    private void spawnTimer(List<RoadPuzzle> spawnPuzzles) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            RoadPuzzle spawnPuzzle = getRandomSpawnPuzzle(spawnPuzzles);
            new Vehicle(spawnPuzzle);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private RoadPuzzle getRandomSpawnPuzzle(List<RoadPuzzle> spawnPuzzles) {
        int randomIndex = (int) Math.floor(Math.random() * spawnPuzzles.size());
        return spawnPuzzles.get(randomIndex);
    }
}