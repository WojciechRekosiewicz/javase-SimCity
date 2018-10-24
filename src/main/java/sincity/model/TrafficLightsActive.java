package sincity.model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class TrafficLightsActive extends TrafficLights implements Runnable {

    private int displayTime;
    private RoadPuzzle puzzle;

    public TrafficLightsActive(RoadPuzzle puzzle, int displayTime) {

        this.puzzle = puzzle;
        this.displayTime = displayTime;
        this.orientation = Orientation.VERTICAL;
        this.currentColor = LightColor.GREEN;

    }



    public void run()

    {
        boolean loop_status = true;
        while (loop_status) {
            synchronized (this) {
                try {
                    // Displaying the thread that is running
//                    System.out.println("Thread " +
//                            Thread.currentThread().getId() +
//                            " is running");
//
//                    this.changeColor(this.currentColor.getNext());
//                    setChanged();
//                    notifyObservers(currentColor);
//                    clearChanged();
                   // Thread.currentThread().sleep(2000);
                } catch (Exception e) {
                    // Throwing an exception
                    System.out.println("Exception is caught");
                }
            }
        }
    }




    public RoadPuzzle getPuzzle() {
        return puzzle;
    }

    public void timeline() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
            this.changeColor(this.currentColor.getNext());
            setChanged();
            notifyObservers(currentColor);
            clearChanged();

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
