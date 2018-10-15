package sincity.model;

import java.util.Observable;


public abstract class TrafficLights extends Observable {
    LightColor currentColor;
    Direction[] lightDirection;
    //all traffic lights  created green by default

    public TrafficLights(Direction direction) {
        this.currentColor = LightColor.GREEN;
        if (direction == Direction.W || direction == Direction.E) this.lightDirection = new Direction[] {Direction.E, Direction.W};
        if (direction == Direction.S || direction == Direction.N) this.lightDirection = new Direction[] {Direction.S, Direction.N};
    }

    public LightColor getCurrentColor() {
        return this.currentColor;
    }

    public void changeColor(LightColor lightColor) {
        synchronized (this) {
            currentColor = lightColor;
        }
        setChanged();
        notifyObservers(currentColor);
    }

}
