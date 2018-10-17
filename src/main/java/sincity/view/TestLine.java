package sincity.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

class TestLine extends Line {
    Color color;

    TestLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, Color color) {
        this.color = color;
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setStrokeWidth(3);
        setStroke(color);
        setStrokeLineCap(StrokeLineCap.ROUND);
        getStrokeDashArray().setAll(15.0, 10.0);
        setMouseTransparent(true);
    }
}