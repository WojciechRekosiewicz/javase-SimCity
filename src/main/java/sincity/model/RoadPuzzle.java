package sincity.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    List<Vehicle> northVehicleList = new ArrayList<>();
    List<Vehicle> southVehicleList = new ArrayList<>();
    List<Vehicle> westVehicleList = new ArrayList<>();
    List<Vehicle> eastVehicleList = new ArrayList<>();

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

    void addVehicleToList(Vehicle vehicle, Direction direction) {
        switch (direction) {
            case E:
                eastVehicleList.add(vehicle);
                break;
            case N:
                northVehicleList.add(vehicle);
                break;
            case S:
                southVehicleList.add(vehicle);
                break;
            case W:
                westVehicleList.add(vehicle);
                break;
        }
    }

    void removeLastVehicleFromList(Vehicle vehicle, Direction direction) {
        if (direction != null) {
            switch (direction) {
                case E:
                    eastVehicleList.remove(vehicle);
                    break;
                case N:
                    northVehicleList.remove(vehicle);
                    break;
                case S:
                    southVehicleList.remove(vehicle);
                    break;
                case W:
                    westVehicleList.remove(vehicle);
                    break;
            }
        }
    }
}
