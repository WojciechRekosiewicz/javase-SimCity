package sincity.model;

import sincity.view.Renderer;

class Car extends Vehicle {

    Car(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection, VehicleType vehicleType) {
        super(city, renderer, roadPuzzle, arrivalDirection, vehicleType);
        topSpeed = 0.7;
        currentSpeed = topSpeed;
    }
}

