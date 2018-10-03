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
    private double MAX_SPEED;
    private double speed;
    private boolean size;
    private int[] puzzle;
    private double roadPosition;
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

    public void setCarImage(Image carImage) {
        this.carImage = carImage;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }


    enum direction {
        NORTH(),
        SOUTH(),
        WEST(),
        EAST()
    }

    private void chooseImageRandomly() {
        //   int imageNumber = getImageNumber();
        Random generator = new Random();
        imageNumber = generator.nextInt(7);
        setImageNumber(imageNumber);
    }

    private void setTheImageOfTheCar() {
        int imageNumber = getImageNumber();

        if (imageNumber == 0) {
            Image carImage = new Image("car_00.png");
            setCarImage(carImage);
        } else if (imageNumber == 1) {
            Image carImage = new Image("car_01.png");
            setCarImage(carImage);
        } else if (imageNumber == 2) {
            Image carImage = new Image("car_02.png");
            setCarImage(carImage);
        } else if (imageNumber == 3) {
            Image carImage = new Image("car_03.png");
            setCarImage(carImage);
        } else if (imageNumber == 4) {
            Image carImage = new Image("car_04.png");
            setCarImage(carImage);
        } else if (imageNumber == 5) {
            Image carImage = new Image("car_05.png");
            setCarImage(carImage);
        } else if (imageNumber == 6) {
            Image carImage = new Image("car_06.png");
            setCarImage(carImage);
        } else if (imageNumber == 7) {
            Image carImage = new Image("car_07.png");
            setCarImage(carImage);
        }
    }

    private void chooseDirection() {

    }

}
