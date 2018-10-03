package sincity.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import sincity.model.City;

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

    public void render() {
        root.getChildren().clear();
        for (int y = padding; y < verticalPuzzles - padding; y++) {
            for (int x = padding; x < horizontalPuzzles - padding; x++) {

                // set image based on puzzle (random for null)
                String imageUrl = city.puzzleBoard[x][y] != null ? getProperImageUrl() : getRandomImageUrl();

                // choose image
                Image image = new Image(imageUrl, tileSize, tileSize, false, false);

                // create tile
                Tile tile = new Tile(x, y, tileSize, padding, image); // x and y are board indices

                // add tile to group
                root.getChildren().add(tile);
            }
        }
    }

    private String getRandomImageUrl() {
        int randomNumber = (int) Math.ceil(Math.random() * 10); // 10 is number of possible images
        return "file:src/main/resources/bcg" + randomNumber + ".png";
    }

    private String getProperImageUrl() {
        // TODO
        return "";
    }
}