package application.model;

import jakarta.persistence.*;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int number;
    private int size;
    private String specialFeature;
    private int level;
    private int hits;

    public Ship() {}

    public Ship(String name, int number, int size, String specialFeature, int level) {
        this.name = name;
        this.number = number;
        this.size = size;
        this.specialFeature = specialFeature;
        this.level = level;
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
    public String getName() { return name; }
    public int getNumber() { return number; }
    public int getSize() { return size; }
    public String getSpecialFeature() { return specialFeature; }
    public int getLevel() { return level; }
}
