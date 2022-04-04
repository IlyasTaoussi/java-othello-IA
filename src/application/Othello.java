package application;

import game.Board;
import game.Color;
import game.Strategy;
import players.Human;
import players.AI;
import players.Player;

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
            System.out.println(playerTwo.getName() + " wins (" + nbPlayerOne + " " + playerOne.getColor() + " / " +
                    nbPlayerTwo + " " + playerTwo.getColor() + ")");
        }
    }

    public static void main(String[] args) {
        Board b = new Board();

        Human playerOne = new Human("Ilyas", Color.BLACK);
        Human playerTwo = new Human("Birkan", Color.WHITE);
        Scanner sc = new Scanner(System.in);

        AI playerIAOne = new AI("IA 1", Color.WHITE, Strategy.ABSOLUTE);
        AI playerIATwo = new AI("IA 2", Color.BLACK, Strategy.MOBILITY);

        int counter = 0;
        long start = System.currentTimeMillis();
/*

        // Human VS Human
        while(!b.isFull()) {
            if(playerOne.canPlay(b)) {
                playerOne.play(b, sc);
            }
            if(playerTwo.canPlay(b)) {
                playerTwo.play(b, sc);
            }
        }

        // Human VS IA
        while(!b.isFull()) {
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

        long end = System.currentTimeMillis() - start;
        getWinner(playerIAOne, playerIATwo, b);
        System.out.println(timeToHMS(end/1000));
    }

    public static String timeToHMS(long tempsS) {

        int h = (int) (tempsS / 3600);
        int m = (int) ((tempsS % 3600) / 60);
        int s = (int) (tempsS % 60);

        String r="";

        if(h>0) {r+=h+" h ";}
        if(m>0) {r+=m+" min ";}
        if(s>0) {r+=s+" s";}
        if(h<=0 && m<=0 && s<=0) {r="0 s";}

        return r;
    }
}
