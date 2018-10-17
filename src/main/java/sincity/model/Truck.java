package sincity.model;

import sincity.view.Renderer;

public class Truck extends Vehicle {
    VehicleType vehicleType = VehicleType.TRUCK;

    Truck(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection) {
        super(city, renderer, roadPuzzle, arrivalDirection);
        speed = 0.2;
    }
}
