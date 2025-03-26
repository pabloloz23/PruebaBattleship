package application.game;

import application.model.Game;
import application.service.GameService;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        GameService gameService = new GameService();
        gameService.saveGame(game);
    }
}
