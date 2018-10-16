package sincity.controller;

import javafx.animation.AnimationTimer;
import sincity.model.Tank;
import sincity.model.Vehicle;

import java.util.LinkedList;

public class GameLoop extends AnimationTimer {

    private LinkedList<Vehicle> vehicleList =  new LinkedList<>();
    private LinkedList<Tank> tankList = new LinkedList<>();

    //  This gets called every 1/60 seconds
    @Override
    public void handle(long now) {
        for (Vehicle vehicle : vehicleList) {

        }
        for (Tank tank : tankList) {
            tank.update();
        }
    }

    public void addToVehicleList(Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    public void addToTankList(Tank tank) {
        tankList.add(tank);
    }
}