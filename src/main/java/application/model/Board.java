package application.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int width;
    private final int height;
    private final List<Ship> ships;

    public Board(int width, int height, List<Ship> ships) {
        this.width = width;
        this.height = height;
        this.ships = ships;
    }

    public boolean attack() {
        if (ships.isEmpty()) return false;
        Ship target = ships.get(0);
        target.hit();
        if (target.isSunk()) {
            ships.remove(target);
        }
        return !ships.isEmpty();
    }

    public boolean hasShips() {
        return !ships.isEmpty();
    }

    @Override
    public String toString() {
        return "Board{width=" + width + ", height=" + height + ", ships=" + ships + "}";
    }
}
