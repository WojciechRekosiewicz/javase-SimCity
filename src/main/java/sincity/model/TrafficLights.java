package sincity.model;

import java.util.Observable;


public abstract class TrafficLights extends Observable {
    LightColor currentColor;
    Orientation orientation;
    //all traffic lights  created green by default

    public TrafficLights() {
        this.currentColor = LightColor.GREEN;

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
    }

}
