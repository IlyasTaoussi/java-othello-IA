package players;

import game.Board;
import game.Color;
import game.Disk;
import game.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Human extends Player {

    public Human(String name, Color color) {
        super(name, color);
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
