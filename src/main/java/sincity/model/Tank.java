package sincity.model;

import javafx.animation.PathTransition;
import sincity.view.*;
import sincity.view.Renderer;


public class Tank extends Vehicle {

    Tank(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection, VehicleType vehicleType) {
        super(city, renderer, roadPuzzle, arrivalDirection, vehicleType);
        speed = 0.7;
    }
}
