package org.cis1200.Wordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.cis1200.Wordle.WordleModel.registerGuess;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private final static Box[][] boxes = new Box[6][5];
    public static final int WIDTH = 450;
    public static final int HEIGHT = 630;
    private static JLabel status;
    static LinkedList<String> guessesList = new LinkedList<>();
    private static int curRow = 0;
    private WordList w  = new WordList("files/wordleWordList.txt");

    public GameBoard(JLabel s){
        this.setLayout(new GridLayout(6, 5, 7, 7));
        this.setSize(WIDTH, HEIGHT);
        status = s;
        for (int i = 0; i < boxes.length; i++){
            for (int j = 0; j < boxes[i].length; j++){
                boxes[i][j] = new Box();
                this.add(boxes[i][j]);
            }

        }

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();

                if (!(WordleModel.isGameDone())) {

                    if (Character.isLetter(key) && guessesList.size() < 5) {
                        guessesList.add(String.valueOf(key));
                        Append();
                    } else if (key == KeyEvent.VK_BACK_SPACE) {
                        try {
                            guessesList.removeLast();
                            Append();
                        } catch (NoSuchElementException ignored) {}
                    } else if (key == KeyEvent.VK_ENTER) {
                        update();
                    }
                    updateStatus();
                }

            }

        });

    }

    public static void setCurRow(int curRow) {
        GameBoard.curRow = curRow;
    }


    public Box[] getRow(int row){
        return this.boxes[row];
    }


    public Box getBox(int row, int col){
        return this.boxes[row][col];
    }

    public void updateStatus(){
        if(WordleModel.isGameDone()){
            String s;
            if(!(WordleModel.isGameWin())) {
                s = "You Lose!";
            }

            else {
                s = "You Win!";
            }

            status.setText(s);
        }
    }

    public void Append() {
        Iterator<String> g = guessesList.iterator();
        for (int i = 0; i < 5; i++) {
            if (g.hasNext()) {
                boxes[curRow][i].set(g.next().toUpperCase());
            } else {
                boxes[curRow][i].set(" ");
            }
        }
    }

    public void save(){
        WordleModel.saveGame();
    }



    public void restart() {
        guessesList = new LinkedList<>();
        curRow = 0;
        for (int i = 0; i < boxes.length; i++){
            for (int j = 0; j < boxes[i].length; j++){
                boxes[i][j].set(4, " ");
            }
        }
        WordleModel.reset();
        requestFocusInWindow();
    }

    /*static void updateLine(Box[] row, WordList wordList){
        String answer = WordleModel.answer;
        String temp = "";
        Box box;

        for (Box singleBox : row) {
            box = singleBox;
            temp = temp.concat(box.getText());
        }

        if (wordList.inWordList(temp)) {
            for (int i = 0; i < row.length; i++) {
                box = row[i];

                char c = box.getText().charAt(0);
                String s = String.valueOf(c);
                int indexInAnswer = answer.indexOf(c);

                if (answer.indexOf(c) != -1) {
                    if (indexInAnswer == i) {
                        box.set(3,s);
                    } else {
                        box.set(2,s);
                    }
                    char[] chars = answer.toCharArray();
                    chars[indexInAnswer] = '*';
                    answer = new String(chars);

                } else {
                    box.set(1,s);
                }

            }

            }
        }*/

    public void updateLine(Box[] row) {
        String answer = WordleModel.answer;
        Box box;

        StringBuilder s = new StringBuilder();

        for (String letter : guessesList) {
            s.append(letter);
        }

        String str = String.valueOf(s);

        if (str.length() == 5) {
            for (int i = 0; i < row.length; i++) {
                box = row[i];
                String c = str.substring(i, i + 1);
                int indexInAnswer = answer.indexOf(c);
                if (answer.indexOf(c) != -1) {
                    if (indexInAnswer == i) {
                        box.set(3, c);
                    } else {
                        box.set(2, c);
                    }
                } else {
                    box.set(1, c);
                }

            }

        } else {
            throw new NoSuchElementException();
        }
    }


    private void update() {
        StringBuilder s = new StringBuilder();

        for (String letter: guessesList) {
            s.append(letter);
        }

        String str = String.valueOf(s);

        if (w.inWordList(str)) {
            status.setText(" ");
            updateLine(boxes[curRow]);
            curRow++;
        }
        else {
            status.setText("Word Does Not Exist".toUpperCase());
        }


        guessesList = new LinkedList<>();
        registerGuess(str);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }


}

