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


    public TrafficLightGenerator(RoadPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    public TrafficLights[] createLights(){
        TrafficLightsActive activeLights = new TrafficLightsActive(puzzle, DISPLAY_TIME);
        activeLights.timeline();

        TrafficLightsPassive passiveLights = new TrafficLightsPassive(activeLights);
        return new TrafficLights[] {activeLights, passiveLights};
    }

    public TrafficLightsActive getActiveLights() {
        return activeLights;
    }

    public TrafficLightsPassive getPassive() {
        return passiveLights;
    }
}
