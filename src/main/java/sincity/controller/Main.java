package sincity.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sincity.view.Renderer;

public class Main extends Application {
    private int verticalPuzzles = 3;
    private int horizontalPuzzles = 3;

    private double sceneWidth = 800;
    private double sceneHeight = 800;

    private double tileWidth = sceneWidth / verticalPuzzles;
    private double tileHeight = sceneHeight / horizontalPuzzles;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setScene(scene);
        primaryStage.show();

        int padding = 1; // tiles off-screen

        // tutaj potrzebne stworzenie City city z parametrami (verticalTiles, horizontalTiles, padding)

        Renderer renderer = new Renderer(city, root, tileWidth, tileHeight, verticalPuzzles, horizontalPuzzles, padding);
        renderer.render();
    }
}