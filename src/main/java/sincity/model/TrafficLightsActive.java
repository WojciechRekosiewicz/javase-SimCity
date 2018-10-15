package main.java.sincity.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TrafficLightsActive extends TrafficLights {
    int displayTime;


    public TrafficLightsActive(int displayTime) {
        this.displayTime =  displayTime;
    }


    public void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            System.out.println("kolor zmieniony!");
            this.changeColor(this.currentColor.getNext());

        }));
        timeline.setAutoReverse(true);
        timeline.play();
    }

}
