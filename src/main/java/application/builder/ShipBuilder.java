package application.builder;

import application.model.Ship;
import application.model.ShipType;

public class ShipBuilder {
    private String name;
    private int size;
    private String specialFeature;

    public ShipBuilder setType(ShipType type) {
        this.name = type.name();
        this.size = type.getSize();
        this.specialFeature = type.getSpecialFeature();
        return this;
    }

    public Ship build() {
        return new Ship(name, size, specialFeature);
    }
}
