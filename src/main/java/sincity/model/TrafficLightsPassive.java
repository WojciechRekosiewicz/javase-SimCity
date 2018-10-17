package sincity.model;

import sincity.view.Renderer;

import java.util.Observable;
import java.util.Observer;

public class TrafficLightsPassive extends TrafficLights implements Observer {

    // passive Traffic Lights are RED and HORIZONTAL by default
    public TrafficLightsPassive(TrafficLightsActive activeLights) {
        activeLights.addObserver(this);
        this.orientation = Orientation.HORIZONTAL;
        this.currentColor = LightColor.RED;

    }

    public void update(Observable o, Object color) {
        this.changeToOpposite((LightColor) color);
    }

    void changeToOpposite(LightColor color) {
        this.changeColor(color.getOpposite());
    }
}
