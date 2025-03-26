package application.game;

import application.builder.ShipBuilder;
import application.builder.ShipDirector;
import application.model.Game;
import application.model.Player;
import application.model.Board;
import application.model.Ship;
import application.repository.GameRepository;
import application.repository.PlayerRepository;
import application.repository.BoardRepository;
import application.repository.ShipRepository;
import application.repository.ShipHashTable;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;
    private final ShipRepository shipRepository;
    private final ShipHashTable shipHashTable;
    private Game currentGame;

    public GameManager() {
        this.gameRepository = new GameRepository();
        this.playerRepository = new PlayerRepository();
        this.boardRepository = new BoardRepository();
        this.shipRepository = new ShipRepository();
        this.shipHashTable = new ShipHashTable();
    }

    // Iniciar una nueva partida
    public void startNewGame() {
        currentGame = new Game();
        gameRepository.save(currentGame);
        System.out.println("Nueva partida creada con ID: " + currentGame.getId());
    }

    // Crear un jugador con un tablero y agregarlo a la partida
    public Player createPlayer(String name) {
        Board board = new Board(10, 10, new ArrayList<>());
        boardRepository.save(board);

        Player player = new Player(name, board);
        playerRepository.save(player);

        // Asociar el jugador a la partida actual
        List<Player> players = currentGame.getPlayers();
        if (players == null) players = new ArrayList<>();
        players.add(player);
        currentGame.setPlayers(players);
        gameRepository.save(currentGame);

        System.out.println("Jugador creado: " + name + " con tablero ID: " + board.getId());
        return player;
    }

    // Crear y asignar barcos a un jugador
    public void assignShipsToPlayer(Player player) {
        ShipBuilder builder = new ShipBuilder();
        ShipDirector director = new ShipDirector(builder);

        List<Ship> ships = new ArrayList<>();
        ships.add(director.buildBattleship());
        ships.add(director.buildFrigate());
        ships.add(director.buildCanoe());

        for (Ship ship : ships) {
            shipRepository.save(ship);
            shipHashTable.addShip(ship);
            System.out.println("Barco asignado: " + ship.getName() + " al jugador: " + player.getName());
        }

        // Guardar barcos en el tablero del jugador
        Board board = player.getBoard();
        board.setShips(ships);
        boardRepository.save(board);
    }

    // Recuperar una partida existente
    public Game loadGame(Long gameId) {
        Game game = gameRepository.getById(gameId);
        if (game != null) {
            System.out.println("Partida cargada con ID: " + game.getId());
            currentGame = game;
        } else {
            System.out.println("No se encontró una partida con ID: " + gameId);
        }
        return game;
    }

    // Atacar un barco de un jugador oponente
    public void attack(Player attacker, Player defender) {
        Board defenderBoard = defender.getBoard();
        List<Ship> ships = defenderBoard.getShips();

        if (ships.isEmpty()) {
            System.out.println("¡" + defender.getName() + " ya no tiene barcos!");
            return;
        }

        Ship target = ships.get(0); // Atacar el primer barco disponible
        target.hit();
        shipRepository.save(target);

        if (target.isSunk()) {
            ships.remove(target);
            System.out.println("¡El barco " + target.getName() + " ha sido hundido!");
        } else {
            System.out.println(attacker.getName() + " ha atacado el barco " + target.getName());
        }

        boardRepository.save(defenderBoard);
    }

    // Verificar si la partida ha terminado
    public boolean checkGameOver() {
        List<Player> players = currentGame.getPlayers();
        int playersWithShips = 0;

        for (Player player : players) {
            if (player.getBoard().hasShips()) {
                playersWithShips++;
            }
        }

        if (playersWithShips <= 1) {
            System.out.println("¡Juego terminado!");
            return true;
        }

        return false;
    }
}
