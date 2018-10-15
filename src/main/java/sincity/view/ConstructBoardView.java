package sincity.view;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class ConstructBoardView {

    private int horizontalPuzzles;
    private int verticalPuzzles;

    public ConstructBoardView(int horizontalPuzzles, int verticalPuzzles) {
        this.horizontalPuzzles = horizontalPuzzles;
        this.verticalPuzzles = verticalPuzzles;
    }

    public GridPane constructFields() {
        GridPane gridPane = new GridPane();
        for (int x = 0; x < horizontalPuzzles; x++) {
            for (int y = 0; y < verticalPuzzles; y++) {
                Rectangle rectangle = new Rectangle(140, 140);
                gridPane.add(rectangle, x, y);
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }


}
