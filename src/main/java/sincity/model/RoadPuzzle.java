package sincity.model;

import sincity.view.TrafficLightsDisplay;

public class RoadPuzzle {

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

    private boolean isTrafficLight;

    TrafficLights[] trafficLights;



    RoadPuzzle(int xIndex, int yIndex, int padding, double size, RoadType type, boolean isTrafficLights) {
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
        this.isTrafficLight = isTrafficLights;
        if (isTrafficLight){
            this.trafficLights = new TrafficLightGenerator(this).createLights();
        }
    }

    public boolean isTrafficLight() {
        return isTrafficLight;
    }

    public void setTrafficLight(boolean trafficLight) {
        isTrafficLight = trafficLight;
    }

    public TrafficLights[] getTrafficLights() {
        return trafficLights;
    }

    public double getCoX() {
        return coX;
    }

    public double getCoY() {
        return coY;
    }

    public double getSize() {
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

    public boolean[] getRoadDirections() {
        return roadDirections;
    }

    RoadType getRoadType() {
        return roadType;
    }
}
