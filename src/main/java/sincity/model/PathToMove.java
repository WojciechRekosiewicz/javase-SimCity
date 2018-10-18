package sincity.model;

import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;

class PathToMove extends Path {

    private RoadPuzzle puzzle;
    private String fromTo;
    private String shape;

    PathToMove(RoadPuzzle puzzle, String fromTo) {
        this.puzzle = puzzle;
        this.fromTo = fromTo;
        setPathShape();
        setPathDirection();
    }

    String getShape() {
        return shape;
    }

    private void setPathRight() {

        MoveTo moveTo = new MoveTo();
        moveTo.setX(puzzle.getCoX());
        moveTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);

        LineTo lineTo = new LineTo();
        lineTo.setX(puzzle.getCenterX() - 2 * puzzle.halfLaneWidth);
        lineTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(puzzle.getCenterX() - puzzle.halfLaneWidth);
        arcTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);
        arcTo.setRadiusX(puzzle.getSize()/2 - puzzle.halfLaneWidth);
        arcTo.setRadiusY(puzzle.getSize()/2 - puzzle.halfLaneWidth);
        arcTo.setLargeArcFlag(false);
        arcTo.setSweepFlag(true);

        LineTo lineTo2 = new LineTo();
        lineTo2.setX(puzzle.getCenterX() - puzzle.halfLaneWidth);
        lineTo2.setY(puzzle.getCoY() + puzzle.getSize());

        this.getElements().add(moveTo);
        this.getElements().add(lineTo);
        this.getElements().add(arcTo);
        this.getElements().add(lineTo2);

    }

    private void setPathLeft() {

        MoveTo moveTo = new MoveTo();
        moveTo.setX(puzzle.getCoX());
        moveTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);

        LineTo lineTo = new LineTo();
        lineTo.setX(puzzle.getCenterX() - puzzle.halfLaneWidth);
        lineTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);

        ArcTo arcTo = new ArcTo();
        arcTo.setX(puzzle.getCenterX() + puzzle.halfLaneWidth);
        arcTo.setY(puzzle.getCenterY() - puzzle.halfLaneWidth);
        arcTo.setRadiusX(3 * puzzle.halfLaneWidth);
        arcTo.setRadiusY(3 * puzzle.halfLaneWidth);

        LineTo lineTo2 = new LineTo();
        lineTo2.setX(puzzle.getCenterX() + puzzle.halfLaneWidth);
        lineTo2.setY(puzzle.getCoY());

        this.getElements().add(moveTo);
        this.getElements().add(lineTo);
        this.getElements().add(arcTo);
        this.getElements().add(lineTo2);

    }

    private void setPathStraight() {

        MoveTo moveTo = new MoveTo();
        moveTo.setX(puzzle.getCoX());
        moveTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);

        LineTo lineTo = new LineTo();
        lineTo.setX(puzzle.getCoX() + puzzle.getSize());
        lineTo.setY(puzzle.getCenterY() + puzzle.halfLaneWidth);

        this.getElements().add(moveTo);
        this.getElements().add(lineTo);
    }

    private void setPathShape() {

        switch (fromTo) {
            case "N_W":
            case "S_E":
            case "W_S":
            case "E_N":
                this.setPathRight();
                shape = "right";
                break;
            case "N_E":
            case "S_W":
            case "W_N":
            case "E_S":
                this.setPathLeft();
                shape = "left";
                break;
            case "N_S":
            case "S_N":
            case "W_E":
            case "E_W":
                this.setPathStraight();
                shape = "straight";
                break;
        }
    }

    private void setPathDirection() {
        switch (fromTo) {
            case "N_W":
            case "N_S":
            case "N_E":
                this.getTransforms().add(new Rotate(90, puzzle.getCenterX(), puzzle.getCenterY()));
                break;
            case "E_N":
            case "E_W":
            case "E_S":
                this.getTransforms().add(new Rotate(180, puzzle.getCenterX(), puzzle.getCenterY()));
                break;
            case "S_E":
            case "S_N":
            case "S_W":
                this.getTransforms().add(new Rotate(270, puzzle.getCenterX(), puzzle.getCenterY()));
        }
    }



}
