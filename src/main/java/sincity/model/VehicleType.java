package sincity.model;

public enum VehicleType {
    CAR("car", 8, 0.37),
    TRUCK("truck", 3, 0.40),
    TANK("tank", 1, 0.44);

    private final String NAME;
    private final int AMOUNT;
    private final double SCALE;

    VehicleType(String name, int amount, double scale) {
        this.NAME = name;
        this.AMOUNT = amount;
        this.SCALE = scale;
    }

    public int getAmount() {
        return AMOUNT;
    }

    public double getScale() {
        return SCALE;
    }

    public String getName() {
        return NAME;
    }

}
