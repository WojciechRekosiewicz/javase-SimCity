package sincity.model;

import java.util.List;

import static java.util.Arrays.asList;

public enum LightColor {

    RED("RED_light.png"),
    YELLOW_GREEN("YELLOW_G_light.png"),
    GREEN("GREEN_light.png"),
    YELLOW_RED("YELLOW_R_light.png");


    private String imageUrl;
    private static List<LightColor> lights = asList(RED, YELLOW_GREEN, GREEN, YELLOW_RED);

    LightColor(String imageUrl) {
        this.imageUrl = imageUrl;

    }


    public String getImageUrl() {
        return imageUrl;
    }

    public LightColor getNext() {
        return lights.get((lights.indexOf(this) + 1 ) % lights.size());
    }

    public LightColor getOpposite() {
        return lights.get((lights.indexOf(this) + lights.size() / 2 ) % lights.size());
    }


}
