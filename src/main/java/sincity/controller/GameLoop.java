package sincity.controller;

import javafx.animation.AnimationTimer;
import sincity.model.Vehicle;

import java.util.LinkedList;

public class GameLoop extends AnimationTimer {

    private LinkedList<Vehicle> vehicleList =  new LinkedList<>();

    // This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (Vehicle vehicle : vehicleList) {
            vehicle.updateVehicle();
        }
    }

    public void addToVehicleList(Vehicle vehicle){
        vehicleList.add(vehicle);
    }
}