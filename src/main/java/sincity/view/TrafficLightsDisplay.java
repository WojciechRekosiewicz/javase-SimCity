package sincity.view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sincity.model.Direction;
import sincity.model.Orientation;
import sincity.model.RoadPuzzle;
import sincity.model.TrafficLights;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrafficLightsDisplay extends Pane {

    private RoadPuzzle puzzle;
    Image trafficColorImg;
    ImageView trafficLigthView;
    TrafficLights lights;
    double imageSizeH;
    double imageSizeV;

    public TrafficLightsDisplay(TrafficLights lights, RoadPuzzle puzzle) {
        this.lights = lights;
        this.puzzle = puzzle;
        createLightsViesIfNeeded();
        imageSizeH = 0.15 * puzzle.getSize();
        imageSizeV = 0.3 * puzzle.getSize();
//        trafficColorImg = new Image (lights.getCurrentColor().getImageUrl());
//        trafficLigthView = new ImageView(trafficColorImg);
//        getChildren().add(trafficLigthView);
    }

    private void createLightsViesIfNeeded() {
        int dir = 0;
        double imageSizeH = 0.15 * puzzle.getSize();
        double imageSizeV = 0.3 * puzzle.getSize();
        for (boolean isThereRoad : puzzle.getRoadDirections()) {
            if (isThereRoad && (lights.getOrientation() == Orientation.HORIZONTAL && (dir == 0 || dir == 3)  )) {
                trafficColorImg = new Image(lights.getCurrentColor().getImageUrl(), imageSizeH, imageSizeV, false, true);
                trafficLigthView = new ImageView(trafficColorImg);
                setImagePlacement(dir);
                getChildren().add(trafficLigthView);
                }
            if (isThereRoad && (lights.getOrientation() == Orientation.VERTICAL && (dir == 1 || dir == 2)  )) {
                trafficColorImg = new Image(lights.getCurrentColor().getImageUrl(), imageSizeH, imageSizeV, false, true);
                trafficLigthView = new ImageView(trafficColorImg);
                setImagePlacement(dir);
                getChildren().add(trafficLigthView);
            }

            dir++;
        }
    }

    private void setImagePlacement(int dir){
        final double DISTANCE_TRANSLATION = 0.15 * puzzle.getSize();
        switch(dir){
            case 0:                     //EAST
                trafficLigthView.setRotate(trafficLigthView.getRotate() + 270);                        //rotate around puzzle self-centre
                trafficLigthView.setX(puzzle.getCoX() + puzzle.getSize() - 1.5 * DISTANCE_TRANSLATION);
                trafficLigthView.setY(puzzle.getCoY() + 0.5 * DISTANCE_TRANSLATION);
                break;

            case 1:                     //NORTH
                trafficLigthView.setRotate(trafficLigthView.getRotate() + 180);                          //rotate around puzzle self-centre
                trafficLigthView.setX(puzzle.getCoX() + DISTANCE_TRANSLATION);
                trafficLigthView.setY(puzzle.getCoY());
                break;

            case 2:                     //SOUTH
                trafficLigthView.setRotate(trafficLigthView.getRotate() + 0);                         //rotate around puzzle self-centre
                trafficLigthView.setX(puzzle.getCoX() + puzzle.getSize() - 2 * DISTANCE_TRANSLATION);
                trafficLigthView.setY(puzzle.getCoY() + puzzle.getSize() - 2 * DISTANCE_TRANSLATION);
                break;

            case 3:                     //WEST
                trafficLigthView.setRotate(trafficLigthView.getRotate() + 90);                    //rotate around puzzle self-centre
                trafficLigthView.setX(puzzle.getCoX() + 0.5 * DISTANCE_TRANSLATION);
                trafficLigthView.setY(puzzle.getCoY() + puzzle.getSize() - 2.5 * DISTANCE_TRANSLATION);
                break;
        }

    }




}
