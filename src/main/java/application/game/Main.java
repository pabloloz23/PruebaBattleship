package application.game;

import application.builder.ShipBuilder;
import application.builder.ShipDirector;
import application.model.Ship;
import application.repository.ShipRepository;
import application.repository.ShipHashTable;

public class Main {
    public static void main(String[] args) {
        // Crear un barco usando el Builder y Director
        ShipBuilder builder = new ShipBuilder();
        ShipDirector director = new ShipDirector(builder);
        Ship ship = director.buildBattleship();

        // Guardarlo en MySQL
        ShipRepository repo = new ShipRepository();
        repo.save(ship);

        // Cargarlo en la tabla hash en memoria
        ShipHashTable hashTable = new ShipHashTable();
        hashTable.addShip(ship);

        // Mostrar búsquedas en la tabla hash
        System.out.println("Búsqueda por tipo: " + hashTable.getByType("Contenedores aislados").getName());
        System.out.println("Búsqueda por número: " + hashTable.getByNumber(1001).getName());
        System.out.println("Búsqueda por nombre: " + hashTable.getByName("Battleship").getName());
    }
}
