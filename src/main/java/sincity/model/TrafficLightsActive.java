package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class TrafficLightsActive extends TrafficLights {

    private int displayTime;
    private RoadPuzzle puzzle;

    public TrafficLightsActive(RoadPuzzle puzzle, int displayTime) {

        this.puzzle = puzzle;
        this.displayTime = displayTime;
        this.orientation = Orientation.VERTICAL;
        this.currentColor = LightColor.GREEN;
    }

    public RoadPuzzle getPuzzle() {
        return puzzle;
    }

    public void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(displayTime), ev -> {
            System.out.println("kolor zmieniony!");
            this.changeColor(this.currentColor.getNext());
            setChanged();
            notifyObservers(currentColor);
            clearChanged();

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
