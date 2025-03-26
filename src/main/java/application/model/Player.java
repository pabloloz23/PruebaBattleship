package application.model;

import application.builder.BoardBuilder;

public class Player {
    private final String name;
    private final Board board;

    public Player(String name, BoardBuilder boardBuilder) {
        this.name = name;
        this.board = boardBuilder.build();
    }

    public void attack(Player opponent) {
        System.out.println(name + " ataca a " + opponent.getName());
        boolean stillHasShips = opponent.board.attack();
        if (!stillHasShips) {
            System.out.println(opponent.getName() + " ya no tiene barcos.");
        }
    }

    public boolean hasShips() {
        return board.hasShips();
    }

    public String getName() {
        return name;
    }

    public void showBoard() {
        System.out.println(name + " - " + board);
    }
}
