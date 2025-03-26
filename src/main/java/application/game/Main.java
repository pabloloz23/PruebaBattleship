package application.game;

import application.builder.ShipBuilder;
import application.builder.ShipDirector;
import application.model.Player;
import application.model.Ship;
import application.repository.PortGraph;
import application.repository.ShipRepository;
import application.repository.ShipHashTable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = new GameManager();
        PortGraph portGraph = new PortGraph();
        ShipHashTable shipHashTable = new ShipHashTable();
        ShipRepository shipRepo = new ShipRepository();

        // Iniciar nueva partida
        gameManager.startNewGame();

        // Crear jugadores
        Player player1 = gameManager.createPlayer("Jugador 1");
        Player player2 = gameManager.createPlayer("Jugador 2");

        // Asignar barcos a los jugadores
        gameManager.assignShipsToPlayer(player1);
        gameManager.assignShipsToPlayer(player2);

        // Cargar puertos y conexiones
        System.out.println("\n🔧 Configurando puertos...");
        portGraph.addPort("Puerto Madero");
        portGraph.addPort("Puerto de Rodas");
        portGraph.addPort("Barcelona");
        portGraph.addPort("Lisboa");

        portGraph.connectPorts("Puerto Madero", "Barcelona", 10);
        portGraph.connectPorts("Barcelona", "Puerto de Rodas", 5);
        portGraph.connectPorts("Lisboa", "Puerto Madero", 15);
        portGraph.connectPorts("Lisboa", "Puerto de Rodas", 7);

        System.out.println("\n🌍 Explorando puertos con DFS:");
        portGraph.depthFirstSearch("Puerto Madero");

        System.out.println("\n🛤 Camino más corto entre Puerto Madero y Puerto de Rodas:");
        System.out.println(portGraph.shortestPath("Puerto Madero", "Puerto de Rodas"));

        // Jugar Battleship hasta que haya un ganador
        System.out.println("\n🚀 ¡Comienza el juego Battleship!");
        while (!gameManager.checkGameOver()) {
            gameManager.attack(player1, player2);
            if (gameManager.checkGameOver()) break;
            gameManager.attack(player2, player1);
        }

        System.out.println("\n🏆 Fin del juego.");

        // Guardar un barco en tablas hash y buscarlo
        ShipBuilder builder = new ShipBuilder();
        ShipDirector director = new ShipDirector(builder);
        Ship newShip = director.buildBattleship();
        shipRepo.save(newShip); // Guardar en BD
        shipHashTable.addShip(newShip); // Guardar en memoria

        System.out.println("\n🔍 Buscando barco en tablas hash:");
        System.out.println("Por tipo: " + shipHashTable.getByType("Contenedores aislados").getName());
        System.out.println("Por número: " + shipHashTable.getByNumber(1001).getName());
        System.out.println("Por nombre: " + shipHashTable.getByName("Battleship").getName());

        // Eliminar el puerto con más conexiones
        System.out.println("\n🛑 Eliminando puerto con más conexiones...");
        portGraph.removeMostConnectedPort();

        scanner.close();
    }
}
