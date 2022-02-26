package game;

import java.util.ArrayList;

public class Board {

    private final Position[] INITIAL_POSITIONS_WHITE = new Position[]{
            new Position(3, 3),
            new Position(4, 4)
    };
    private final Position[] INITIAL_POSITIONS_BLACK = new Position[]{
            new Position(3, 4),
            new Position(4, 3)
    };
    private final Position[] BOARD_CORNERS = new Position[]{
            new Position(0, 0),
            new Position(7, 0),
            new Position(0, 7),
            new Position(7, 7)
    };
    private Disk[][] board;

    public Board() {
        this.init();
    }

    private void init() {
        board = new Disk[8][8];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Disk();
                board[i][j].setPos(new Position(i, j));
            }
        }
        board[INITIAL_POSITIONS_WHITE[0].getX()][INITIAL_POSITIONS_WHITE[0].getY()].setColor(Color.WHITE);
        board[INITIAL_POSITIONS_WHITE[1].getX()][INITIAL_POSITIONS_WHITE[1].getY()].setColor(Color.WHITE);

        board[INITIAL_POSITIONS_BLACK[0].getX()][INITIAL_POSITIONS_BLACK[0].getY()].setColor(Color.BLACK);
        board[INITIAL_POSITIONS_BLACK[1].getX()][INITIAL_POSITIONS_BLACK[1].getY()].setColor(Color.BLACK);
    }

    public void updateBoard(Disk[] disks) {
        for (int i = 0; i < disks.length; i++) {
            board[disks[i].getPos().getX()][disks[i].getPos().getY()] = disks[i];
        }
    }

    public void getAvailablePlays(Disk disk) {
        ArrayList<ArrayList<Disk>> availablePlays = new ArrayList<>();

        Disk opponentDisc = new Disk();
        if (disk.getColor() == Color.BLACK) opponentDisc.setColor(Color.WHITE);
        if (disk.getColor() == Color.WHITE) opponentDisc.setColor(Color.BLACK);

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int x = 0; int y = 0;

        ArrayList<Disk> updatedDisks;

        // Horizontal Right
        if (board[X][Y+1].getColor() == opponentDisc.getColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[x][y+1].getPos()));
            y = Y + 2;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    updatedDisks.add(new Disk(disk.getColor(), board[0][y].getPos()));
                    availablePlays.add(updatedDisks);
                    break;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    y++;
                    if (y == 8) break;
                }
            }
        }

        //Horizontal Left
        if (board[X][Y-1].getColor() == opponentDisc.getColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[x][y-1].getPos()));
            y = Y - 2;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    availablePlays.add(updatedDisks);
                    break;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    y--;
                    if (y == 0) break;
                }
            }
        }

        // Vertical Down
        if (board[X+1][Y].getColor() == opponentDisc.getColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[x+1][y].getPos()));
            x = X + 2;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    availablePlays.add(updatedDisks);
                    break;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    x++;
                    if (x == 8) break;
                }
            }
        }

        //Vertical Up
        if (board[X-1][Y].getColor() == opponentDisc.getColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[0][y].getPos()));
            x = X - 2;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    availablePlays.add(updatedDisks);
                    break;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    x--;
                    if (x == 0) break;
                }
            }


        }

        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board.length - 1; j++) {

            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board :\n ");
        for (int i = 0; i < board.length; i++) {
            sb.append("   " + i);
        }
        sb.append("\n");
        for (int i = 0; i < board.length; i++) {
            sb.append(i + "   ");
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j].getColor() + "   ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
