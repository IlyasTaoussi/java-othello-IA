package players;

import game.Board;
import game.Color;
import game.Disk;
import game.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Human {
    private String name;
    private Color color;
    private HashMap<Disk, ArrayList<ArrayList<Disk>>> plays;

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

    public HashMap<Disk, ArrayList<ArrayList<Disk>>> getPlays() {
        return plays;
    }

    public boolean canPlay(Board board) {
        this.plays = board.getAllAvailablePlays(this.color);
        return !plays.isEmpty();
    }

    public void play(Board board, Scanner sc){
        StringBuilder sb = new StringBuilder();
        Position pos;
        System.out.println(board);
        sb.append(name).append(": ");
        for(Disk disk : plays.keySet()){
            sb.append("(").append(disk.getPos().getX()).append(",").append(disk.getPos().getY()).append(") ");
        }
        System.out.println(sb);
        System.out.print("Answer Form x y : ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        pos = new Position(x,y);
        board.updateBoard(plays, pos, color);
    }
}
