package sincity.model;

import javafx.scene.transform.Rotate;
import sincity.view.Renderer;
import sincity.view.TrafficLightsDisplay;

public class TrafficLightGenerator {

    final int DISPLAY_TIME = 1;
    RoadPuzzle puzzle;
    Direction[] directions = {Direction.E, Direction.N, Direction.S, Direction.W};
    TrafficLightsActive activeLights;
    TrafficLightsPassive passiveLights;


    public TrafficLightGenerator( RoadPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    public TrafficLights[] createLights(){
        TrafficLightsActive activeLights = new TrafficLightsActive(DISPLAY_TIME);
        activeLights.timeline();

        TrafficLightsPassive passiveLights = new TrafficLightsPassive(activeLights);
        return new TrafficLights[] {activeLights, passiveLights};
    }




//    public void createLightsView() {
//        int dir = 0;
//        for (boolean isThereRoad : puzzle.getRoadDirections()) {
//            if (isThereRoad) {
//                if (dir == 1 || dir == 2) {
//                    showTrafficLights(activeLights, puzzle);
//                } else {
//                    showTrafficLights(passiveLights, puzzle);
//                }
//                dir++;
//            }
//        }
//    }
//
//        private void showTrafficLights (TrafficLights lights, RoadPuzzle puzzle){
//            TrafficLightsDisplay trafficLightsDisplay = new TrafficLightsDisplay(lights, puzzle );
//        }


//        createLightsDisplay(puzzle);
//        setLightsPosition();
//        setLightsDirection(direction);

//        return TrafficLights;
//    }

//    private void createLightsDisplay(RoadPuzzle puzzle) {
//
//    }


//    private void setLightsPosition(){
//        final double TRANSLATION_DISTANCE = 0.25 * puzzle.getSize();
//
//        double lightCoX = puzzle.getCoX() + TRANSLATION_DISTANCE;
//        double lightCoY = puzzle.getCoY() + TRANSLATION_DISTANCE;
//    }
//
//    private void setLightsDirection(Direction direction) {
//        switch(direction){
//            case N:
//                this.getTransforms().add(new Rotate(180, puzzle.getCenterX(), puzzle.getCenterY()));     //rotate around own centre
//                this.getTransforms().add(new Rotate(90, puzzle.getCenterX(), puzzle.getCenterY()));     // rotate around puzzle centre
//                break;
//            case S:
//                this.getTransforms().add(new Rotate(0, puzzle.getCenterX(), puzzle.getCenterY()));
//                this.getTransforms().add(new Rotate(180, puzzle.getCenterX(), puzzle.getCenterY()));
//                break;
//            case W:
//                this.getTransforms().add(new Rotate(270, puzzle.getCenterX(), puzzle.getCenterY()));
//                this.getTransforms().add(new Rotate(90, puzzle.getCenterX(), puzzle.getCenterY()));
//                break;
//            case E:
//                this.getTransforms().add(new Rotate(90, puzzle.getCenterX(), puzzle.getCenterY()));
//                this.getTransforms().add(new Rotate(270, puzzle.getCenterX(), puzzle.getCenterY()));
//                break;
//        }
//    }


//    }

    public TrafficLightsActive getActiveLights() {
        return activeLights;
    }

    public TrafficLightsPassive getPassive() {
        return passiveLights;
    }
}
