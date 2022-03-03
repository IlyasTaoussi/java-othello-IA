package players;

import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class IA {

    private String name;
    private Color color;
    private HashMap<Disk, ArrayList<ArrayList<Disk>>> plays;
    private Strategy strategy;

    public IA(String name, Color color, Strategy strategy){
        this.name = name;
        this.color = color;
        this.strategy = strategy;
    }

    public boolean canPlay(Board board) {
        this.plays = board.getAllAvailablePlays(this.color);
        return !plays.isEmpty();
    }

    public Position getPositionalStrategyBestPlay(){
        ArrayList<Position> evenScores = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        int score;
        for(Disk disk : plays.keySet()){
            score = Position.positional[disk.getPos().getX()][disk.getPos().getY()];
            if(score > bestScore){
                bestScore = score;
                evenScores = new ArrayList<>();
                evenScores.add(disk.getPos());
            }
            if(score == bestScore){
                evenScores.add(disk.getPos());
            }
        }
        Random random = new Random();
        return evenScores.get(random.nextInt(evenScores.size()));
    }

    public void positionalStrategy(Board board){
        board.updateBoard(plays, getPositionalStrategyBestPlay(), color);
    }

    public void play(Board board){
        if(strategy == Strategy.POSITIONAL){
            positionalStrategy(board);
        }
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
}
