package sincity.model;

import sincity.view.Renderer;

import java.util.Observable;
import java.util.Observer;

public class TrafficLightsPassive extends TrafficLights implements Observer {

    public void update(Observable o, Object color) {
        this.changeToOpposite((LightColor) color);
    }

    public TrafficLightsPassive(TrafficLightsActive activeLights) {
        activeLights.addObserver(this);

        //  sets passive lightDirection as opposite to active LightDirection
        if (activeLights.getLightDirection()[0] == Direction.N || activeLights.getLightDirection()[0] == Direction.S) this.lightDirection = new Direction[] {Direction.W, Direction.E};
        if (activeLights.getLightDirection()[0] == Direction.W || activeLights.getLightDirection()[0] == Direction.E) this.lightDirection = new Direction[] {Direction.S, Direction.N};
    }

    void changeToOpposite(LightColor color) {
        this.changeColor(color.getOpposite());
    }
}
