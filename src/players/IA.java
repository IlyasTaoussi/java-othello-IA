package players;

import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class IA extends Player{
    private Strategy strategy;

    public IA(String name, Color color, Strategy strategy){
        super(name, color);
        this.strategy = strategy;
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


    private Position getMobilityStrategyBestPlay() {
        // TO DO

        return null;
    }

    public void mobilityStrategy(Board board) {
        board.updateBoard(plays, getMobilityStrategyBestPlay(), color);
    }

    private Position getAbsoluteBestPlay() {
        // TO DO
        return null;
    }

    public void absoluteStrategy(Board board) {
        board.updateBoard(plays, getAbsoluteBestPlay(), color);
    }

    public Position getMixedBestPlay() {
        // TO DO
        return null;
    }

    public void mixedStrategy(Board board) {
        board.updateBoard(plays, getMixedBestPlay(), color);
    }

    public void play(Board board){
        if(strategy == Strategy.POSITIONAL) {
            positionalStrategy(board);
        }
        else if(strategy == Strategy.MOBILITY) {
            mobilityStrategy(board);
        }
        else if(strategy == Strategy.ABSOLUTE) {
            absoluteStrategy(board);
        }
        else {
            mixedStrategy(board);
        }
    }
}
