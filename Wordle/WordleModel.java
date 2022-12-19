package org.cis1200.Wordle;

import java.io.*;

public class WordleModel {

    private static String[][] gameBoard = new String[6][5];
    private static String currentWord;
    private static boolean isGameDone = false;
    public static String answer = WordList.getAns();
    private static boolean finalOutcome = false;
    private static int numGuesses = 0;



    public static boolean isGameDone(){

        return isGameDone;
    }

    public static boolean isGameWin(){

        return finalOutcome;
    }

    public static void registerGuess(String s){
        s = s.toLowerCase().trim();
        WordList w  = new WordList("files/wordleWordList.txt");

        if (w.inWordList(s)) {

        currentWord = s;

        if (currentWord.equals(answer)) {
            finalOutcome = true;
            isGameDone = true;
        } else if (numGuesses >= 6) {
            finalOutcome = false;

            isGameDone = true;
        }

        numGuesses++;

        }
    }

    public static void reset() {
        numGuesses = 0;
        isGameDone = false;
        finalOutcome = false;
        answer = WordList.getAns();
    }

    public static void saveGame() {

        if (finalOutcome) {
            try {
                BufferedWriter bw = new BufferedWriter
                        (new FileWriter("files/CorrectGuessesList.txt", true));

                bw.write("Correct Word " + answer + " in " + numGuesses);
                bw.newLine();

            } catch (IOException except) {
                except.printStackTrace();
            }
        }
    }
}

