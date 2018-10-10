package sincity.model;

import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

class RoadPuzzle {
    private boolean[] roadDirections;
    private RoadType roadType;

    private int indexX; // position in City board
    private int indexY;

    private double size;

    private double coX; // position on Scene
    private double coY;

    private double centerX; // center of the puzzle - anchor point of path rotation
    private double centerY;

    private double halfLaneWidth;

    RoadPuzzle(int xIndex, int yIndex, int padding, double size, RoadType type) {
        this.roadDirections = type.getPossibleDirection();
        this.roadType = type;
        this.size = size;
        this.indexX = xIndex;
        this.indexY = yIndex;
        this.coX = xIndex * size - (padding * size);
        this.coY = yIndex * size - (padding * size);
        this.centerX = coX + size / 2.0;
        this.centerY = coY + size / 2.0;
        this.halfLaneWidth = 0.1 * size;
    }

    int getIndexX() {
        return indexX;
    }

    int getIndexY() {
        return indexY;
    }

    boolean[] getRoadDirections() {
        return roadDirections;
    }

    RoadType getRoadType() {
        return roadType;
    }

    private Path setPathRight() {

        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(coX);
        moveTo.setY(centerY + halfLaneWidth);

        LineTo lineTo = new LineTo();
        lineTo.setX(centerX - 2 * halfLaneWidth);
        lineTo.setY(centerY + halfLaneWidth);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - halfLaneWidth);
        arcTo.setY(centerY + halfLaneWidth);
        arcTo.setRadiusX(size/2 - halfLaneWidth);
        arcTo.setRadiusY(size/2 - halfLaneWidth);
        arcTo.setLargeArcFlag(false);
        arcTo.setSweepFlag(true);

        LineTo lineTo2 = new LineTo();
        lineTo2.setX(centerX - halfLaneWidth);
        lineTo2.setY(coY + size);
        
        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.getElements().add(arcTo);
        path.getElements().add(lineTo2);

        return path;
    }

    private Path setPathLeft() {

        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(coX);
        moveTo.setY(centerY + halfLaneWidth);

        LineTo lineTo = new LineTo();
        lineTo.setX(centerX - halfLaneWidth);
        lineTo.setY(centerY + halfLaneWidth);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX + halfLaneWidth);
        arcTo.setY(centerY - halfLaneWidth);
        arcTo.setRadiusX(3*halfLaneWidth);
        arcTo.setRadiusY(3*halfLaneWidth);

        LineTo lineTo2 = new LineTo();
        lineTo2.setX(centerX + halfLaneWidth);
        lineTo2.setY(coY);

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.getElements().add(arcTo);
        path.getElements().add(lineTo2);

        return path;
    }

    private Path setPathStraight() {

        Path path = new Path();

        MoveTo moveTo = new MoveTo();
        moveTo.setX(coX);
        moveTo.setY(centerY + halfLaneWidth);

        LineTo lineTo = new LineTo();
        lineTo.setX(coX + size);
        lineTo.setY(centerY + halfLaneWidth);

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);

        return path;
    }

    Path getPathToMove(String fromTo) {
        return setPathDirection(setPathShape(fromTo), fromTo); // setting path arrivalDirection and shape according to arrivalDirection "from" and "to"
    }

    private Path setPathShape(String fromTo) {

        Path  pathToMove = new Path ();

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

    private Path setPathDirection(Path  pathToMove, String fromTo) {
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
