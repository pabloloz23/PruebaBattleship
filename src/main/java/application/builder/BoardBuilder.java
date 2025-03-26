package application.builder;

import application.model.Board;
import application.model.Ship;
import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {
    private int width = 10;  // Valor por defecto
    private int height = 10; // Valor por defecto
    private List<Ship> ships = new ArrayList<>();

    public BoardBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public BoardBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public BoardBuilder addShip(Ship ship) {
        this.ships.add(ship);
        return this;
    }

    public Board build() {
        return new Board(width, height, ships);
    }
}
