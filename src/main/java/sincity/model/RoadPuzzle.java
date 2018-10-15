package sincity.model;

import java.util.LinkedList;
import java.util.Queue;

class RoadPuzzle {

    private boolean[] roadDirections;
    private RoadType roadType;

    private int indexX; // position in City board
    private int indexY;

    private double size;

    private double coX; // position on Scene
    private double coY;

    double halfLaneWidth;

    private double centerY;
    private double centerX; // center of the puzzle - anchor point of path rotation

    Queue<Vehicle> northVehicleList = new LinkedList<>();
    Queue<Vehicle> southvehicleList = new LinkedList<>();
    Queue<Vehicle> westVehicleList = new LinkedList<>();
    Queue<Vehicle> eastVehicleList = new LinkedList<>();




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
        this.halfLaneWidth = 0.1 * size;     // maybe should be final
    }

    double getCoX() {
        return coX;
    }

    double getCoY() {
        return coY;
    }

    double getSize() {
        return size;
    }

    int getIndexY() {
        return indexY;
    }

    int getIndexX() {
        return indexX;
    }

    double getCenterX() {
        return centerX;
    }

    double getCenterY() {
        return centerY;
    }

    boolean[] getRoadDirections() {
        return roadDirections;
    }

    RoadType getRoadType() {
        return roadType;
    }
}
