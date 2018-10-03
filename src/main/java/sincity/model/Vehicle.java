package sincity.model;

import javafx.scene.image.Image;

import java.util.Random;

public class Vehicle {
    double maxSpeed;
    double speed;
    boolean size;
    int[] puzzle;
    double roadPosition;
    private int imageNumber;
    private Image carImage;

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
}
