package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sincity.view.Renderer;

public class TrafficLightsActive extends TrafficLights {
    int displayTime;



    public TrafficLightsActive( int displayTime) {
        this.displayTime =  displayTime;
        this.orientation = Orientation.VERTICAL;
        this.currentColor = LightColor.GREEN;
    }


    public void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(displayTime), ev -> {
            System.out.println("kolor zmieniony!");
            this.changeColor(this.currentColor.getNext());

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
