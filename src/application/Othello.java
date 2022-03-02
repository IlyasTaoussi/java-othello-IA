package application;

import game.Board;
import game.Color;
import game.Position;
import game.Strategy;
import players.Human;
import players.IA;

import java.awt.*;
import java.util.Scanner;

public class Othello {

    public static void getWinner(Human playerOne, Human playerTwo, Board board){
        System.out.println("The game is over");
        int nbPlayerOne = board.getNumberOfDisks(playerOne.getColor());
        int nbPlayerTwo = board.getNumberOfDisks(playerTwo.getColor());
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

    public static void getWinner(Human playerOne, IA playerIA, Board board){
        System.out.println("The game is over");
        int nbPlayerOne = board.getNumberOfDisks(playerOne.getColor());
        int nbPlayerIA = board.getNumberOfDisks(playerIA.getColor());
        if(nbPlayerOne == nbPlayerIA) {
            System.out.println("It's a draw");
        }
        else if(nbPlayerOne > nbPlayerIA) {
            System.out.println(playerOne.getName() + " wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerIA + " " + playerIA.getColor());
        }
        else {
            System.out.println(playerIA.getName() + "wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerIA + " " + playerIA.getColor());
        }
    }

    public static void getWinner(IA playerIAOne, IA playerIATwo, Board board){
        System.out.println("The game is over");
        int nbPlayerIAOne = board.getNumberOfDisks(playerIAOne.getColor());
        int nbPlayerIATwo = board.getNumberOfDisks(playerIATwo.getColor());
        if(nbPlayerIAOne == nbPlayerIATwo) {
            System.out.println("It's a draw");
        }
        else if(nbPlayerIAOne > nbPlayerIATwo) {
            System.out.println(playerIAOne.getName() + " wins (" + nbPlayerIAOne + " " + playerIAOne.getColor() + " / " +
                    nbPlayerIATwo + " " + playerIATwo.getColor());
        }
        else {
            System.out.println(playerIATwo.getName() + "wins (" + nbPlayerIAOne + " " + playerIAOne.getColor() + " / " +
                    nbPlayerIATwo + " " + playerIATwo.getColor());
        }
    }

    public static void main(String[] args) {
        Board b = new Board();

        Human playerOne = new Human("Ilyas", Color.BLACK);
        Human playerTwo = new Human("Birkan", Color.WHITE);

        IA playerIAOne = new IA("IA 1", Color.BLACK, Strategy.POSITIONAL);
        IA playerIATwo = new IA("IA 2", Color.WHITE, Strategy.POSITIONAL);

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
        getWinner(playerOne, playerTwo, b);
*/

        while(!b.isFull()) {
            if(playerOne.canPlay(b)) {
                playerOne.play(b, sc);
            }
            if(playerIAOne.canPlay(b)) {
                playerIAOne.play(b);
            }
        }
        getWinner(playerOne, playerIAOne, b);

/*
        while(!b.isFull()) {
            if(playerIAOne.canPlay(b)) {
                playerOne.play(b, sc);
            }
            if(playerIATwo.canPlay(b)) {
                playerIATwo.play(b);
            }
        }
        getWinner(playerOne, playerIAOne, b);

 */
    }
}
