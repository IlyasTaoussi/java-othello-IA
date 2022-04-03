package application;

import game.Board;
import game.Color;
import game.Position;
import game.Strategy;
import players.Human;
import players.IA;
import players.Player;

import java.awt.*;
import java.util.Scanner;

public class Othello {

    public static void getWinner(Player playerOne, Player playerTwo, Board board){
        System.out.println("The game is over");
        int nbPlayerOne = board.getNumberOfDisks(playerOne.getColor());
        int nbPlayerTwo = board.getNumberOfDisks(playerTwo.getColor());
        if(nbPlayerOne == nbPlayerTwo) {
            System.out.println("It's a draw");
        }
        else if(nbPlayerOne > nbPlayerTwo) {
            System.out.println(playerOne.getName() + " wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerTwo + " " + playerTwo.getColor() + ")");
        }
        else {
            System.out.println(playerTwo.getName() + "wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerTwo + " " + playerTwo.getColor() + ")");
        }
    }

    public static void main(String[] args) {
        Board b = new Board();

        Human playerOne = new Human("Ilyas", Color.BLACK);
        Human playerTwo = new Human("Birkan", Color.WHITE);

        IA playerIAOne = new IA("IA 1", Color.WHITE, Strategy.ABSOLUTE);
        IA playerIATwo = new IA("IA 2", Color.BLACK, Strategy.MOBILITY);

        int counter = 0;

        Scanner sc = new Scanner(System.in);
/*
        while(!b.isFull()) {
            if(playerOne.canPlay(b)) {
                playerOne.play(b, sc);
            }
            if(playerTwo.canPlay(b)) {
                playerTwo.play(b, sc);
            }
        }
*/

/*        while(!b.isFull()) {
            if(playerOne.canPlay(b)) {
                playerOne.play(b, sc);
            }
            if(playerIAOne.canPlay(b)) {
                playerIAOne.play(b);
            }
        }
*/

        while(!b.isFull()) {
            if(playerIAOne.canPlay(b)) {
                playerIAOne.play(b, counter);
            }
            if(playerIATwo.canPlay(b)) {
                playerIATwo.play(b, counter);
            }
            counter++;
            System.out.println(b);
        }
        getWinner(playerIAOne, playerIATwo, b);
    }
}
