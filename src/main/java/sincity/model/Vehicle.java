package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.Random;

public class Vehicle {
    RoadPuzzle currentRoadPuzzle;
    double maxSpeed;
    double speed;
    double size;
    double roadPosition;
    private int imageNumber;
    private Image carImage;

    Vehicle(RoadPuzzle roadPuzzle) {
        this.currentRoadPuzzle = roadPuzzle;
    }

    private void move(Line path, String image) {
        Image carImage = new Image("file:src/main/resources/" + image);
        ImageView imageView = new ImageView(carImage);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setNode(imageView);
        pathTransition.setPath(path);
        pathTransition.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(5000);
        pathTransition.setAutoReverse(false);
        pathTransition.play();
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
