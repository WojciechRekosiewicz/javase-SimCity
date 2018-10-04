package sincity.model;

import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;

class RoadPuzzle {

    private boolean[] roadDirections;
    private RoadType roadType;

    private double size;

    private double coX;
    private double coY;

    private double centerX;             // center of the puzzle - anchor point of path rotation
    private double centerY;

    private double halfLaneWidth;

    RoadPuzzle(double coX, double coY, double size, RoadType type) {
        this.roadDirections = type.getPossibleDirection();
        this.roadType = type;
        this.size = size;
        this.coX = coX;
        this.coY = coY;
        this.centerX = coX + size / 2.0;
        this.centerY = coY + size / 2.0;
        this.halfLaneWidth = 0.1 * size;
    }

    boolean[] getRoadDirections() {
        return roadDirections;
    }

    RoadType getRoadType() {
        return roadType;
    }

    private Polyline setPathRight() {
        Polyline pathRight = new Polyline();
        pathRight.getPoints().addAll(coX, centerY + halfLaneWidth,
                centerX - halfLaneWidth, centerY + halfLaneWidth,
                centerX - halfLaneWidth, coY + size);
        return pathRight;
    }

    private Polyline setPathLeft() {
        Polyline pathLeft = new Polyline();
        pathLeft.getPoints().addAll(coX, centerY + halfLaneWidth,
                centerX - halfLaneWidth, centerY + halfLaneWidth,
                centerX + halfLaneWidth, centerY + halfLaneWidth,
                centerX + halfLaneWidth, centerY - halfLaneWidth,
                centerX + halfLaneWidth, coY);
        return pathLeft;
    }

    private Polyline setPathStraight() {
        Polyline pathStraight = new Polyline();
        pathStraight.getPoints().addAll(coX, centerY + halfLaneWidth,
                coX + size, centerY + halfLaneWidth);
        return pathStraight;
    }

    Polyline getPathToMove(String fromTo) {
        return setPathDirection(setPathShape(fromTo), fromTo); // setting path arrivalDirection and shape according to arrivalDirection "from" and "to"
    }

    private Polyline setPathShape(String fromTo) {

        Polyline pathToMove = new Polyline();

        switch (fromTo) {
            case "N_W":
            case "S_E":
            case "W_S":
            case "E_N":
                pathToMove = setPathRight();
                break;
            case "N_E":
            case "S_W":
            case "W_N":
            case "E_S":
                pathToMove = setPathLeft();
                break;
            case "N_S":
            case "S_N":
            case "W_E":
            case "E_W":
                pathToMove = setPathStraight();
                break;
        }
        return pathToMove;
    }

    private Polyline setPathDirection(Polyline pathToMove, String fromTo) {
        switch (fromTo) {
            case "N_W":
            case "N_S":
            case "N_E":
                pathToMove.getTransforms().add(new Rotate(90, centerX, centerY));
                break;
            case "E_N":
            case "E_W":
            case "E_S":
                pathToMove.getTransforms().add(new Rotate(180, centerX, centerY));
                break;
            case "S_E":
            case "S_N":
            case "S_W":
                pathToMove.getTransforms().add(new Rotate(270, centerX, centerY));
        }
        return pathToMove;
    }
}
