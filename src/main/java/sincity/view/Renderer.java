package sincity.view;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import sincity.model.Vehicle;
import sincity.model.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Observable;
import java.util.Observer;

public class Renderer implements Observer {
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

                // set image basedc on roadType
                String imageUrl = "file:src/main/resources/" + roadType.getImageUrl();

                // choose image
                Image image = new Image(imageUrl, tileSize, tileSize, false, false);

                // create tile
                Tile tile = new Tile(x, y, tileSize, padding, image); // x and y are board indices

                // add tile to group
                root.getChildren().add(tile);

                if (city.getPuzzleBoard()[x][y].isTrafficLight()) {
                    addLightsToView(y, x);
                }
            }
        }
    }

    private void addLightsToView(int y, int x) {
        TrafficLights[] allTrafficLights = city.getPuzzleBoard()[x][y].getTrafficLights();
        TrafficLightsActive activeLights = (TrafficLightsActive) allTrafficLights[0];
        activeLights.addObserver(this);

        for (TrafficLights light : allTrafficLights) {
            TrafficLightsDisplay lightDisplay = new TrafficLightsDisplay(light, city.getPuzzleBoard()[x][y]);
            root.getChildren().add(lightDisplay);
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

    public TrafficLightsDisplay renderTrafficLights(TrafficLightsDisplay trafficLightsDisplay ){
        root.getChildren().add(trafficLightsDisplay);
        return trafficLightsDisplay;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof TrafficLightsActive) {
            TrafficLightsActive light = (TrafficLightsActive) o;
            RoadPuzzle puzzle = light.getPuzzle();
            addLightsToView(puzzle.getIndexY(), puzzle.getIndexX());
        }
    }

    // metody pomocnicze do testow:

    public void RenderTestLine(double startX1, double startY1, double endX1, double endY1, Color color) {
        List<javafx.scene.Node> linesToRemove = new ArrayList<>();
        for (javafx.scene.Node node : root.getChildren()) {
            if (node instanceof TestLine) {
                if (((TestLine) node).color.equals(color)) {
                    linesToRemove.add(node);
                }
            }
        }

        root.getChildren().removeAll(linesToRemove);

        DoubleProperty startX = new SimpleDoubleProperty(startX1);
        DoubleProperty startY = new SimpleDoubleProperty(startY1);
        DoubleProperty endX = new SimpleDoubleProperty(endX1);
        DoubleProperty endY = new SimpleDoubleProperty(endY1);

        TestLine testLine = new TestLine(startX, startY, endX, endY, color);

        root.getChildren().add(testLine);
    }

    public void RemoveTestLine(Color color) {
        List<javafx.scene.Node> linesToRemove = new ArrayList<>();
        for (javafx.scene.Node node : root.getChildren()) {
            if (node instanceof TestLine) {
                if (((TestLine) node).color.equals(color)) {
                    linesToRemove.add(node);
                }
            }
        }
        root.getChildren().removeAll(linesToRemove);
    }

}