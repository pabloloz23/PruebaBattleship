package application.game;

import application.builder.BoardBuilder;
import application.builder.ShipBuilder;
import application.builder.ShipDirector;
import application.model.Player;
import application.model.Ship;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShipBuilder shipBuilder = new ShipBuilder();
        ShipDirector shipDirector = new ShipDirector(shipBuilder);
        BoardBuilder boardBuilder1 = new BoardBuilder();
        BoardBuilder boardBuilder2 = new BoardBuilder();

        System.out.println("Configurando barcos...");
        boardBuilder1.addShip(shipDirector.buildBattleship())
                .addShip(shipDirector.buildFrigate())
                .addShip(shipDirector.buildCanoe());

        boardBuilder2.addShip(shipDirector.buildBattleship())
                .addShip(shipDirector.buildFrigate())
                .addShip(shipDirector.buildCanoe());

        Player player1 = new Player("Jugador 1", boardBuilder1);
        Player player2 = new Player("Jugador 2", boardBuilder2);

        System.out.println("Comienza el juego...");
        while (player1.hasShips() && player2.hasShips()) {
            player1.attack(player2);
            player2.attack(player1);
        }

        if (player1.hasShips() && !player2.hasShips()) {
            System.out.println(player1.getName() + " gana!");
        } else if (player2.hasShips() && !player1.hasShips()) {
            System.out.println(player2.getName() + " gana!");
        } else {
            System.out.println("¡Empate!");
        }

        scanner.close();
    }
}
