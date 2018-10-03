package sincity.model;

import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;

public class RoadPuzzle {

    private boolean[] roadType;

    private double size;

    private double coX;
    private double coY;

    private double centerX = coX + size / 2.0;             // center of the puzzle - anchor point of path rotation
    private double centerY = coY + size / 2.0;

    private double halfLaneWidth = 0.1 * size;


    private Polyline pathRight = new Polyline();        //
    private Polyline pathLeft = new Polyline();
    private Polyline pathStright = new Polyline;



    private void setPathTurnRight() {
        pathRight.getPoints().addAll(coX, centerY + halfLaneWidth,
                centerX - halfLaneWidth, centerY + halfLaneWidth,
                centerX - halfLaneWidth, coY + size);
    }

    private void setPathTurnLeft() {
        pathLeft.getPoints().addAll(coX, centerY + halfLaneWidth,
                centerX - halfLaneWidth, centerY + halfLaneWidth,
                centerX + halfLaneWidth, centerY + halfLaneWidth,
                centerX + halfLaneWidth, centerY - halfLaneWidth,
                centerX + halfLaneWidth, coY);
    }

    private void setPatrhStright() {
        pathStright.getPoints().addAll(coX, centerY + halfLaneWidth,
                coX + size, centerY + halfLaneWidth);
    }

    public Polyline getPathToMove(String from_to) {

        Polyline pathToMove = new Polyline();

        switch(from_to) {

            // going stright
            case "W_E":
                pathToMove =  pathStright;
                break;
            case "N_S":
                pathStright.getTransforms().add(new Rotate(90, centerX, centerY));
                pathToMove =  pathStright;
                break;
            case "E_W":
                pathStright.getTransforms().add(new Rotate(180, centerX, centerY));
                pathToMove =  pathStright;
                break;
            case "S_N":
                pathStright.getTransforms().add(new Rotate(270, centerX, centerY));
                pathToMove =  pathStright;
                break;

                // turns left
            case "W_N":
                pathToMove =  pathLeft;
                break;
            case "N_E":
                pathLeft.getTransforms().add(new Rotate(90, centerX, centerY));
                pathToMove =  pathLeft;
                break;
            case "E_S":
                pathLeft.getTransforms().add(new Rotate(180, centerX, centerY));
                pathToMove =  pathLeft;
                break;
            case "S_W":
                pathLeft.getTransforms().add(new Rotate(270, centerX, centerY));
                pathToMove =  pathLeft;
                break;

                // turns right
            case "W_S":
                return pathRight;
            case "N_W":
                pathRight.getTransforms().add(new Rotate(90, centerX, centerY));
                pathToMove = pathRight;
                break;
            case "E_N":
                pathRight.getTransforms().add(new Rotate(180, centerX, centerY));
                pathToMove = pathRight;
                break;
            case "S_E":
                pathRight.getTransforms().add(new Rotate(270, centerX, centerY));
                pathToMove = pathRight;
                break;
        }

        return pathToMove;

    }

    public RoadPuzzle(int coX, int coY, RoadType type) {
        this.size = 256;

        this.roadType = type.getPossibleDirection();
        this.coX = coX;
        this.coY = coY;

    }
}
