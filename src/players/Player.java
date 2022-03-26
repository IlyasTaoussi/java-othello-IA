package players;

import game.Board;
import game.Color;
import game.Disk;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Player {
    protected String name;
    protected Color color;
    protected HashMap<Disk, ArrayList<ArrayList<Disk>>> plays;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public HashMap<Disk, ArrayList<ArrayList<Disk>>> getPlays() {
        return plays;
    }

    public boolean canPlay(Board board) {
        this.plays = board.getAllAvailablePlays(this.color);
        return !plays.isEmpty();
    }

}
