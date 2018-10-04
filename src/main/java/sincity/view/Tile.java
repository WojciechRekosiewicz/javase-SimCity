package sincity.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


class Tile extends StackPane {


    Tile(int x, int y, double size, int padding, Image image) {
        // create tile image
        ImageView tileImage = new ImageView(image);

        // set position
        setTranslateX(x * size - (padding * size)); // window x coordinate
        setTranslateY(y * size - (padding * size)); // window y coordinate

        getChildren().addAll(tileImage);
    }
}