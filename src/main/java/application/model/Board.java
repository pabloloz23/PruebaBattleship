package application.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int width;
    private int height;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Ship> ships;

    public Board() {}

    public Board(int width, int height, List<Ship> ships) {
        this.width = width;
        this.height = height;
        this.ships = ships;
    }

    public boolean hasShips() {
        return ships != null && !ships.isEmpty();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public List<Ship> getShips() { return ships; }
    public void setShips(List<Ship> ships) { this.ships = ships; }
}
