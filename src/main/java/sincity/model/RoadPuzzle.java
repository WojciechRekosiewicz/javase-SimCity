package sincity.model;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class RoadPuzzle {

    //  0.6 i 0.4 MAGIC NUMBERS!!!! NEED CHANGE THAT

    boolean N;
    boolean S;
    boolean E;
    boolean W;

    double SIZE;

    double coX;
    double coY;

    Line pathE_W = new Line(coX+SIZE, coY+0.4*SIZE, coX, coY+0.4*SIZE );
    Line pathW_E = new Line(coX, coY+0.6*SIZE, coX+SIZE, coY+0.6*SIZE );
    Line pathS_N = new Line (coX + 0.6*SIZE, coY+SIZE,coX + 0.6*SIZE, coY);
    Line pathN_S = new Line (coX + 0.4*SIZE, coY,coX + 0.4*SIZE, coY+SIZE);

    Polyline pathE_N = new Polyline();
    Polyline pathE_S = new Polyline();
    Polyline pathW_N = new Polyline();
    Polyline pathW_S = new Polyline();
    Polyline pathN_W = new Polyline();
    Polyline pathN_E = new Polyline();
    Polyline pathS_E = new Polyline();
    Polyline pathS_W = new Polyline();

    private void setPathE_N() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + SIZE, coY + 0.4 * SIZE,
                coX +0.6*SIZE, coY + 0.4 * SIZE,
                coX + 0.6*SIZE, coY,

        });
    }

    public Polyline getPathE_N() {
        return pathE_N;
    }

    private void setPathE_S() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + SIZE, coY + 0.4 * SIZE,
                coX +0.6*SIZE, coY + 0.4 * SIZE,
                coX +0.4*SIZE, coY + 0.4 * SIZE,
                coX + 0.4*SIZE, coY+0.6*SIZE,
                coX + 0.4*SIZE, coY+ SIZE,

        });
    }

    public Polyline getPathE_S() {
        return pathE_S;
    }

    private void setPathN_W() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + 0.4*SIZE, coY,
                coX +0.4*SIZE, coY + 0.4 * SIZE,
                coX, coY+0.4*SIZE

        });
    }

    public Polyline getPathN_W() {
        return pathN_W;
    }

    private void setPathN_E() {
        pathE_N.getPoints().addAll(new Double[]{
                coX +0.4*SIZE, coY,
                coX +0.4*SIZE, coY + 0.4* SIZE,
                coX +0.4*SIZE, coY + 0.6* SIZE,
                coX+0.6*SIZE, coY+0.6*SIZE,
                coX+SIZE, coY+0.6*SIZE,

        });
    }

    public Polyline getPathN_E() {
        return pathN_E;
    }


    private void setPathS_E() {
        pathE_N.getPoints().addAll(new Double[]{
                coX + 0.6*SIZE, coY+SIZE,
                coX +0.6*SIZE, coY + 0.6 * SIZE,
                coX + SIZE, coY+0.6*SIZE

        });
    }

    public Polyline getPathS_E() {
        return pathS_E;
    }

    private void setPathS_W() {
        pathE_N.getPoints().addAll(new Double[]{
                coX +0.6*SIZE, coY+SIZE,
                coX +0.6*SIZE, coY + 0.6* SIZE,
                coX +0.6*SIZE, coY + 0.4* SIZE,
                coX+0.4*SIZE, coY+0.4*SIZE,
                coX, coY+0.4*SIZE,

        });
    }

    public Polyline getPathS_W() {
        return pathS_W;
    }

    private void setPathW_N() {
        pathE_N.getPoints().addAll(new Double[]{
                coX, coY + 0.6 * SIZE,
                coX +0.4*SIZE, coY + 0.6 * SIZE,
                coX +0.6*SIZE, coY + 0.6 * SIZE,
                coX +0.6*SIZE, coY + 0.4 * SIZE,
                coX + 0.6*SIZE, coY,

        });
    }

    public Polyline getPathW_N() {
        return pathW_N;
    }

    private void setPathW_S() {
        pathE_N.getPoints().addAll(new Double[]{
                coX, coY + 0.6 * SIZE,
                coX +0.4*SIZE, coY + 0.6 * SIZE,
                coX +0.4*SIZE, coY + SIZE,
        });
    }

    public Polyline getPathW_S() {
        return pathW_S;
    }

    public RoadPuzzle(int coX, int coY, boolean N, boolean S, boolean E, boolean W) {
        this.SIZE = 256;

        this.N = N;
        this.S = S;
        this.W = W;
        this.E = E;

        this.coX = coX;
        this.coY = coY;

    }


}
