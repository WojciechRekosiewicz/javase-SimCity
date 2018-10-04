package sincity.model;

public enum RoadType {

    // Directions order in boolean array : East, North, South, West
    ENSW(new boolean[]{true, true, true, true}, "ensw_1.png"),
    ENW(new boolean[]{true, true, false, true}, "enw_1.png"),
    NSW(new boolean[]{false, true, true, true}, "nsw_1.png"),
    ENS(new boolean[]{true, true, true, false}, "ens_1.png"),
    ESW(new boolean[]{true, false, true, true}, "esw_1.png"),
    EW(new boolean[]{true, false, false, true}, "ew_1.png"),
    NS(new boolean[]{false, true, true, false}, "ns_1.png"),
    BCG(new boolean[]{false, false, false, false}, "bcg_");

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
        return imageUrl.equals("bcg_") ? imageUrl + (int) Math.ceil(Math.random() * 10) + ".png" : imageUrl;
    }
}
