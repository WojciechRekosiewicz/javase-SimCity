package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sincity.controller.GameLoop;
import sincity.view.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Spawner {
    private List<RoadPuzzle> spawnPuzzles = new ArrayList<>();
    private Renderer renderer;
    private City city;
    private GameLoop gameLoop;

    public Spawner(City city, Renderer renderer, GameLoop gameLoop) {
        this.city = city;
        this.renderer = renderer;
        this.gameLoop = gameLoop;
        getSpawnPuzzles(city.getPuzzleBoard());
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
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            RoadPuzzle spawnPuzzle = getRandomSpawnPuzzle(spawnPuzzles);
            Direction arrivalDirection = getArrivalDirection(spawnPuzzle);
            Vehicle vehicle = new Vehicle(city, renderer, spawnPuzzle, arrivalDirection);
            gameLoop.addToVehicleList(vehicle);
//        }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

    private RoadPuzzle getRandomSpawnPuzzle(List<RoadPuzzle> spawnPuzzles) {
        int randomIndex = (int) Math.floor(Math.random() * spawnPuzzles.size());
        return spawnPuzzles.get(randomIndex);
    }

    private Direction getArrivalDirection(RoadPuzzle spawnPuzzle) {
        Direction arrivalDirection = null;
        if (spawnPuzzle.getIndexX() == 0) {
            arrivalDirection = Direction.W;
        } else if (spawnPuzzle.getIndexX() == city.getPuzzleBoard().length - 1) {
            arrivalDirection = Direction.E;
        } else if (spawnPuzzle.getIndexY() == 0) {
            arrivalDirection = Direction.N;
        } else if (spawnPuzzle.getIndexY() == city.getPuzzleBoard()[0].length - 1) {
            arrivalDirection = Direction.S;
        }
        return arrivalDirection;
    }
}