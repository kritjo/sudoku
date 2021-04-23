package com.kritjo.sudoku.gui;

import com.kritjo.sudoku.BertoSugokuApiIntegration;
import com.kritjo.sudoku.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * GUI-representation of sudoku board and options for user.
 */
public class Gameboard extends JPanel {
    private final Board board;

    /**
     * @param board Sudoku board to draw. int[row][col]
     */
    public Gameboard(int[][] board) {
        this.board = new Board(board);
    }

    /**
     * Logic for initializing GUI.
     */
    public void initGUI() {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 5;
        c.ipady = 5;

        board.initGUI();
        add(board, c);


        JButton solve = new JButton("Solve");
        class SolveHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Solver solver = new Solver();
                int[][] doneBoard = solver.solve(board.getOriBoard());
                for (int row = 0; row < board.getBoardSize(); row++) {
                    for (int col = 0; col < board.getBoardSize(); col++) {
                        board.setNum(row, col, doneBoard[row][col]);
                    }
                }
            }
        }
        solve.addActionListener(new SolveHandler());
        c.gridy = 1;
        add(solve, c);

        JLabel state = new JLabel("Not solved");

        JButton checkSolution = new JButton("Check Your Solution");
        class CheckSolutionHandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (BertoSugokuApiIntegration.validate(board.getBoard())) {
                        state.setText("Solved! All done.");
                    } else {
                        state.setText("Not solved");
                    }
                } catch (IOException e) {
                    state.setText("CAN NOT CONNECT TO API");
                }
            }
        }
        checkSolution.addActionListener(new CheckSolutionHandler());
        add(checkSolution, c);
        c.gridy = 2;
        add(state, c);
    }
}