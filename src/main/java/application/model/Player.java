package application.model;

import application.builder.BoardBuilder;

public class Player {
    private final String name;
    private final Board board;

    public Player(String name, BoardBuilder boardBuilder) {
        this.name = name;
        this.board = boardBuilder.build();
    }

    public boolean attack(Player opponent) {
        return opponent.board.attack();
    }

    public boolean hasShips() {
        return board.hasShips();
    }

    public String getName() {
        return name;
    }
}
