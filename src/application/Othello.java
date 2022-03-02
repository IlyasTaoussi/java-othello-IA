package application;

import game.Board;
import game.Color;
import game.Position;
import players.Human;

import java.util.Scanner;

public class Othello {
    public static void main(String[] args) {
        Board b = new Board();
        Human playerOne = new Human("Ilyas", Color.BLACK);
        Human playerTwo = new Human("Birkan", Color.WHITE);

        Scanner sc = new Scanner(System.in);
        Position pos;

        while(!b.isFull()) {
            if(playerOne.canPlay(b)) {
                var map = b.getAllAvailablePlays(playerOne.getColor());
                System.out.println(b);
                int i = sc.nextInt();
                int j = sc.nextInt();
                pos = new Position(i,j);
                b.updateBoard(map, pos, playerOne.getColor());
                System.out.println(b);
            }
            if(playerTwo.canPlay(b)) {
                var map = b.getAllAvailablePlays(playerTwo.getColor());
                System.out.println(b);
                int i = sc.nextInt();
                int j = sc.nextInt();
                pos = new Position(i,j);
                b.updateBoard(map, pos, playerTwo.getColor());
                System.out.println(b);
            }
        }
    }
}
