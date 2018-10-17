package sincity.model;

import sincity.view.Renderer;
import sincity.view.TrafficLightsDisplay;

import java.util.Observable;


public abstract class TrafficLights extends Observable {
    LightColor currentColor;
    Orientation orientation;

    public TrafficLights() {
    }

    public LightColor getCurrentColor() {
        return this.currentColor;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }


    public void changeColor(LightColor lightColor) {
        synchronized (this) {
            currentColor = lightColor;
        }
        setChanged();
        notifyObservers(currentColor);
        clearChanged();
    }

}
