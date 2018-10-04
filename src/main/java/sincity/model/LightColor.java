package main.java.sincity.model;


public enum LightColor {

        RED("RED_light.png"),
        YELLOW("YELLOW_light.png"),
        GREEN("GREEN_light.png");

        private String imageUrl;

        LightColor(String imageUrl) {
            this.imageUrl = imageUrl;
        }


        public String getImageUrl() {
            return imageUrl;
        }
    }

