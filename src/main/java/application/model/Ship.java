package application.model;

import jakarta.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int size;
    private String specialFeature;
    private int hits;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Ship() {}

    public Ship(String name, int size, String specialFeature) {
        this.name = name;
        this.size = size;
        this.specialFeature = specialFeature;
        this.hits = 0;
    }

    public boolean isSunk() {
        return hits >= size;
    }

    public void hit() {
        if (!isSunk()) hits++;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
    public String getSpecialFeature() { return specialFeature; }
    public void setSpecialFeature(String specialFeature) { this.specialFeature = specialFeature; }
    public int getHits() { return hits; }
    public void setHits(int hits) { this.hits = hits; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }
}
