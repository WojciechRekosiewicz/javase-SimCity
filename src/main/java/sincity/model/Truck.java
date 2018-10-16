package sincity.model;

import sincity.view.Renderer;

public class Truck extends Vehicle {
    VehicleType vehicleType = VehicleType.MOTOR;

    Truck(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection) {
        super(city, renderer, roadPuzzle, arrivalDirection);
        Vehicle vehicle = new Vehicle(city, renderer, roadPuzzle, arrivalDirection);

    }
}
