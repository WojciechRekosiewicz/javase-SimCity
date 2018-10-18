package sincity.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import sincity.model.RoadType;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

import static javafx.scene.paint.Color.*;

public class ConstructBoardView {

    private int horizontalPuzzles;
    private int verticalPuzzles;
    private int smallPuzzleSize = 80;
    private int puzzleSize;
    private int[][] imgPositionsInGrid = new int[][]{{9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 4}};
    private RoadType[][] puzzleBoard;
    private RoadType straightStreetPuzzle = RoadType.EW;
    private RoadType intersectionPuzzle = RoadType.ENSW;
    private LinkedList<RoadType> backgroundPuzzles = createBackgroundPuzzlesLinkedList();
    private RoadType activeType = RoadType.BCG8;
    private LinkedList<RoadType> tStreetPuzzles = createTPuzzlesLinkedList();
    private Rectangle selector;


    public ConstructBoardView(int horizontalPuzzles, int verticalPuzzles, int puzzleSize) {
        this.horizontalPuzzles = horizontalPuzzles;
        this.verticalPuzzles = verticalPuzzles;
        this.puzzleSize = puzzleSize;
        puzzleBoard = new RoadType[verticalPuzzles + 2][horizontalPuzzles + 2];
    }


    public GridPane constructFields() {
        GridPane gridPane = new GridPane();
        createBlackboard(gridPane);
        createSelectionFrame(gridPane);
        createPaletteImages(gridPane);

        return gridPane;
    }

    public RoadType[][] getPuzzleBoard(){
        return puzzleBoard;
    }

    private void createPaletteImages(GridPane gridPane) {
        ImageView imageBackground = createImagePuzzle(RoadType.BCG8.getImageUrl(), smallPuzzleSize);
        gridPane.add(imageBackground, imgPositionsInGrid[1][0], imgPositionsInGrid[1][1]);

        ImageView imageStraightRoad = createImagePuzzle(straightStreetPuzzle.getImageUrl(), smallPuzzleSize);
        gridPane.add(imageStraightRoad, imgPositionsInGrid[2][0], imgPositionsInGrid[2][1]);

        ImageView imageT = createImagePuzzle(tStreetPuzzles.getFirst().getImageUrl(), smallPuzzleSize);
        gridPane.add(imageT, imgPositionsInGrid[3][0], imgPositionsInGrid[3][1]);

        ImageView imageIntersection = createImagePuzzle(intersectionPuzzle.getImageUrl(), smallPuzzleSize);
        gridPane.add(imageIntersection, imgPositionsInGrid[4][0], imgPositionsInGrid[4][1]);

        imageBackground.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                backgroundPuzzles.addLast(backgroundPuzzles.pollFirst());
                imageBackground.setImage(new Image("file:src/main/resources/" + backgroundPuzzles.getFirst().getImageUrl()));
                if (isBcgPuzzleActive()) {
                    activeType = backgroundPuzzles.getFirst();
                }
            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[1][0], imgPositionsInGrid[1][1]);
                activeType = backgroundPuzzles.getFirst();
            }
        });

        imageStraightRoad.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                straightStreetPuzzle = straightStreetPuzzle.equals(RoadType.EW) ? RoadType.NS : RoadType.EW;
                imageStraightRoad.setImage(new Image("file:src/main/resources/" + straightStreetPuzzle.getImageUrl()));
                if (isStraightRoad()) {
                    activeType = straightStreetPuzzle;
                }
            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[2][0], imgPositionsInGrid[2][1]);
                activeType = straightStreetPuzzle;
            }
        });

        imageT.setOnMouseClicked(e -> {

            if (e.getButton().equals(MouseButton.PRIMARY)) {
                tStreetPuzzles.addLast(tStreetPuzzles.pollFirst());
                imageT.setImage(new Image("file:src/main/resources/" + tStreetPuzzles.getFirst().getImageUrl()));
                if (isTIntersection()) {
                    activeType = tStreetPuzzles.getFirst();
                }
            }
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[3][0], imgPositionsInGrid[3][1]);
                activeType = tStreetPuzzles.getFirst();
            }
        });

        imageIntersection.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.SECONDARY)) {
                selectPuzzle(imgPositionsInGrid[4][0], imgPositionsInGrid[4][1]);
                activeType = intersectionPuzzle;

            }
        });
    }

    private boolean isStraightRoad() {
        return activeType.equals(RoadType.EW) || activeType.equals(RoadType.NS);
    }

    private boolean isTIntersection() {
        return tStreetPuzzles.contains(activeType);
    }

    private boolean isBcgPuzzleActive() {
        return backgroundPuzzles.contains(activeType);
    }

    private void createSelectionFrame(GridPane gridPane) {
        selector = new Rectangle(82, 82);
        selector.setFill(TRANSPARENT);
        selector.setStroke(RED);
        selector.setStrokeWidth(5);
        gridPane.add(selector, imgPositionsInGrid[1][0], imgPositionsInGrid[1][1]);
    }

    public Button createAcceptButton(GridPane gridPane) {
        Button button = new Button("OK");
        gridPane.add(button, 9, 0);
        button.setPadding(new Insets(20, 20, 20, 20));
        GridPane.setHalignment(button, HPos.CENTER);
        return button;
    }

    private void createBlackboard(GridPane gridPane) {
        for (int x = 0; x < horizontalPuzzles; x++) {
            for (int y = 0; y < verticalPuzzles; y++) {
                Rectangle rectangle = new Rectangle(puzzleSize, puzzleSize);
                gridPane.add(rectangle, x, y);
                rectangle.setFill(BLACK);
                rectangle.setStroke(TRANSPARENT);

                rectangle.setOnMouseClicked(e -> {
                    int colPos = GridPane.getColumnIndex(rectangle);
                    int rowPos = GridPane.getRowIndex(rectangle);

                    if (canAddPuzzleToBoard()) {
                        Image image = new Image(activeType.getImageUrl());
                        rectangle.setFill(new ImagePattern(image));
                        puzzleBoard[rowPos + 1] [colPos + 1]= activeType;
                    }
                });
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

    private LinkedList<RoadType> createBackgroundPuzzlesLinkedList() {

        LinkedList<RoadType> bcgList = new LinkedList<>();
        bcgList.add(RoadType.BCG1);
//        bcgList.add(RoadType.BCG2);
        bcgList.add(RoadType.BCG3);
        bcgList.add(RoadType.BCG4);
        bcgList.add(RoadType.BCG5);
        bcgList.add(RoadType.BCG6);
        bcgList.add(RoadType.BCG7);
        bcgList.add(RoadType.BCG8);
        bcgList.add(RoadType.BCG9);
        bcgList.add(RoadType.BCG10);

        return bcgList;
    }

    private void selectPuzzle(int x, int y) {
        GridPane.setColumnIndex(selector, x);
        GridPane.setRowIndex(selector, y);
    }

    private ImageView createImagePuzzle(String url, int size) {
        ImageView image = new ImageView();
        image.setImage(new Image("file:src/main/resources/" + url));
        image.setFitWidth(size);
        image.setFitHeight(size);
        GridPane.setHalignment(image, HPos.CENTER);
        return image;
    }

    private boolean canAddPuzzleToBoard() {
        //todo
        return true;
    }

}






