package sincity.view;

import javafx.scene.layout.Pane;
import sincity.model.Direction;
import sincity.model.Orientation;
import sincity.model.RoadPuzzle;
import sincity.model.TrafficLights;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrafficLightsDisplay extends Pane {

    private RoadPuzzle puzzle;
    private ImageView trafficLightView;
    private TrafficLights lights;
//    private double imageSizeH;
//    private double imageSizeV;


    public TrafficLightsDisplay(TrafficLights lights, RoadPuzzle puzzle) {
        this.lights = lights;
        this.puzzle = puzzle;
        createLightsViewsIfNeeded();
//        this.imageSizeH = 0.15 * puzzle.getSize();
//        this.imageSizeV = 0.3 * puzzle.getSize();
    }

    private void createLightsViewsIfNeeded() {
        double imageSizeH = 0.15 * puzzle.getSize();
        double imageSizeV = 0.3 * puzzle.getSize();

        for (Direction direction : Direction.values()) {
            Image trafficColorImg;
            boolean isDirectionHorizontal = direction.getOrientation() == Orientation.HORIZONTAL;
            boolean isDirectionVertical = direction.getOrientation() == Orientation.VERTICAL;
            boolean isThereRoad = puzzle.getRoadDirections().get(direction);
            boolean isLightHorizontal = lights.getOrientation() == Orientation.HORIZONTAL;
            boolean isLightVertical = lights.getOrientation() == Orientation.VERTICAL;

            if (isThereRoad && ((isDirectionHorizontal && isLightHorizontal) || (isDirectionVertical && isLightVertical))) {
                trafficColorImg = new Image(lights.getCurrentColor().getImageUrl(), imageSizeH, imageSizeV, false, true);
                trafficLightView = new ImageView(trafficColorImg);
                setImagePlacement(direction);
                getChildren().add(trafficLightView);
            }
        }
    }

    private void setImagePlacement(Direction direction) {
        final double DISTANCE_TRANSLATION = 0.15 * puzzle.getSize();
        switch (direction) {
            case E:
                trafficLightView.setRotate(trafficLightView.getRotate() + 270);                        //rotate around image's centre
                trafficLightView.setX(puzzle.getCoX() + puzzle.getSize() - 1.5 * DISTANCE_TRANSLATION);
                trafficLightView.setY(puzzle.getCoY() + 0.5 * DISTANCE_TRANSLATION);
                break;

            case N:
                trafficLightView.setRotate(trafficLightView.getRotate() + 180);                          //rotate around image's centre
                trafficLightView.setX(puzzle.getCoX() + DISTANCE_TRANSLATION);
                trafficLightView.setY(puzzle.getCoY());
                break;

            case S:
                //no rotation
                trafficLightView.setX(puzzle.getCoX() + puzzle.getSize() - 2 * DISTANCE_TRANSLATION);
                trafficLightView.setY(puzzle.getCoY() + puzzle.getSize() - 2 * DISTANCE_TRANSLATION);
                break;

            case W:
                trafficLightView.setRotate(trafficLightView.getRotate() + 90);                    //rotate around image's centre
                trafficLightView.setX(puzzle.getCoX() + 0.5 * DISTANCE_TRANSLATION);
                trafficLightView.setY(puzzle.getCoY() + puzzle.getSize() - 2.5 * DISTANCE_TRANSLATION);
                break;
        }

    }


}
