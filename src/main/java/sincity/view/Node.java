package sincity.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

class Node extends StackPane {

    Node(double x, double y, Color color) {
        // create rectangle
        Rectangle rectangle = null;
        rectangle = new Rectangle(10, 10);
        rectangle.setFill(color);

        // set position
        setTranslateX(x - 5);
        setTranslateY(y - 5);

        getChildren().addAll(rectangle);
    }
}