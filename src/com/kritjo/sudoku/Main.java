package com.kritjo.sudoku;

import com.kritjo.sudoku.gui.Gameboard;

import javax.swing.*;
import java.io.IOException;

/**
 * Starts a GUI-window and populates the board from Berto Sugoku Api
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Gameboard gameboard = new Gameboard(BertoSugokuApiIntegration.getBoard());
        gameboard.initGUI();
        JFrame vindu = new JFrame("Sudoku");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.add(gameboard);
        vindu.pack();
        vindu.setVisible(true);
    }
}
