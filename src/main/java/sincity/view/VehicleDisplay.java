package sincity.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class VehicleDisplay extends StackPane {

    VehicleDisplay(Image image) {
        getChildren().add(new ImageView(image));
    }

    public double getCenterX() {
        return getTranslateX() + getWidth() / 2.0;
    }

    public double getCenterY() {
        return getTranslateY() + getHeight() / 2.0;
    }
}