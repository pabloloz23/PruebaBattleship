package application.builder;

import application.model.Ship;
import application.model.ShipType;

public class ShipDirector {
    private final ShipBuilder builder;

    public ShipDirector(ShipBuilder builder) {
        this.builder = builder;
    }

    public Ship buildBattleship() {
        return builder.setType(ShipType.BATTLESHIP).build();
    }

    public Ship buildFrigate() {
        return builder.setType(ShipType.FRIGATE).build();
    }

    public Ship buildCanoe() {
        return builder.setType(ShipType.CANOE).build();
    }
}
