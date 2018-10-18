package sincity.model;

class TrafficLightGenerator {

    private sincity.model.RoadPuzzle puzzle;

    TrafficLightGenerator(RoadPuzzle puzzle) {
        this.puzzle = puzzle;
    }

    TrafficLights[] createLights() {
        int DISPLAY_TIME = getRandomWithRange(2, 5);
        TrafficLightsActive activeLights = new TrafficLightsActive(puzzle, DISPLAY_TIME);
        activeLights.timeline();

        TrafficLightsPassive passiveLights = new TrafficLightsPassive(activeLights);
        return new TrafficLights[]{activeLights, passiveLights};
    }

    private int getRandomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}