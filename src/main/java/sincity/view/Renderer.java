package sincity.view;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sincity.model.City;
import sincity.model.RoadType;
//import sincity.model.Tank;
import sincity.model.Vehicle;

public class Renderer {
    private Group root;
    private City city;
    private double tileSize;
    private int verticalPuzzles;
    private int horizontalPuzzles;
    private int padding;

    public Renderer(Group root, City city, double tileSize, int verticalPuzzles, int horizontalPuzzles, int padding) {
        this.root = root;
        this.city = city;
        this.tileSize = tileSize;
        this.horizontalPuzzles = horizontalPuzzles + padding * 2;
        this.verticalPuzzles = verticalPuzzles + padding * 2;
        this.padding = padding;
    }

    public void renderCity() {
        root.getChildren().clear();
        for (int y = padding; y < verticalPuzzles - padding; y++) {
            for (int x = padding; x < horizontalPuzzles - padding; x++) {
                RoadType roadType = city.getRoadType(x, y);

                // set image based on roadType
                String imageUrl = "file:src/main/resources/" + roadType.getImageUrl();

                // choose image
                Image image = new Image(imageUrl, tileSize, tileSize, false, false);

                // create tile
                Tile tile = new Tile(x, y, tileSize, padding, image); // x and y are board indices

                // add tile to group
                root.getChildren().add(tile);
            }
        }
    }

    public VehicleDisplay renderVehicle() {
        // vehicle size
        double vehicleSize = tileSize * 0.37; // scale factor

        int randomImageNumber = (int) Math.floor(Math.random() * 8); // 8 is total number of vehicle images
        String imageUrl = ("car_" + randomImageNumber + ".png");

        // set image based on roadType
        Image vehicleImage = new Image("file:src/main/resources/" + imageUrl, vehicleSize, vehicleSize, true, false);
        // create vehicleDisplay
        VehicleDisplay vehicleDisplay = new VehicleDisplay(vehicleImage);

        // add vehicleDisplay to group
        root.getChildren().add(vehicleDisplay);

        return vehicleDisplay;
    }

    public TankDisplay renderTank() {

        double vehicleSize = tileSize * 0.45; // scale factor
        int randomImageNumber = (int) Math.floor(Math.random() * 1); // 8 is total number of vehicle images
        String imageUrl = ("tank_1.png");

        // set image based on roadType
        Image tankImage = new Image("file:src/main/resources/" + imageUrl, vehicleSize, vehicleSize, true, false);
        TankDisplay tankDisplay = new TankDisplay(tankImage);
        root.getChildren().add(tankDisplay);
        return tankDisplay;
    }

    public PathTransition moveAnimation(VehicleDisplay vehicleDisplay, Path pathToMove, double speed) {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setRate(speed); // 1 is default
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setNode(vehicleDisplay);
        pathTransition.setPath(pathToMove);
        pathTransition.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();
        return pathTransition;
    }

    public PathTransition moveAnimationTank(TankDisplay tankDisplay, Path pathToMove, double speed) {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setRate(speed); // 1 is default
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setNode(tankDisplay);
        pathTransition.setPath(pathToMove);
        pathTransition.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.setInterpolator(Interpolator.LINEAR);
        pathTransition.play();
        System.out.println();
        return pathTransition;
    }

    public void RenderRectangle(double x, double y) {
        Node rectangleTest = new Node(x, y);
        root.getChildren().add(rectangleTest);
    }
    public Image vehicleRaffle() {
        Image vehicleImage;
        int raffle = (int) Math.floor(Math.random() * 3);

        if (raffle == 1) {
            double vehicleSize = tileSize * 0.25; // scale factor
            int randomImageNumber = (int) Math.floor(Math.random() * 8); // 8 is total number of vehicle images
            String imageUrl = ("car_" + randomImageNumber + ".png");

            // set image based on roadType
            vehicleImage = new Image("file:src/main/resources/" + imageUrl, vehicleSize, vehicleSize, true, false);
            return vehicleImage;
        } else if (raffle == 2) {
            double vehicleSize = tileSize * 0.40; // scale factor
            int randomImageNumber = (int) Math.floor(Math.random() * 1); // 8 is total number of vehicle images
            String imageUrl = ("truck_1.png");

            // set image based on roadType
            vehicleImage = new Image("file:src/main/resources/" + imageUrl, vehicleSize, vehicleSize, true, false);
            return vehicleImage;
        } else {
            double vehicleSize = tileSize * 0.50; // scale factor
            int randomImageNumber = (int) Math.floor(Math.random() * 1); // 8 is total number of vehicle images
            String imageUrl = ("tank_1.png");

            // set image based on roadType
            vehicleImage = new Image("file:src/main/resources/" + imageUrl, vehicleSize, vehicleSize, true, false);
            return vehicleImage;
        }

      //  vehicleImage = new Image("file:src/main/resources/" + imageUrl, vehicleSize, vehicleSize, true, false);


        //return vehicleImage;
    }

    // add node to group


}