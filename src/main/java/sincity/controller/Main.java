package sincity.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sincity.model.City;
import sincity.model.Spawner;
import sincity.view.Renderer;

public class Main extends Application {

    // board size, there should be always more horizontalPuzzles than verticalPuzzles
    private int horizontalPuzzles = 10; // could be final
    private int verticalPuzzles = 5;

    private double sceneWidth = 1400; // window size in pixels
    private double tileSize = sceneWidth / horizontalPuzzles; // tile size in pixels
    private double sceneHeight = tileSize * verticalPuzzles; // sceneHeight automatically adjusts based on tileSize and sceneWidth

    @Override
    public void start(Stage primaryStage) {
        if (horizontalPuzzles >= verticalPuzzles) {
            Group root = new Group();
            Scene scene = new Scene(root, sceneWidth, sceneHeight);
            primaryStage.setScene(scene);
            primaryStage.show();

            final int PADDING = 1; // tiles off-screen

            // create city with desired size
            City city = new City(verticalPuzzles, horizontalPuzzles, PADDING, tileSize);

            // create view based on city
            Renderer renderer = new Renderer(root, city, tileSize, verticalPuzzles, horizontalPuzzles, PADDING);
            renderer.renderCity();

            // create spawner
            new Spawner(city, renderer);
        } else {
            System.out.println("Wrong board size, please make sure to have equal or more horizontal puzzles than vertical ones.");
            Platform.exit();
        }
    }
}