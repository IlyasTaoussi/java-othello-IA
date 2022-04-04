package game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Board {

    private final Position[] INITIAL_POSITIONS_WHITE = new Position[]{
            new Position(3, 3),
            new Position(4, 4)
    };
    private final Position[] INITIAL_POSITIONS_BLACK = new Position[]{
            new Position(3, 4),
            new Position(4, 3)
    };

    private Disk[][] board;

    public Board() {
        this.init();
    }

    public Board(Board board) {
        this.board = new Disk[8][8];
        for(int i = 0; i<board.board.length; i++) {
            for(int j = 0; j<board.board.length; j++) {
                this.board[i][j] = new Disk(board.board[i][j]);
            }
        }
    }

    private void init() {
        board = new Disk[8][8];

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = new Disk();
                board[x][y].setPos(new Position(x, y));
            }
        }
        board[INITIAL_POSITIONS_WHITE[0].getX()][INITIAL_POSITIONS_WHITE[0].getY()].setColor(Color.WHITE);
        board[INITIAL_POSITIONS_WHITE[1].getX()][INITIAL_POSITIONS_WHITE[1].getY()].setColor(Color.WHITE);

        board[INITIAL_POSITIONS_BLACK[0].getX()][INITIAL_POSITIONS_BLACK[0].getY()].setColor(Color.BLACK);
        board[INITIAL_POSITIONS_BLACK[1].getX()][INITIAL_POSITIONS_BLACK[1].getY()].setColor(Color.BLACK);
    }

    public Disk getDiskFromPos(Position pos){
        return board[pos.getX()][pos.getY()];
    }

    public ArrayList<Disk> searchNorth(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int x;

        if (board[X-1][Y].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X-1][Y].getPos()));
            x = X - 2;
            if (x <= -1) return null;
            while (board[x][Y].getColor() != Color.EMPTY) {
                if (board[x][Y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][Y].getPos()));
                    x--;
                    if (x <= -1) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchSouth(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int x ;

        if (board[X+1][Y].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X+1][Y].getPos()));
            x = X + 2;
            if (x >= 8) return null;
            while (board[x][Y].getColor() != Color.EMPTY) {
                if (board[x][Y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][Y].getPos()));
                    x++;
                    if (x >= 8) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchEast(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int y;

        if (board[X][Y+1].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X][Y+1].getPos()));
            y = Y + 2;
            if (y >= 8) return null;
            while (board[X][y].getColor() != Color.EMPTY) {
                if (board[X][y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[X][y].getPos()));
                    y++;
                    if (y >= 8) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchWest(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int y;
        if (board[X][Y-1].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X][Y-1].getPos()));
            y = Y - 2;
            if (y <= -1) return null;
            while (board[X][y].getColor() != Color.EMPTY) {
                if (board[X][y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[X][y].getPos()));
                    y--;
                    if (y <= -1) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchNorthEast(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int y; int x;
        if (board[X-1][Y+1].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X-1][Y+1].getPos()));
            x = X - 2;
            y = Y + 2;
            if (x <= -1 || y >= 8) return null;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    x--;
                    y++;
                    if (x <= -1 || y >= 8) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchNorthWest(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int y; int x;
        if (board[X-1][Y-1].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X-1][Y-1].getPos()));
            x = X - 2;
            y = Y - 2;
            if (x <= -1 || y <= -1) return null;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    x--;
                    y--;
                    if (x <= -1 || y <= -1) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchSouthWest(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int y; int x;
        if (board[X+1][Y-1].getColor() == disk.getOpponentColor()) {
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X+1][Y-1].getPos()));
            x = X + 2;
            y = Y - 2;
            if (x >= 8 || y <= -1) return null;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    x++;
                    y--;
                    if (x >= 8 || y <= -1) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<Disk> searchSouthEast(Disk disk){
        ArrayList<Disk> updatedDisks;

        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        int y; int x;
        if (board[X+1][Y+1].getColor() == disk.getOpponentColor()){
            updatedDisks = new ArrayList<>();
            updatedDisks.add(new Disk(disk.getColor(), board[X+1][Y+1].getPos()));
            x = X + 2;
            y = Y + 2;
            if (x >= 8 || y >= 8) return null;
            while (board[x][y].getColor() != Color.EMPTY) {
                if (board[x][y].getColor() == disk.getColor()) {
                    return updatedDisks;
                } else {
                    updatedDisks.add(new Disk(disk.getColor(), board[x][y].getPos()));
                    x++;
                    y++;
                    if (x >= 8 || y >= 8) return null;
                }
            }
        }
        return null;
    }

    public ArrayList<ArrayList<Disk>> getAvailablePlays(Disk disk) {
        ArrayList<ArrayList<Disk>> availablePlays = new ArrayList<>();
        ArrayList<Disk> availablePlay ;
        int X = disk.getPos().getX();
        int Y = disk.getPos().getY();

        if(X >= 0 && X < 7 ){
            availablePlay = searchSouth(disk);
            if(availablePlay != null) availablePlays.add(availablePlay);
            if(0 <= Y && Y < 7 ){
                availablePlay = searchSouthEast(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
                availablePlay = searchEast(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
            }
            if(0 < Y && Y <= 7 ){
                availablePlay = searchSouthWest(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
                availablePlay = searchWest(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
            }
            if(X != 0){
                if(0 < Y && Y <= 7){
                    availablePlay = searchNorthWest(disk);
                    if(availablePlay != null) availablePlays.add(availablePlay);
                }
                if(0 <= Y && Y < 7){
                    availablePlay = searchNorthEast(disk);
                    if(availablePlay != null) availablePlays.add(availablePlay);
                }
                availablePlay = searchNorth(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
            }
        }
        if(X == 7){
            availablePlay = searchNorth(disk);
            if(availablePlay != null) availablePlays.add(availablePlay);
            if(0 <= Y && Y < 7){
                availablePlay = searchNorthEast(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
                availablePlay = searchEast(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
            }
            if(0 < Y && Y <= 7 ){
                availablePlay = searchNorthWest(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
                availablePlay = searchWest(disk);
                if(availablePlay != null) availablePlays.add(availablePlay);
            }
        }
        return availablePlays;
    }

    public HashMap<Disk, ArrayList<ArrayList<Disk>>> getAllAvailablePlays(Color color){
        Disk disk;
        ArrayList<ArrayList<Disk>> availablePlays;
        HashMap<Disk, ArrayList<ArrayList<Disk>>> possiblePaths = new HashMap<>();
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board.length; y++){
                disk = new Disk(board[x][y].getColor(), board[x][y].getPos());
                if(disk.getColor() == Color.EMPTY){
                    disk.setColor(color);
                    availablePlays = getAvailablePlays(disk);
                    if(!availablePlays.isEmpty()){
                        possiblePaths.put(new Disk(Color.POSSIBLE,disk.getPos()), availablePlays);
                    }
                }
            }
        }
        //updateBoard(possiblePaths);
        return possiblePaths;
    }

    public void updateBoard(Disk disk){
        board[disk.getPos().getX()][disk.getPos().getY()] = disk;
    }

    public void updateBoard(List<Disk> disks) {
        for (Disk disk : disks) {
            updateBoard(disk);
        }
    }

    public void updateBoard(ArrayList<ArrayList<Disk>> paths){
        for(ArrayList<Disk> path : paths){
            updateBoard(path);
        }
    }

    public void updateBoard(HashMap<Disk, ArrayList<ArrayList<Disk>>> possiblePaths){
        updateBoard(new ArrayList<>(possiblePaths.keySet()));
    }

    public void updateBoard(HashMap<Disk, ArrayList<ArrayList<Disk>>> possiblePaths, Position chosen, Color color){
        List<Disk> possibleMoves = new ArrayList<>(possiblePaths.keySet());
        for(Disk disk : possibleMoves){
            if(disk.getPos().equals(chosen)){
                updateBoard(possiblePaths.get(disk));
                disk.setColor(color);
                updateBoard(disk);
                break;
            }
        }
    }

    public boolean isFull() {
        for(int x = 0; x< board.length; x++) {
            for(int y = 0; y<board[0].length; y++) {
                if(board[x][y].getColor() == Color.EMPTY)
                    return false;
            }
        }
        return true;
    }
    
    public int getNumberOfDisks(Color color){
        int counter = 0;
        for(int x=0; x < board.length; x++){
            for(int y=0; y < board.length; y++){
                if(board[x][y].getColor() == color) counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Board :\n ");
        for (int x = 0; x < board.length; x++) {
            sb.append("   ").append(x);
        }
        sb.append("\n");
        for (int x = 0; x < board.length; x++) {
            sb.append(x).append("   ");
            for (int y = 0; y < board[0].length; y++) {
                sb.append(board[x][y].getColor()).append("   ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
