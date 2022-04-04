package players;

import game.*;
import tree.Node;

public class IA extends Player{
    private Strategy strategy;
    private Node tree;
    private static final int MAX_DEPTH = 5;

    public IA(String name, Color color, Strategy strategy){
        super(name, color);
        this.strategy = strategy;
        this.tree = null;
    }

    private void createTree(Node node, int depth, Color color, Strategy strategy) {
        var plays = node.getBoard().getAllAvailablePlays(color);
        if(depth == IA.MAX_DEPTH || plays.keySet().size() == 0) {
            node.calculateWeight(strategy);
            return ;
        }
        if(node.getChildren().isEmpty()) {
            for (Disk disk : plays.keySet()) {
                Board board = new Board(node.getBoard());
                board.updateBoard(plays, disk.getPos(), color);
                Node n = new Node(board, new Disk(disk));
                node.addChild(n);
                if (color == Color.WHITE)
                    createTree(n, depth + 1, Color.BLACK, strategy);
                else
                    createTree(n, depth + 1, Color.WHITE, strategy);
            }
        }
        else {
            for(Node n : node.getChildren()) {
                if(color == Color.WHITE) createTree(n, depth+1, Color.BLACK, strategy);
                else createTree(n, depth+1, Color.WHITE, strategy);
            }
        }
    }

    public Position getStrategyBestPlay(Strategy strategy){
        createTree(tree, 0, color, strategy);
        Node node = alphaBeta(0, tree, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        for(Node n : node.getChildren()) {
            if(n.getWeight() == node.getWeight()) {
                tree = n;
                break;
            }
        }
        return tree.getDisk().getPos();
    }

    public void applyStrategy(Board board, Strategy strategy){
        board.updateBoard(plays, getStrategyBestPlay(strategy), color);
    }


    public void mixedStrategy(Board board, int counter) {
        if(counter <= 10){
            applyStrategy(board, Strategy.POSITIONAL);
        }
        else if(10 < counter && counter <= 20){
            applyStrategy(board, Strategy.MOBILITY);
        }
        else{
            applyStrategy(board, Strategy.ABSOLUTE);
        }
    }

    public Node alphaBeta(int depth, Node node, boolean maximizing, int alpha, int beta) {
        if(depth == IA.MAX_DEPTH) {
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

    public void play(Board board, int counter){
        tree = new Node(board);
        if(strategy != Strategy.MIXED) applyStrategy(board, strategy);
        else{
            mixedStrategy(board, counter);
        }
    }

    public void simulatePlay(Disk disk, Board board){
        board.updateBoard(this.getPlays(), disk.getPos(), disk.getColor());
    }
}
