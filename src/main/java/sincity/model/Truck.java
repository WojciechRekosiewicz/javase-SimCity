package sincity.model;

import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import sincity.view.Renderer;
import sincity.view.VehicleDisplay;


public class Truck extends Vehicle {
    //  VehicleType vehicleType = VehicleType.TRUCK;

//    private Renderer renderer;
//    private VehicleDisplay vehicleDisplay;

    Truck(City city, Renderer renderer, RoadPuzzle roadPuzzle, Direction arrivalDirection, VehicleType vehicleType) {
        super(city, renderer, roadPuzzle, arrivalDirection, vehicleType);
        speed = 0.2;
        //  Vehicle vehicle = new Vehicle(city,renderer, roadPuzzle, arrivalDirection, vehicleType);
        // vehicle.move();
    }
}
