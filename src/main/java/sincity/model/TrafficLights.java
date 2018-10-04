package main.java.sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class TrafficLights {
    LightColor color;
    int displayTime;  //display time (in seconds) of green and red light
    int yellowTime;   //display time (in sec) of orange light
    private Image lightsImage;

    //all trafic lights are created green by default
    public TrafficLights(int displayTime, int orangeTime) {
        this.color = LightColor.GREEN;
        this.lightsImage =  new Image(color.getImageUrl());
        this.displayTime = displayTime;
        this.yellowTime = orangeTime;
    }


    public LightColor getColor() {
            return this.color;
    }

    public void changeColor(LightColor lightColor) {
        this.color = lightColor;
        this.lightsImage =  new Image(color.getImageUrl());
    }


    private void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(yellowTime), ev -> {
            this.changeColor(LightColor.YELLOW);
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(displayTime), ev -> {
            this.changeColor(LightColor.GREEN);
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(yellowTime), ev -> {
            this.changeColor(LightColor.YELLOW);
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(displayTime), ev -> {
            this.changeColor(LightColor.RED);
        }));
        timeline.setAutoReverse(true);
        timeline.play();
    }







}
