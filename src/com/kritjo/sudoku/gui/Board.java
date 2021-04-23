package com.kritjo.sudoku.gui;

import javax.swing.*;
import java.awt.*;

/**
 * GUI-representation of Sudoku board.
 */
public class Board extends JPanel {
    private final Cell[][] cells;

    /**
     * Original copy of board.
     */
    private final int[][] oriBoard;

    /**
     * @param cells Sudoku board to draw. int[row][col]
     */
    public Board(int[][] cells) {
        oriBoard = cells;
        this.cells = new Cell[cells.length][cells.length];
        for (int i = 0; i < cells.length; i++) {
            int[] row = cells[i];
            for (int j = 0; j < row.length; j++) {
                int col = row[j];
                this.cells[i][j] = new Cell(col);
            }
        }
    }

    /**
     * Logic for initializing GUI.
     */
    public void initGUI() {
        setLayout(new GridLayout(9,9));
        for (Cell[] cell : cells) {
            for (int col = 0; col < cells.length; col++) {
                cell[col].initGUI();
                add(cell[col]);
            }
        }
    }

    /**
     * @return int[row][col] representation of sudoku board.
     */
    public int[][] getBoard() {
        int[][] board = new int[cells.length][cells.length];
        for (int row = 0; row < cells.length; row++) {
            for (int col=0; col < cells.length; col++) {
                board[row][col] = cells[row][col].getNum();
            }
        }
        return board;
    }

    /**
     * @return original unmodified board int[row][col]
     */
    public int[][] getOriBoard() {
        return oriBoard;
    }

    /**
     * @return board size. Eg.: A 9x9 board will return 9.
     */
    public int getBoardSize() {
        return cells.length;
    }

    /**
     * Set num to a specific cell
     */
    public void setNum(int row, int col, int num) {
        cells[row][col].setNum(num);
    }
}
