package sincity.model;

import java.util.List;

import static java.util.Arrays.asList;
import static sincity.model.Orientation.*;

public enum Direction {
    N(VERTICAL),
    S(VERTICAL),
    W(HORIZONTAL),
    E(HORIZONTAL);

    private Orientation orientation;
    private static List<Direction> directions = asList(N, S, W, E);



    Direction(Orientation orientation) {
        this.orientation = orientation;

    }

    public Orientation getOrientation() {
        return orientation;
    }
}

