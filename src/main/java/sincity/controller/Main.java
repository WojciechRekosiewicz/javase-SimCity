package sincity.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sincity.model.City;
import sincity.model.RoadType;
import sincity.model.Spawner;
import sincity.view.ConstructBoardView;
import sincity.view.Renderer;

public class Main extends Application {

    // board size, there should be always more horizontalPuzzles than verticalPuzzles
    private int horizontalPuzzles = 9; // could be final
    private int verticalPuzzles = 5;
    //    private double tileSize = sceneWidth / horizontalPuzzles; // tile size in pixels
    //    private double sceneWidth = 1400; // window size in pixels
    //    private double sceneHeight = tileSize * verticalPuzzles; // sceneHeight automatically adjusts based on tileSize and sceneWidth

    private int tileSize = 140; // tile size in pixels
    private double sceneWidth = horizontalPuzzles * tileSize; // window size in pixels
    private double sceneHeight = verticalPuzzles * tileSize; // sceneHeight automatically adjusts based on tileSize and sceneWidth

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, sceneWidth + tileSize, sceneHeight);
        primaryStage.setScene(scene);

        ConstructBoardView initialView = new ConstructBoardView(horizontalPuzzles, verticalPuzzles, tileSize);
        GridPane gridPane = initialView.constructFields();
        Button startButton = initialView.createAcceptButton(gridPane);

        root.getChildren().add(gridPane);
        primaryStage.show();
        primaryStage.setScene(scene);

        startButton.setOnMouseClicked( e -> {

            root.getChildren().clear();
            primaryStage.setMaxWidth(sceneWidth );
            final int PADDING = 1; // tiles off-screen

            RoadType[][] userMap = initialView.getPuzzleBoard();

            // create city with desired size
            City city = new City(userMap, verticalPuzzles, horizontalPuzzles, PADDING, tileSize);

            // create view based on city
            Renderer renderer = new Renderer(root, city, tileSize, verticalPuzzles, horizontalPuzzles, PADDING);
            renderer.renderCity();

            GameLoop gameLoop = new GameLoop();
            gameLoop.start();

            // create spawner
            new Spawner(city, renderer, gameLoop);
        });
    }
}