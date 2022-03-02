package application;

import game.Board;
import game.Color;
import game.Position;
import players.Human;

import java.awt.*;
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
                System.out.println(playerOne.getPlays());
                System.out.println(b);
                int i = sc.nextInt();
                int j = sc.nextInt();
                pos = new Position(i,j);
                b.updateBoard(playerOne.getPlays(), pos, playerOne.getColor());
                System.out.println(b);
            }
            if(playerTwo.canPlay(b)) {
                System.out.println(b);
                int i = sc.nextInt();
                int j = sc.nextInt();
                pos = new Position(i,j);
                b.updateBoard(playerTwo.getPlays(), pos, playerTwo.getColor());
                System.out.println(b);
            }
        }
        System.out.println("The game is over");
        int nbPlayerOne = b.getNumberOfDisks(playerOne.getColor());
        int nbPlayerTwo = b.getNumberOfDisks(playerTwo.getColor());
        if(nbPlayerOne == nbPlayerTwo) {
            System.out.println("It's a draw");
        }
        else if(nbPlayerOne > nbPlayerTwo) {
            System.out.println(playerOne.getName() + " wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerTwo + " " + playerTwo.getColor());
        }
        else {
            System.out.println(playerTwo.getName() + "wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerTwo + " " + playerTwo.getColor());
        }
    }
}
