package sincity.model;

import java.util.List;

import static java.util.Arrays.asList;

public enum LightColor {

    RED("RED_light.png"),
    RED2("RED_light.png"),
    YELLOW_RED("YELLOW_R_light.png"),
    GREEN("GREEN_light.png"),
    GREEN2("GREEN_light.png"),
    YELLOW_GREEN("YELLOW_G_light.png");


    private static List<LightColor> lights = asList(RED, RED2, YELLOW_RED, GREEN, GREEN2, YELLOW_GREEN); // TODO List is not needed
    private String imageUrl;

    LightColor(String imageUrl) {
        this.imageUrl = imageUrl;

    }


    public String getImageUrl() {
        return imageUrl;
    }

    public LightColor getNext() {
        return lights.get((lights.indexOf(this) + 1) % lights.size());
    }

    public LightColor getOpposite() {
        return lights.get((lights.indexOf(this) + lights.size() / 2) % lights.size());
    }


}
