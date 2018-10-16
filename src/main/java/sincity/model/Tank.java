package sincity.model;

import javafx.animation.PathTransition;
import sincity.view.*;
import sincity.view.Renderer;


public class Tank {


    private double speed = 1; // 1 is default
    private RoadPuzzle currentRoadPuzzle;
    private Direction arrivalDirection;
    private Direction outDirection;
    private City city;
    private Renderer renderer;
    private TankDisplay tankDisplay;
    private PathTransition pathTransition;

    Tank(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection) {
        this.renderer = renderer;
        this.currentRoadPuzzle = roadPuzzle;
        this.arrivalDirection = arrivalDirection;
        this.tankDisplay = renderer.renderTank();
        this.city = city;
        moveTank();
    }

    public void update() {
        System.out.println("X: " + tankDisplay.getTranslateX());
        //   System.out.println("Y: " + tankDisplay.getTranslateY());

    }

    private void moveTank() {
        //System.out.println("ZMIANA PUZZLA PRZED: " + outDirection);
        // wypisanie z kolejki o danym kierunku

        outDirection = getRandomOutDirection(currentRoadPuzzle.getRoadDirections());
        System.out.println("dfdfgdf");
        // System.out.println("ZMIANA PUZZLA PO: " + outDirection);
        // wpisanie do kolejki o danym kierunku

        String fromTo = arrivalDirection.toString() + "_" + outDirection.toString();

        PathToMove pathToMove = new PathToMove(currentRoadPuzzle, fromTo);
        pathTransition = renderer.moveAnimationTank(tankDisplay, pathToMove, speed);
        pathTransition.setOnFinished(event -> {
            changeRoadPuzzle(currentRoadPuzzle);
            if (currentRoadPuzzle != null) {
                moveTank();
            }
        });
    }

    private Direction getRandomOutDirection(boolean[] directions) {
        Direction[] allDirections = new Direction[]{Direction.E, Direction.N, Direction.S, Direction.W};
        int randomIndex;
        do {
            randomIndex = (int) Math.floor(Math.random() * allDirections.length);
        } while (!directions[randomIndex] || allDirections[randomIndex].equals(arrivalDirection));
        return allDirections[randomIndex];
    }

    private void changeRoadPuzzle(RoadPuzzle puzzle) {
        currentRoadPuzzle = findNextPuzzle(puzzle, outDirection);

    }

    private RoadPuzzle findNextPuzzle(RoadPuzzle puzzle, Direction outDir) {
        System.out.println();
        int currentX = puzzle.getIndexX();
        int currentY = puzzle.getIndexY();

        int nextPuzzleIndexX = currentX;
        int nextPuzzleIndexY = currentY;

        switch (outDir) {
            case W:
                System.out.println();
                nextPuzzleIndexX = currentX - 1;
                arrivalDirection = Direction.E;
                break;
            case E:
                nextPuzzleIndexX = currentX + 1;
                arrivalDirection = Direction.W;
                break;
            case N:
                nextPuzzleIndexY = currentY - 1;
                arrivalDirection = Direction.S;
                break;
            case S:
                nextPuzzleIndexY = currentY + 1;
                arrivalDirection = Direction.N;
                break;
        }

        try {
            return city.getPuzzleBoard()[nextPuzzleIndexX][nextPuzzleIndexY];
        } catch (Exception e) {
            return null;
        }
    }
}


