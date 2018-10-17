package sincity.model;

import java.util.HashMap;

public enum RoadType {

    // Directions order in boolean array : East, North, South, West
    ENSW(new HashMap<Direction, Boolean>() {{
        put(Direction.E, true);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, true);
    }}, "ensw_1.png"),

    ENW(new HashMap<Direction, Boolean>() {{
        put(Direction.E, true);
        put(Direction.N, true);
        put(Direction.S, false);
        put(Direction.W, true);
    }}, "enw_1.png"),

    NSW(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, true);
    }}, "nsw_1.png"),

    ENS(new HashMap<Direction, Boolean>() {{
        put(Direction.E, true);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, false);
    }}, "ens_1.png"),

    ESW(new HashMap<Direction, Boolean>() {{
        put(Direction.E, true);
        put(Direction.N, false);
        put(Direction.S, true);
        put(Direction.W, true);
    }}, "esw_1.png"),

    EW(new HashMap<Direction, Boolean>() {{
        put(Direction.E, true);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, true);
    }}, "ew_1.png"),

    NS(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, false);
    }}, "ns_1.png"),

    BCG1(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_1"),

    BCG2(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_2"),

    BCG3(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_3"),

    BCG4(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_4"),

    BCG5(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_5"),

    BCG6(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_6"),

    BCG7(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_7"),

    BCG8(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_8"),

    BCG9(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_9"),

    BCG10(new HashMap<Direction, Boolean>() {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_10");



    private HashMap<Direction, Boolean> possibleDirection;
    private String imageUrl;

    RoadType(HashMap<Direction, Boolean> possibleDirection, String imageUrl) {
        this.possibleDirection = possibleDirection;
        this.imageUrl = imageUrl;
    }

    public HashMap<Direction, Boolean> getPossibleDirection() {
        return possibleDirection;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
