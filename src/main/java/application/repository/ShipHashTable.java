package application.repository;

import application.model.Ship;
import java.util.HashMap;
import java.util.Map;

public class ShipHashTable {
    private Map<String, Ship> typeTable;   // Clave = Tipo de barco
    private Map<Integer, Ship[]> numberTable; // Clave = Número del barco, tamaño fijo de 15
    private Map<String, Ship> nameTable;   // Clave = Nombre del barco

    public ShipHashTable() {
        typeTable = new HashMap<>();
        numberTable = new HashMap<>();
        nameTable = new HashMap<>();
    }

    public void addShip(Ship ship) {
        // Agregar por tipo
        typeTable.put(ship.getSpecialFeature(), ship);

        // Agregar por número (mod 15)
        int hashKey = ship.getNumber() % 15;
        numberTable.putIfAbsent(hashKey, new Ship[15]);
        Ship[] ships = numberTable.get(hashKey);

        for (int i = 0; i < ships.length; i++) {
            if (ships[i] == null) {
                ships[i] = ship;
                break;
            }
        }

        // Agregar por nombre
        nameTable.put(ship.getName(), ship);
    }

    public Ship getByType(String type) {
        return typeTable.get(type);
    }

    public Ship getByNumber(int number) {
        int hashKey = number % 15;
        Ship[] ships = numberTable.get(hashKey);
        return (ships != null) ? ships[hashKey] : null;
    }

    public Ship getByName(String name) {
        return nameTable.get(name);
    }
}
