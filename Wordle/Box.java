package org.cis1200.Wordle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Box extends JLabel {

    private Border border = null;

    public Box(){
        this.setBorder(border);
        this.setText(" ");
        this.setSize(50, 50);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        border = BorderFactory.createLineBorder(WordleColors.OUTLINE, 7);  //the initial border color is gray
    }


    public void set (int value, String s) {
        this.setText(s);
        this.setColor(value);
    }

    public void set (String s) {
        this.setText(s);
    }


    public void set (int value){
        this.setColor(value);
    }


    private void setColor(int value) {
        this.setForeground(Color.WHITE);
        if (value == 1) {
            this.border = BorderFactory.createLineBorder(WordleColors.GRAY, 4);
            this.setBorder(border);
            this.setBackground(WordleColors.GRAY);
        } else if (value == 2) {
            this.border = BorderFactory.createLineBorder(WordleColors.YELLOW, 4);
            this.setBorder(border);
            this.setBackground(WordleColors.YELLOW);
        } else if (value == 3) {
            this.border = BorderFactory.createLineBorder(WordleColors.GREEN, 4);
            this.setBorder(border);
            this.setBackground(WordleColors.GREEN);
        } else {
            this.border = BorderFactory.createLineBorder(WordleColors.OUTLINE, 4);
            this.setBorder(border);
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
    }

    public void updateColor(String s) {
        if (s == null){
            this.setBackground(Color.WHITE);
        }
        else {
            switch (s) {
                case "3" -> this.setBackground(WordleColors.GREEN);
                case "2" -> this.setBackground(WordleColors.YELLOW);
                case "1" -> this.setBackground(WordleColors.GRAY);

                default -> this.setBackground(Color.BLACK);
            }
        }
    }



}