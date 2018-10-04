package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Vehicle {
    RoadPuzzle currentRoadPuzzle;
    double maxSpeed;
    double speed;
    double size;
    public Direction direction = Direction.E;
    double roadPosition;
    private int imageNumber;
    private Image carImage;
    int[] puzzleIndicies;

    Vehicle(RoadPuzzle roadPuzzle) {
        this.currentRoadPuzzle = roadPuzzle;
    }

    private void move(RoadPuzzle puzzle) {


//        puzzle.getRoadDirections();
        Direction outDir = getRandomOutDirection(puzzle.getRoadDirections());
        String FromTo = direction.toString() + "_" + outDir.toString();
        Polyline pathToMove = puzzle.getPathToMove(FromTo);


        Image carImage = new Image("file:src/main/resources/" + image);
        ImageView imageView = new ImageView(carImage);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(15));
        pathTransition.setNode(imageView);
        pathTransition.setPath(pathToMove);
        pathTransition.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(5000);
        pathTransition.setAutoReverse(false);
        pathTransition.play();
    }


    private Direction getRandomOutDirection(boolean[] directions) {
        Direction[] allDirections = new Direction[]{Direction.E, Direction.N, Direction.S, Direction.W};
        int rnd;
        do {
            rnd = new Random().nextInt(directions.length);
        } while (!directions[rnd] || notSameDirection(allDirections[rnd]));
        rnd = new Random().nextInt(directions.length);

        return allDirections[rnd];
    }

    boolean notSameDirection(Direction chosen) {
        if (this.direction.equals(Direction.E) && chosen.equals((Direction.W))) {
            return false;
        }
        if (this.direction.equals(Direction.W) && chosen.equals((Direction.E))) {
            return false;
        }
        if (this.direction.equals(Direction.N) && chosen.equals((Direction.S))) {
            return false;
        }
        if (this.direction.equals(Direction.S) && chosen.equals((Direction.N))) {
            return false;
        }
        return true;
    }


    private void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {
            //traffic light
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Image getCarImage() {
        return carImage;
    }

    private void setCarImage(Image carImage) {
        this.carImage = carImage;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    private void setTheImageOfTheCar(VehicleType vehicleType, int numberOfVehicle) {
        int randomImageNumber = (int) Math.floor(Math.random() * numberOfVehicle);
        Image carImage = new Image(vehicleType.getName() + "_0" + randomImageNumber + ".png");
        setCarImage(carImage);
    }

    private void chooseDirection() {

    }

}
