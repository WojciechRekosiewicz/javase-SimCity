package sincity.model;

import java.util.Random;

public class Vehicle {
    private double MAX_SPEED;
    private double speed;
    private boolean size;
    //private Enum dire;
    private int[] puzzle;
    private double roadPosition;
    private int image;

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

    private void chooseDirection() {

    }

}
