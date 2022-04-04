package tree;

import game.*;

import java.util.ArrayList;

public class Node {
    private Board board;
    private int weight;
    private ArrayList<Node> children;
    private Disk disk;

    public Node(Board board) {
        this.board = board;
        this.children = new ArrayList<>();
    }

    public Node(Board board, Disk disk) {
        this.board = board;
        this.disk = disk;
        this.children = new ArrayList<>();
    }

    public Disk getDisk() {
        return disk;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Board getBoard() {
        return board;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(weight).append(" ").append(children.size()).append("->");
        for(Node n : children) sb.append(n);
        sb.append("}\n");
        return sb.toString();
    }

    public void calculateWeight(Strategy strategy) {
        if(strategy == Strategy.POSITIONAL) {
            weight = Position.positional[disk.getPos().getX()][disk.getPos().getY()];
        }
        if(strategy == Strategy.MOBILITY) {
            weight = board.getAllAvailablePlays(disk.getColor()).keySet().size();
        }
        if(strategy == Strategy.ABSOLUTE) {
            if(disk.getColor() == Color.BLACK)
                weight = getNumberOfDisks(disk.getColor()) - getNumberOfDisks(Color.WHITE);
            else
                weight = getNumberOfDisks(disk.getColor()) - getNumberOfDisks(Color.BLACK);
        }
    }

    private int getNumberOfDisks(Color color){
        return board.getNumberOfDisks(color);
    }
}
