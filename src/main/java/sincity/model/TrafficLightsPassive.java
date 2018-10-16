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
        this.orientation = Orientation.HORIZONTAL;
    }

    void changeToOpposite(LightColor color) {
        this.changeColor(color.getOpposite());
    }
}
