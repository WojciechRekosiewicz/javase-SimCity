package sincity.model;

import javafx.scene.image.Image;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import sincity.view.Renderer;


class Vehicle {
    RoadPuzzle currentRoadPuzzle;
    double maxSpeed;
    double speed;
    double size;
    private Direction arrivalDirection = Direction.W;
    double roadPosition;
    private int imageNumber;
    private Image carImage;
    private Renderer renderer;

    Vehicle(Renderer renderer, RoadPuzzle roadPuzzle) {
        this.renderer = renderer;
        this.currentRoadPuzzle = roadPuzzle;
        move(roadPuzzle);
    }

    private void move(RoadPuzzle puzzle) {
        Direction outDir = getRandomOutDirection(puzzle.getRoadDirections());
        String fromTo = arrivalDirection.toString() + "_" + outDir.toString();
        Path pathToMove = puzzle.getPathToMove(fromTo);
        String imageUrl = "car_03.png";
        renderer.renderVehicle(imageUrl, pathToMove);
    }

    private Direction getRandomOutDirection(boolean[] directions) {
        Direction[] allDirections = new Direction[]{Direction.E, Direction.N, Direction.S, Direction.W};
        int randomIndex;
        do {
            randomIndex = (int) Math.floor(Math.random() * allDirections.length);
        } while (!directions[randomIndex] || allDirections[randomIndex].equals(arrivalDirection));
        return allDirections[randomIndex];
    }

    private void setCarImage(Image carImage) {
        this.carImage = carImage;
    }

    private void setTheImageOfTheCar(VehicleType vehicleType, int numberOfVehicle) {
        int randomImageNumber = (int) Math.floor(Math.random() * numberOfVehicle);
        Image carImage = new Image(vehicleType.getName() + "_0" + randomImageNumber + ".png");
        setCarImage(carImage);
    }

}
