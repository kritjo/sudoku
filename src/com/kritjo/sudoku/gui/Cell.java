package com.kritjo.sudoku.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Represention of a single cell on the sudoku board.
 */
public class Cell extends JTextField {
    private int num;

    public Cell(int num) {
        this.num = num;
    }

    public int getNum() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setNum(int num) {
        this.num = num;
        setText(String.valueOf(num));
    }

    /**
     * Logic for initializing GUI.
     */
    public void initGUI() {
        setDocument(new JTextFieldLimit(1));
        setPreferredSize(new Dimension(80,80));
        setFont(new Font("Monospaced", Font.BOLD, 50));
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setDisabledTextColor(Color.BLACK);
        if (num != 0) {
            setText(String.valueOf(num));
            setEnabled(false);
        }
    }
}
