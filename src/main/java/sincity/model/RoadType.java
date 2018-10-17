package sincity.model;

import java.util.HashMap;

public enum RoadType {

    // Directions order in boolean array : East, North, South, West
    ENSW(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, true);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, true);
    }},"ensw_1.png"),

    ENW(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, true);
        put(Direction.N, true);
        put(Direction.S, false);
        put(Direction.W, true);
    }}, "enw_1.png"),

    NSW(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, false);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, true);
    }}, "nsw_1.png"),

    ENS(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, true);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, false);
    }}, "nsw_1.png"),

    ESW(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, true);
        put(Direction.N, false);
        put(Direction.S, true);
        put(Direction.W, true);
    }}, "esw_1.png"),

    EW(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, true);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, true);
    }}, "ew_1.png"),

    NS(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, false);
        put(Direction.N, true);
        put(Direction.S, true);
        put(Direction.W, false);
    }}, "ns_1.png"),

    BCG(new HashMap<Direction, Boolean>()
    {{
        put(Direction.E, false);
        put(Direction.N, false);
        put(Direction.S, false);
        put(Direction.W, false);
    }}, "bcg_");

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
        return imageUrl.equals("bcg_") ? imageUrl + (int) Math.ceil(Math.random() * 10) + ".png" : imageUrl;
    }
}
