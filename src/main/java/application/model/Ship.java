package application.model;

public class Ship {
    private final String name;
    private final int size;
    private final String specialFeature;
    private int hits;

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
        if (!isSunk()) {
            hits++;
        }
    }

    @Override
    public String toString() {
        return "Ship{name='" + name + "', size=" + size + ", specialFeature='" + specialFeature + "', hits=" + hits + "}";
    }
}
