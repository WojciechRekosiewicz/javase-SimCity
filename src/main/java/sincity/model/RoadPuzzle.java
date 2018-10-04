package sincity.model;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class RoadPuzzle {

    //  0.6 i 0.4 MAGIC NUMBERS!!!! NEED CHANGE THAT

    boolean[] possibleDirections;
    double size;

    double coX;

    double coY;
    Line pathE_W = new Line(coX + size, coY + 0.4 * size, coX, coY + 0.4 * size);
    Line pathW_E = new Line(coX, coY + 0.6 * size, coX + size, coY + 0.6 * size);
    Line pathS_N = new Line(coX + 0.6 * size, coY + size, coX + 0.6 * size, coY);
    Line pathN_S = new Line(coX + 0.4 * size, coY, coX + 0.4 * size, coY + size);
    private RoadType roadType;
    Polyline pathE_N = new Polyline();

    Polyline pathE_S = new Polyline();
    Polyline pathW_N = new Polyline();
    Polyline pathW_S = new Polyline();
    Polyline pathN_W = new Polyline();
    Polyline pathN_E = new Polyline();
    Polyline pathS_E = new Polyline();
    Polyline pathS_W = new Polyline();

    public RoadPuzzle(double coX, double coY, double size, RoadType type) {
        this.roadType = type;
        this.size = size;
        this.possibleDirections = type.getPossibleDirection();
        this.coX = coX;
        this.coY = coY;

    }

    public RoadType getRoadType() {
        return roadType;
    }

    public Polyline getPathE_N() {
        return pathE_N;
    }

    private void setPathE_N() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + size, coY + 0.4 * size,
                coX + 0.6 * size, coY + 0.4 * size,
                coX + 0.6 * size, coY,

        });
    }

    public Polyline getPathE_S() {
        return pathE_S;
    }

    private void setPathE_S() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + size, coY + 0.4 * size,
                coX + 0.6 * size, coY + 0.4 * size,
                coX + 0.4 * size, coY + 0.4 * size,
                coX + 0.4 * size, coY + 0.6 * size,
                coX + 0.4 * size, coY + size,

        });
    }

    public Polyline getPathN_W() {
        return pathN_W;
    }

    private void setPathN_W() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + 0.4 * size, coY,
                coX + 0.4 * size, coY + 0.4 * size,
                coX, coY + 0.4 * size

        });
    }

    public Polyline getPathN_E() {
        return pathN_E;
    }

    private void setPathN_E() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + 0.4 * size, coY,
                coX + 0.4 * size, coY + 0.4 * size,
                coX + 0.4 * size, coY + 0.6 * size,
                coX + 0.6 * size, coY + 0.6 * size,
                coX + size, coY + 0.6 * size,

        });
    }

    public Polyline getPathS_E() {
        return pathS_E;
    }

    private void setPathS_E() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + 0.6 * size, coY + size,
                coX + 0.6 * size, coY + 0.6 * size,
                coX + size, coY + 0.6 * size

        });
    }

    public Polyline getPathS_W() {
        return pathS_W;
    }

    private void setPathS_W() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + 0.6 * size, coY + size,
                coX + 0.6 * size, coY + 0.6 * size,
                coX + 0.6 * size, coY + 0.4 * size,
                coX + 0.4 * size, coY + 0.4 * size,
                coX, coY + 0.4 * size,

        });
    }

    public Polyline getPathW_N() {
        return pathW_N;
    }

    private void setPathW_N() {
        pathE_N.getPoints().addAll(new Double[]{
                coX, coY + 0.6 * size,
                coX + 0.4 * size, coY + 0.6 * size,
                coX + 0.6 * size, coY + 0.6 * size,
                coX + 0.6 * size, coY + 0.4 * size,
                coX + 0.6 * size, coY,

        });
    }

    public Polyline getPathW_S() {
        return pathW_S;
    }

    private void setPathW_S() {
        pathE_N.getPoints().addAll(new Double[]{
                coX, coY + 0.6 * size,
                coX + 0.4 * size, coY + 0.6 * size,
                coX + 0.4 * size, coY + size,
        });
    }


}
