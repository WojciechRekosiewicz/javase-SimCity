package sincity.model;

public enum VehicleType {
    CAR("car"),
    TRUCK("truck"),
    MOTOR("motor");

    private final String NAME;

    VehicleType(String name) {
        this.NAME = name;
    }

    String getName() {
        return this.NAME;
    }

}
