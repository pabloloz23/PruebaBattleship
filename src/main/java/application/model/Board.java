package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    private final List<Ship> ships;
    private final Random random;

    public Board(int width, int height, List<Ship> ships) {
        this.width = width;
        this.height = height;
        this.ships = ships;
        this.random = new Random();
    }

    public boolean attack() {
        if (ships.isEmpty()) return false;

        // Selecciona un barco aleatorio para atacar
        int index = random.nextInt(ships.size());
        Ship target = ships.get(index);
        target.hit();

        System.out.println("¡Barco atacado! " + target);

        // Elimina el barco si está hundido
        if (target.isSunk()) {
            ships.remove(index);
            System.out.println("¡Barco hundido!");
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
