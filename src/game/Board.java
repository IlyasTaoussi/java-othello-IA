package game;

import java.util.Arrays;

public class Board {
    private Color[][] board;
    private final Position[] INITIAL_POSITIONS_WHITE = new Position[] {
            new Position(3,3),
            new Position(4,4)
    };
    private final Position[] INITIAL_POSITIONS_BLACK = new Position[] {
            new Position(3,4),
            new Position(4,3)
    };

    public Board() {
        this.init();
    }

    private void init() {
        board = new Color[8][8];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                board[i][j] = Color.EMPTY;
            }
        }
        board[INITIAL_POSITIONS_WHITE[0].getX()][INITIAL_POSITIONS_WHITE[0].getY()] = Color.WHITE;
        board[INITIAL_POSITIONS_WHITE[1].getX()][INITIAL_POSITIONS_WHITE[1].getY()] = Color.WHITE;

        board[INITIAL_POSITIONS_BLACK[0].getX()][INITIAL_POSITIONS_BLACK[0].getY()] = Color.BLACK;
        board[INITIAL_POSITIONS_BLACK[1].getX()][INITIAL_POSITIONS_BLACK[1].getY()] = Color.BLACK;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board :\n ");
        for (int i = 0; i<board.length; i++) {
            sb.append("   " + i);
        }
        sb.append("\n");
        for(int i = 0; i<board.length; i++) {
            sb.append(i + "   ");
            for(int j = 0; j<board[0].length; j++) {
                sb.append(board[i][j] + "   ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
