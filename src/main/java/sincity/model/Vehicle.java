package sincity.model;

import javafx.scene.image.Image;

import java.util.Random;

public class Vehicle {
    private double MAX_SPEED;
    private double speed;
    private boolean size;
    //private Enum direction;
    private int[] puzzle;
    private double roadPosition;
    private int imageNumber;
    private Image carImage;

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

    public void move() {

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
