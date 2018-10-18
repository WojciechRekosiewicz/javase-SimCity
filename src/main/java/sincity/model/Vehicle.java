package sincity.model;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import sincity.view.Renderer;
import sincity.view.VehicleDisplay;

import java.util.*;


public class Vehicle implements Observer {


    double speed; // 1 is default
    private RoadPuzzle currentRoadPuzzle;
    private Direction arrivalDirection;
    private Direction outDirection;
    private City city;
    private Renderer renderer;
    private VehicleDisplay vehicleDisplay;
    private PathTransition pathTransition;
    private Color color;


    Vehicle(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection, VehicleType vehicleType) {
        this.color = Color.color(Math.random(), Math.random(), Math.random(), 1);
        this.renderer = renderer;
        this.currentRoadPuzzle = roadPuzzle;
        this.arrivalDirection = arrivalDirection;
        this.vehicleDisplay = renderer.renderVehicle(vehicleType);
        this.city = city;
        move();
    }

    public void updateVehicle() {
        Vehicle carInFront = findCarInFront();

        if (carInFront != null) {
            renderer.RenderTestLine(vehicleDisplay.getCenterX(), vehicleDisplay.getCenterY(),
                    carInFront.vehicleDisplay.getCenterX(), carInFront.vehicleDisplay.getCenterY(), color);
        } else {
            renderer.RemoveTestLine(color);
        }

        pathTransition.setRate(speed);
    }

    private void move() {

        addToCorrectList();
        outDirection = getRandomOutDirection(currentRoadPuzzle.getRoadDirections());
        String fromTo = arrivalDirection.toString() + "_" + outDirection.toString();
        PathToMove pathToMove = new PathToMove(currentRoadPuzzle, fromTo);

        if (currentRoadPuzzle.isTrafficLight()) {

            TrafficLights[] lights = currentRoadPuzzle.getTrafficLights();
            for (TrafficLights light : lights) {
                if (arrivalDirection.getOrientation() == light.getOrientation()) {
                    if (light.currentColor == LightColor.GREEN) {
                        //DO NOTHING
                    } else {
                        light.addObserver(this);
                        speed = 0;  //add setter for speed

                    }

                }
            }
        }

        pathTransition = renderer.moveAnimation(vehicleDisplay, pathToMove, speed);

        pathTransition.setOnFinished(event -> {
            removeFromCorrectList();
            changeRoadPuzzle(currentRoadPuzzle);
            if (currentRoadPuzzle != null) {
                move();
            }
        });
    }

    private void addToCorrectList() {
        currentRoadPuzzle.addVehicleToList(this, arrivalDirection);
    }

    private void removeFromCorrectList() {
        currentRoadPuzzle.removeLastVehicleFromList(this, arrivalDirection);
    }

    private Vehicle findCarInFront() {
        if (currentRoadPuzzle == null) {
            return null;
        }

        RoadPuzzle nextRoadPuzzle = findNextPuzzle(currentRoadPuzzle, outDirection);

        List<Vehicle> vehicleList = null;
        List<Vehicle> nextVehicleList = null;

        switch (arrivalDirection) {
            case E:
                vehicleList = currentRoadPuzzle.eastVehicleList;
                nextVehicleList = nextRoadPuzzle != null ? nextRoadPuzzle.eastVehicleList : null;
                break;
            case N:
                vehicleList = currentRoadPuzzle.northVehicleList;
                nextVehicleList = nextRoadPuzzle != null ? nextRoadPuzzle.northVehicleList : null;
                break;
            case S:
                vehicleList = currentRoadPuzzle.southVehicleList;
                nextVehicleList = nextRoadPuzzle != null ? nextRoadPuzzle.southVehicleList : null;
                break;
            case W:
                vehicleList = currentRoadPuzzle.westVehicleList;
                nextVehicleList = nextRoadPuzzle != null ? nextRoadPuzzle.westVehicleList : null;
                break;
        }

        List<Vehicle> combinedVehicleList = new ArrayList<>();

        if (nextVehicleList != null) {
            combinedVehicleList.addAll(nextVehicleList);
        }

        combinedVehicleList.addAll(vehicleList);

        if (combinedVehicleList.indexOf(this) > 0) {
            Vehicle carInFront = combinedVehicleList.get(combinedVehicleList.indexOf(this) - 1);
            boolean fromSameDirection = this.arrivalDirection == carInFront.arrivalDirection; // tweak these values to get the best result
            boolean toSameDirection = this.outDirection == carInFront.outDirection;
            return fromSameDirection || toSameDirection ? carInFront : null;
        } else {
            return null;
        }
    }

    private Direction getRandomOutDirection(HashMap<Direction, Boolean> possibleDirections) {
        int randomIndex;
        boolean isChosenDirection;
        Direction chosen;
        do {
            randomIndex = (int) Math.floor(Math.random() * 4);  // number of directions
            chosen = Direction.values()[randomIndex];
            isChosenDirection = possibleDirections.get(chosen);
        } while (!isChosenDirection || Direction.values()[randomIndex].equals(arrivalDirection));
        return chosen;
    }

    private void changeRoadPuzzle(RoadPuzzle puzzle) {
        changeArrivalDirection(outDirection);
        currentRoadPuzzle = findNextPuzzle(puzzle, outDirection);
    }

    private RoadPuzzle findNextPuzzle(RoadPuzzle puzzle, Direction outDir) {
        int currentX = puzzle.getIndexX();
        int currentY = puzzle.getIndexY();

        int nextPuzzleIndexX = currentX;
        int nextPuzzleIndexY = currentY;

        switch (outDir) {
            case W:
                nextPuzzleIndexX = currentX - 1;
                break;
            case E:
                nextPuzzleIndexX = currentX + 1;
                break;
            case N:
                nextPuzzleIndexY = currentY - 1;
                break;
            case S:
                nextPuzzleIndexY = currentY + 1;
                break;
        }

        try {
            return city.getPuzzleBoard()[nextPuzzleIndexX][nextPuzzleIndexY];
        } catch (Exception e) {
            return null;
        }
    }

    private void changeArrivalDirection(Direction outDirection) {
        switch (outDirection) {
            case W:
                arrivalDirection = Direction.E;
                break;
            case E:
                arrivalDirection = Direction.W;
                break;
            case N:
                arrivalDirection = Direction.S;
                break;
            case S:
                arrivalDirection = Direction.N;
                break;
        }
    }

    @Override
    public void update(Observable o, Object lightColor) {
        if ((LightColor) lightColor == LightColor.GREEN) {
            speed = 0.5;
        }

    }
}