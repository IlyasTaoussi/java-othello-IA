package players;

import game.Board;
import game.Color;

public class Human {
    private String name;
    private Color color;

    public Human(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }

    public boolean canPlay(Board board) {
        return !board.getAllAvailablePlays(this.color).isEmpty();
    }
}
