package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import sincity.view.Renderer;

public class TrafficLightsActive extends TrafficLights {
    int displayTime;
    Direction direction;



    public TrafficLightsActive( int displayTime) {
        this.displayTime =  displayTime;
        this.direction = Direction.S;
        if (direction == Direction.W || direction == Direction.E) this.lightDirection = new Direction[] {Direction.E, Direction.W};
        if (direction == Direction.S || direction == Direction.N) this.lightDirection = new Direction[] {Direction.N, Direction.S};
        this.orientation = Orientation.VERTICAL;
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
