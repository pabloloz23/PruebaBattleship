package application.model;

public enum ShipType {
    BATTLESHIP(5, "Contenedores aislados"),
    FRIGATE(3, "Ninguna"),
    CANOE(1, "Ninguna");

    private final int size;
    private final String specialFeature;

    ShipType(int size, String specialFeature) {
        this.size = size;
        this.specialFeature = specialFeature;
    }

    public int getSize() {
        return size;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }
}
