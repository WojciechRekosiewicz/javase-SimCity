package sincity.model;

import javafx.scene.image.Image;

import java.util.Random;

public class Vehicle {
    private double MAX_SPEED;
    private double speed;
    private boolean size;
    //private Enum dire;
    private int[] puzzle;
    private double roadPosition;
    private int image;
    private Image carImage;

    public Image getCarImage() {
        return carImage;
    }

    public void setCarImage(Image carImage) {
        this.carImage = carImage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void move() {

    }

    private void chooseImageRandomly() {
        //   int image = getImage();
        Random generator = new Random();
        image = generator.nextInt(7);
        setImage(image);
    }

    private void setTheImageOfTheCar() {
        int numOfImage = getImage();

        if (numOfImage == 0) {
            Image carImage = new Image("car_00.png");
            setCarImage(carImage);
        } else if (numOfImage == 1) {
            Image carImage = new Image("car_01.png");
            setCarImage(carImage);
        } else if (numOfImage == 2) {
            Image carImage = new Image("car_02.png");
            setCarImage(carImage);
        } else if (numOfImage == 3) {
            Image carImage = new Image("car_03.png");
            setCarImage(carImage);
        } else if (numOfImage == 4) {
            Image carImage = new Image("car_04.png");
            setCarImage(carImage);
        } else if (numOfImage == 5) {
            Image carImage = new Image("car_05.png");
            setCarImage(carImage);
        } else if (numOfImage == 6) {
            Image carImage = new Image("car_06.png");
            setCarImage(carImage);
        } else if (numOfImage == 7) {
            Image carImage = new Image("car_07.png");
            setCarImage(carImage);
        }

    }

    private void chooseDirection() {

    }

}
