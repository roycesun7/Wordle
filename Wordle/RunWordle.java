package org.cis1200.Wordle;


import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class RunWordle implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("Wordle");
        frame.setLocation(0, 0);
        frame.getContentPane().setBackground(Color.BLACK);

        //Title Panel
        final JPanel title = new JPanel();
        final JLabel text = new JLabel("WORDLE");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("", Font.BOLD, 50));
        title.add(text);
        title.setForeground(Color.WHITE);
        title.setBackground(Color.BLACK);
        frame.add(title, BorderLayout.NORTH);

        // Game board
        final JPanel groupPanel = new JPanel();

        groupPanel.setLocation(0, 0);
        groupPanel.setLayout(new BorderLayout());

        final JPanel statusPanel = new JPanel();
        final JLabel statusLabel = new JLabel();

        statusLabel.setText(" ");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("", Font.BOLD, 49));
        statusPanel.add(statusLabel);
        statusPanel.setBackground(Color.BLACK);

        final GameBoard board = new GameBoard(statusLabel);
        groupPanel.add(board, BorderLayout.CENTER);
        groupPanel.add(statusPanel, BorderLayout.SOUTH);

        frame.add(groupPanel, BorderLayout.CENTER);

        //Bottom Panel for Buttons
        final JPanel panelOfButtons = new JPanel();
        final JButton instructions = new JButton("INSTRUCTIONS");
        instructions.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Guess the 5-letter word in 6 tries. \n" +
                            "- All entries are real 5-letter words. \n" +
                            "- Tile green: letter is in the word in the right spot. \n" +
                            "- Tile yellow: it means the letter is in the word, wrong spot \n" +
                            "- Tile gray: letter not in te word");
                }
        );

        instructions.setFocusable(false);
        panelOfButtons.add(instructions);


        final JButton reset = new JButton("RESTART");
        reset.addActionListener(e -> {
            board.restart();
            System.out.println("New Game");
        });

        reset.setFocusable(false);
        panelOfButtons.add(reset);


        final JButton save = new JButton("SAVE");
        save.addActionListener(e -> {
            board.save();
            System.out.println("Save Game");}
        );
        save.setFocusable(false);
        panelOfButtons.add(save);



        panelOfButtons.setBackground(Color.WHITE);
        reset.setBackground(Color.WHITE);
        save.setBackground(Color.WHITE);

        frame.add(panelOfButtons, BorderLayout.SOUTH);


        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.restart();
    }
}