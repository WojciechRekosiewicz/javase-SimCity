package sincity.model;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import sincity.view.Renderer;
import sincity.view.VehicleDisplay;

import java.util.*;


public class Vehicle implements Observer, Runnable {


    double topSpeed; // 1 is default
    double currentSpeed;
    private boolean isStopped;
    private RoadPuzzle currentRoadPuzzle;
    private Direction arrivalDirection;
    private Direction outDirection;
    private City city;
    private Renderer renderer;
    private VehicleDisplay vehicleDisplay;
    private PathTransition pathTransition;
    private Color color;
    PathToMove pathToMove;
    String shape;


    Vehicle(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection, VehicleType vehicleType) {
        this.color = Color.color(Math.random(), Math.random(), Math.random(), 1);
        this.renderer = renderer;
        this.currentRoadPuzzle = roadPuzzle;
        this.arrivalDirection = arrivalDirection;
        this.vehicleDisplay = renderer.renderVehicle(vehicleType, this);
        this.city = city;
    }

    public void setOutDirection() {
        // TODO redundant
        changeDirectionToOpposite(outDirection);
        String fromTo = arrivalDirection.toString() + "_" + outDirection.toString();
        pathToMove = new PathToMove(currentRoadPuzzle, fromTo);
        shape = pathToMove.getShape();
        tryToMove();

    }

    public void updateVehicle() {
        Vehicle carInFront = findCarInFront();

        if (carInFront != null) {

            double distanceToCarInFront = Math.sqrt(Math.pow(carInFront.vehicleDisplay.getCenterX() - vehicleDisplay.getCenterX(), 2)
                    + Math.pow(carInFront.vehicleDisplay.getCenterY() - vehicleDisplay.getCenterY(), 2));

            double distanceToStop = Math.max(vehicleDisplay.getWidth(), carInFront.vehicleDisplay.getWidth()) + 10;


            if (isStopped || distanceToCarInFront < distanceToStop) {
                currentSpeed = 0.001; // TODO magic number
            } else {
                currentSpeed = topSpeed;
            }


        } else {
            currentSpeed = !isStopped ? topSpeed : 0.001;
            renderer.RemoveTestLine(color);
        }

        pathTransition.setRate(currentSpeed);
    }

    private void move() {

        addToCorrectList();
        outDirection = getRandomOutDirection(currentRoadPuzzle.getRoadDirections());
        String fromTo = arrivalDirection.toString() + "_" + outDirection.toString();
        pathToMove = new PathToMove(currentRoadPuzzle, fromTo);

        shape = pathToMove.getShape();

        tryToMove();
    }

    public void tryToMove() {

        currentRoadPuzzle.addObserver(this);

        if (isMovePossible()) {


            currentSpeed = topSpeed;
            isStopped = false;
            pathTransition = renderer.moveAnimation(vehicleDisplay, pathToMove, currentSpeed);
            RoadPuzzle previous = currentRoadPuzzle;


            pathTransition.setOnFinished(event -> {
                removeFromCorrectList();
                changeRoadPuzzle(currentRoadPuzzle);
                previous.deleteObserver(this);
                if (currentRoadPuzzle != null) {
                    move();
                } else {
                    vehicleDisplay.getChildren().removeAll();
                    Cleaner.destroyCar(this);
                }
            });
            previous.deleteObserver(this);

        }
    }

    private boolean isMovePossible() {

        if (currentRoadPuzzle.hasTrafficLight()) {

            TrafficLights[] lights = currentRoadPuzzle.getTrafficLights();
            for (TrafficLights light : lights) {
                if (arrivalDirection.getOrientation() == light.getOrientation()) {
                    light.addObserver(this);
                    if (light.currentColor == LightColor.GREEN) {

                        if (shape.equals("left")) {
                            if (checkIsMoveLeftPossible()) {
                                isStopped = false;
                                light.deleteObserver(this);
                                return true;
                            } else {
                                isStopped = true;
                                return false;
                            }
                        } else {
                            isStopped = false;
                            light.deleteObserver(this);
                            return true;
                        }
                    } else {
                        isStopped = true;  //add setter for currentSpeed
                        return false;

                    }
                }
            }
        } else {
            return setPriority(shape);
        }
        return false;

    }


    private boolean setPriority(String shape) {
        // TODO don't use string!
        if (shape.equals("right")) {
            return true;
        } else if (shape.equals("straight")) {
            if (arrivalDirection == Direction.N) {
                if (currentRoadPuzzle.westVehicleList.isEmpty()) {
                    currentSpeed = topSpeed;
                    return true;
                }
            } else if (arrivalDirection == Direction.E) {
                if (currentRoadPuzzle.northVehicleList.isEmpty()) {
                    currentSpeed = topSpeed;
                    return true;
                }
            } else if (arrivalDirection == Direction.S) {
                if (currentRoadPuzzle.eastVehicleList.isEmpty()) {
                    currentSpeed = topSpeed;
                    return true;
                }
            } else if (arrivalDirection == Direction.W) {
                if (currentRoadPuzzle.southVehicleList.isEmpty()) {
                    currentSpeed = topSpeed;
                    return true;
                }
            }
        } else if (shape.equals("left")) {
            return checkIsMoveLeftPossible();
        }
        return false;
    }

    private boolean checkIsMoveLeftPossible() {

        List<Vehicle> oppositeQueue = currentRoadPuzzle.getOppositeQueue(arrivalDirection);
        List<Vehicle> rightHandQueue = currentRoadPuzzle.getRightHandQueue(arrivalDirection);


        boolean crossingWithTrafficLights = currentRoadPuzzle.hasTrafficLight();
        boolean oppositeVehicleTurnsLeft = oppositeQueue.size() == 0 || oppositeQueue.get(0).shape.equals("left");


        if ((crossingWithTrafficLights && oppositeVehicleTurnsLeft)
                || (!crossingWithTrafficLights && (rightHandQueue.isEmpty()) && (oppositeQueue.isEmpty() || oppositeVehicleTurnsLeft))) {
            currentSpeed = topSpeed;
            return true;
        }
        return false;
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
            return fromSameDirection ? carInFront : null;
        } else {
            return null;
        }
    }

    private Direction getRandomOutDirection(HashMap<Direction, Boolean> possibleDirections) {
        int randomIndex;
        boolean isChosenDirection;
        Direction chosen;
        do {
            randomIndex = (int) Math.floor(Math.random() * Direction.values().length);  // number of directions
            chosen = Direction.values()[randomIndex];
            isChosenDirection = possibleDirections.get(chosen);
        } while (!isChosenDirection || Direction.values()[randomIndex].equals(arrivalDirection));
        return chosen;
    }

    private void changeRoadPuzzle(RoadPuzzle puzzle) {
        changeDirectionToOpposite(outDirection);
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

    private void changeDirectionToOpposite(Direction outDirection) {
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
        if (lightColor == LightColor.GREEN) {
            isStopped = false;
        }
        tryToMove();
    }


    @Override
    public void run() {
        this.move();
    }

}


