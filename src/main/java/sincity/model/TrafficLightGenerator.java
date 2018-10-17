package sincity.model;

public class TrafficLightGenerator {

    private RoadPuzzle puzzle;

    public TrafficLightGenerator(RoadPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    public TrafficLights[] createLights(){
        int DISPLAY_TIME = 2;
        TrafficLightsActive activeLights = new TrafficLightsActive(puzzle, DISPLAY_TIME);
        activeLights.timeline();

        TrafficLightsPassive passiveLights = new TrafficLightsPassive(activeLights);
        return new TrafficLights[] {activeLights, passiveLights};
    }
}
