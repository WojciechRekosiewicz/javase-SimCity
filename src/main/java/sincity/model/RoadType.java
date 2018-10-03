package sincity.model;

public enum RoadType {

    // Directions order in boolean array : East, North, South, West
    ENSW(new boolean[]{true, true, true, true}),
    ENW(new boolean[]{true, true, false, true}),
    NSW(new boolean[]{false, true, true, true}),
    ENS(new boolean[]{true, true, true, false}),
    ESW(new boolean[]{true, false, true, true}),
    EW(new boolean[]{true, false, false, true}),
    NS(new boolean[]{false, true, true, false});


    private boolean[] possibleDirection;

    RoadType(boolean[] possibleDirection) {
        this.possibleDirection = possibleDirection;
    }

    public boolean[] getPossibleDirection() {
        return possibleDirection;
    }


}
