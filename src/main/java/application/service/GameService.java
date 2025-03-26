package application.service;

import application.model.Game;
import application.repository.GameRepository;

public class GameService {
    private final GameRepository gameRepository;

    public GameService() {
        this.gameRepository = new GameRepository();
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
        System.out.println("Partida guardada correctamente en la base de datos.");
    }
}
