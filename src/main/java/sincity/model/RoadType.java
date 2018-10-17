package sincity.model;

import java.util.HashMap;

public enum RoadType {

    // Directions order in boolean array : East, North, South, West
    ENSW(new boolean[]{true, true, true, true}, "ensw_1.png"),
    ENW(new boolean[]{true, true, false, true}, "enw_1.png"),
    ENS(new boolean[]{true, true, true, false}, "ens_1.png"),
    NSW(new boolean[]{false, true, true, true}, "nsw_1.png"),
    ESW(new boolean[]{true, false, true, true}, "esw_1.png"),
    EW(new boolean[]{true, false, false, true}, "ew_1.png"),
    NS(new boolean[]{false, true, true, false}, "ns_1.png"),
    BCG1(new boolean[]{false, false, false, false}, "bcg_1.png"),
    BCG2(new boolean[]{false, false, false, false}, "bcg_2.png"),
    BCG3(new boolean[]{false, false, false, false}, "bcg_3.png"),
    BCG4(new boolean[]{false, false, false, false}, "bcg_4.png"),
    BCG5(new boolean[]{false, false, false, false}, "bcg_5.png"),
    BCG6(new boolean[]{false, false, false, false}, "bcg_6.png"),
    BCG7(new boolean[]{false, false, false, false}, "bcg_7.png"),
    BCG8(new boolean[]{false, false, false, false}, "bcg_8.png"),
    BCG9(new boolean[]{false, false, false, false}, "bcg_9.png"),
    BCG10(new boolean[]{false, false, false, false}, "bcg_10.png");

    private boolean[] possibleDirection;
    private String imageUrl;

    RoadType(boolean[] possibleDirection, String imageUrl) {
        this.possibleDirection = possibleDirection;
        this.imageUrl = imageUrl;
    }

    public boolean[] getPossibleDirection() {
        return possibleDirection;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
