package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Vehicle {
    double maxSpeed;
    double speed;
    boolean size;
    int[] puzzle;
    double roadPosition;
    private int imageNumber;
    private Image carImage;

    private void move() {
        Rectangle rectangle = new Rectangle(15, 10);
        rectangle.setFill(Color.GREEN);
        Path path = new Path();
        MoveTo moveTo = new MoveTo(10, 10);
        LineTo line1 = new LineTo(100, 10);
        LineTo line2 = new LineTo(100, 100);
        LineTo line3 = new LineTo(10, 100);
        LineTo line4 = new LineTo(10, 10);
        LineTo line5 = new LineTo(300, 250);
        path.getElements().add(moveTo);
        path.getElements().addAll(line1, line2, line3, line4);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(10));
        pathTransition.setNode(rectangle);
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
