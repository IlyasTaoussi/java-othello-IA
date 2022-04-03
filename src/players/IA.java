package players;

import game.*;
import tree.Node;

import java.util.ArrayList;
import java.util.Random;

public class IA extends Player{
    private Strategy strategy;
    private Node tree;

    public IA(String name, Color color, Strategy strategy){
        super(name, color);
        this.strategy = strategy;
        this.tree = null;
    }

    private static void createTree(Node node, int depth, Color color) {
        var plays = node.getBoard().getAllAvailablePlays(color);
        if(depth == 7 || plays.keySet().size() == 0) {
            node.calculateWeight(Strategy.MOBILITY);
            return;
        }
        if(node.getChildren().isEmpty()) {
            for (Disk disk : plays.keySet()) {
                Board board = new Board(node.getBoard());
                board.updateBoard(plays, disk.getPos(), color);
                Node n = new Node(board, new Disk(disk));
                node.addChild(n);
                if (color == Color.WHITE)
                    createTree(n, depth + 1, Color.BLACK);
                else
                    createTree(n, depth + 1, Color.WHITE);
            }
        }
        else {
            for(Node n : node.getChildren()) {
                if(color == Color.WHITE) createTree(n, depth+1, Color.BLACK);
                else createTree(n, depth+1, Color.WHITE);
            }
        }
    }

    public Position getPositionalStrategyBestPlay(){
        /*ArrayList<Position> evenScores = new ArrayList<>();
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
        return evenScores.get(random.nextInt(evenScores.size()));*/
        createTree(tree, 0, color);
        Node node = alphaBeta(0, tree, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        for(Node n : node.getChildren()) {
            if(n.getWeight() == node.getWeight()) {
                tree = node;
                break;
            }
        }
        return node.getDisk().getPos();
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

    public Node alphaBeta(int depth, Node node, boolean maximizing, int alpha, int beta) {
        if(depth == 3) {
            node.calculateWeight(this.strategy);
            return node;
        }
        int best;
        if(maximizing) {
            best = Integer.MIN_VALUE;

            for(Node n : node.getChildren()) {
                Node tmp = alphaBeta(depth+1, n, false, alpha, beta);

                best = Math.max(best, tmp.getWeight());
                alpha = Math.max(alpha, best);
                if(beta <= alpha) break;
            }
        }
        else {
            best = Integer.MAX_VALUE;

            for(Node n : node.getChildren()) {
                Node tmp = alphaBeta(depth++, n, true, alpha, beta);

                best = Math.min(best, tmp.getWeight());
                beta = Math.min(beta, best);

                if(beta <= alpha) break;
            }
        }
        node.setWeight(best);
        return node;
    }

    public void play(Board board){
        if(tree == null) tree = new Node(board);
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

    public void simulatePlay(Disk disk, Board board){
        board.updateBoard(this.getPlays(), disk.getPos(), disk.getColor());
    }
}
