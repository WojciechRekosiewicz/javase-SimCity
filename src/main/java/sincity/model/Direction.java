package sincity.model;

import java.util.List;

import static java.util.Arrays.asList;
import static sincity.model.Orientation.*;

public enum Direction {
    E(HORIZONTAL),
    N(VERTICAL),
    S(VERTICAL),
    W(HORIZONTAL);

    private static List<Direction> directions = asList(N, S, W, E);
    private Orientation orientation;


    Direction(Orientation orientation) {
        this.orientation = orientation;

    }

    public Orientation getOrientation() {
        return orientation;
    }
}

