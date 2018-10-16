package sincity.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import sincity.model.RoadType;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

import static javafx.scene.paint.Color.*;

public class ConstructBoardView {

    private int horizontalPuzzles;
    private int verticalPuzzles;
    private int[][] imgPositionsInGrid = new int[][]{{9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}};
    private RoadType straightStreetPuzzle = RoadType.EW;
    private RoadType intersectionPuzzle = RoadType.ENSW;
    private RoadType emptyPuzzle = RoadType.BCG;
    private RoadType activeType = RoadType.BCG;
    private LinkedList<RoadType> tStreetPuzzles = createTPuzzlesLinkedList();
    private Rectangle selector;


    public ConstructBoardView(int horizontalPuzzles, int verticalPuzzles) {
        this.horizontalPuzzles = horizontalPuzzles;
        this.verticalPuzzles = verticalPuzzles;
    }


    public GridPane constructFields() {
        GridPane gridPane = new GridPane();
        createBlackboard(gridPane);
        createAcceptButton(gridPane);
        createSelectionFrame(gridPane);

        ImageView imageBackground = createImagePuzzle(emptyPuzzle.getImageUrl());
        gridPane.add(imageBackground, imgPositionsInGrid[1][0], imgPositionsInGrid[1][1]);

        ImageView imageStraightRoad = createImagePuzzle(straightStreetPuzzle.getImageUrl());
        gridPane.add(imageStraightRoad, imgPositionsInGrid[2][0], imgPositionsInGrid[2][1]);

        ImageView imageT = createImagePuzzle(tStreetPuzzles.get(0).getImageUrl());
        gridPane.add(imageT,  imgPositionsInGrid[3][0],imgPositionsInGrid[3][1]);

        ImageView imageIntersection = createImagePuzzle(intersectionPuzzle.getImageUrl());
        gridPane.add(imageIntersection, imgPositionsInGrid[4][0], imgPositionsInGrid[4][1]);



        imageBackground.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                imageBackground.setImage(new Image("file:src/main/resources/" + emptyPuzzle.getImageUrl()));

            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[1][0], imgPositionsInGrid[1][1], emptyPuzzle);
                activeType = emptyPuzzle;
            }
            System.out.println(activeType);
        });


        imageStraightRoad.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                straightStreetPuzzle = straightStreetPuzzle.equals(RoadType.EW) ? RoadType.NS : RoadType.EW;
                imageStraightRoad.setImage(new Image("file:src/main/resources/" + straightStreetPuzzle.getImageUrl()));
                if (activeType.equals(RoadType.EW) || activeType.equals(RoadType.NS)) {
                    activeType = straightStreetPuzzle;
                }

            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[2][0], imgPositionsInGrid[2][1], straightStreetPuzzle);
                activeType = straightStreetPuzzle;

            }
            System.out.println(activeType);

        });


        imageT.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                RoadType temp = tStreetPuzzles.pollFirst();
                tStreetPuzzles.addLast(temp);
                imageT.setImage(new Image("file:src/main/resources/" + tStreetPuzzles.get(0).getImageUrl()));
                if (activeType.equals(RoadType.NSW) || activeType.equals(RoadType.ENW) || activeType.equals(RoadType.ENS)) {
                    activeType = tStreetPuzzles.getFirst();
                }

            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[3][0],imgPositionsInGrid[3][1], tStreetPuzzles.getFirst());
                activeType = tStreetPuzzles.getFirst();
            }
            System.out.println(activeType);
        });




        imageIntersection.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[4][0], imgPositionsInGrid[4][1], intersectionPuzzle);
                activeType = intersectionPuzzle;

            }
            System.out.println(activeType);

        });

        return gridPane;
    }

    private void createSelectionFrame(GridPane gridPane) {
        selector = new Rectangle(82, 82);
        selector.setFill(TRANSPARENT);
        selector.setStroke(RED);
        selector.setStrokeWidth(5);
        gridPane.add(selector, imgPositionsInGrid[1][0], imgPositionsInGrid[1][1]);
    }

    private void createAcceptButton(GridPane gridPane) {
        Button button1 = new Button("OK");
        gridPane.add(button1, 9, 0);
        button1.setPadding(new Insets(20, 20, 20, 20));
        GridPane.setHalignment(button1, HPos.CENTER);
    }

    private void createBlackboard(GridPane gridPane) {
        for (int x = 0; x < horizontalPuzzles; x++) {
            for (int y = 0; y < verticalPuzzles; y++) {
                Rectangle rectangle = new Rectangle(140, 140);
                gridPane.add(rectangle, x, y);
                rectangle.setFill(BLACK);
                rectangle.setStroke(TRANSPARENT);
            }
        }
    }


    private LinkedList<RoadType> createTPuzzlesLinkedList() {

        LinkedList<RoadType> tStreetsList = new LinkedList<>();
        tStreetsList.add(RoadType.ENW);
        tStreetsList.add(RoadType.ENS);
        tStreetsList.add(RoadType.NSW);
        tStreetsList.add(RoadType.ESW);

        return tStreetsList;
    }

    private void selectPuzzle(int x, int y, RoadType type) {
        GridPane.setColumnIndex(selector, x);
        GridPane.setRowIndex(selector, y);
        System.out.println(activeType);
    }

    private ImageView createImagePuzzle(String url){
        ImageView image =  new ImageView();
        image.setImage(new Image("file:src/main/resources/" + url));
        image.setFitWidth(80);
        image.setFitHeight(80);
        GridPane.setHalignment(image, HPos.CENTER);
        return image;
    }

}






