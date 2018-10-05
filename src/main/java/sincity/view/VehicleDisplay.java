package sincity.view;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

class VehicleDisplay extends StackPane {

    VehicleDisplay(Image image, Path pathToMove) {
        ImageView vehicleImage = new ImageView(image);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setNode(vehicleImage);
        pathTransition.setPath(pathToMove);
        pathTransition.setOrientation(
                PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.play();
        getChildren().addAll(vehicleImage);
    }
}