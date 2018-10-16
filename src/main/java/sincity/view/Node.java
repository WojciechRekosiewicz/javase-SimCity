package sincity.view;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Node extends StackPane {

    Node(double x, double y) {
        // create rectangle
        Rectangle rectangle = new Rectangle(3, 3);
        rectangle.setFill(Color.RED);

        // set position
        setTranslateX(x);
        setTranslateY(y);

        getChildren().addAll(rectangle);
    }
}