package com.kritjo.sudoku;

import java.util.ArrayList;

/**
 * Model of a sudoku board.
 */
public class Board {
    private final int[][] board;

    /**
     * @param board Sudoku board in the format int[row][col]. 0 for empty cell. int must be in the range 0-9.
     */
    public Board(int[][] board) {
        this.board = new int[board.length][board.length];
        for (int row = 0; row < board.length; row++) {
            System.arraycopy(board[row], 0, this.board[row], 0, board[row].length);
        }
    }

    /**
     * Handles extension of board, to be used with recursive loop.
     * @return new Board object with one more populated cell.
     */
    public Board[] extend() {
        Board[] result = new Board[9];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    ArrayList<Integer> possible = getPossible(row, col);
                    for (int i = 0; i < possible.size(); i++) {
                        int[][] newBoard = new int[board.length][board.length];
                        for (int r = 0; r < board.length; r++) {
                            System.arraycopy(board[r], 0, newBoard[r], 0, board[r].length);
                        }
                        newBoard[row][col] = possible.get(i);
                        result[i] = new Board(newBoard);
                    }
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Returns all possible values for a specific non-final cell.
     * @param row coordinate
     * @param col coordinate
     * @return List of all possible values.
     */
    private ArrayList<Integer> getPossible(int row, int col) {
        ArrayList<Integer> possible = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (onlyInBox(row, col, i) == 0 && onlyInCol(col, i) == 0 && onlyInRow(row, i) == 0) {
                possible.add(i);
            }
        }
        return possible;
    }

    /**
     * @return Is the board valid as per sudoku rules
     */
    public boolean valid() {
        boolean valid = true;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    if (valid) {
                        if (onlyInRow(row, board[row][col]) != 1) {
                            valid = false;
                        }
                    }
                    if (valid) {
                        if (onlyInCol(col, board[row][col]) != 1) {
                            valid = false;
                        }
                    }
                    if (valid) {
                        if (onlyInBox(row, col, board[row][col]) != 1) {
                            valid = false;
                        }
                    }
                }
            }
        }
        return valid;
    }

    /**
     * @return is the board valid and all cells populated
     */
    public boolean done() {
        return valid() && noZero();
    }

    /**
     * @return all cells populated
     */
    private boolean noZero() {
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Is num the only cell in row with this num.
     * @return how many occurences of num, including self.
     */
    private int onlyInRow(int row, int num) {
        int exists = 0;
        for (int col = 0; col < board[row].length; col++) {
            if (board[row][col] == num) {
                exists++;
            }
        }
        return exists;
    }

    /**
     * Is num the only cell in col with this num.
     * @return how many occurences of num, including self.
     */
    private int onlyInCol(int col, int num) {
        int exists = 0;
        for (int[] ints : board) {
            if (ints[col] == num) {
                exists++;
            }
        }
        return exists;
    }
    /**
     * Is num the only cell in box with this num.
     * @return how many occurences of num, including self.
     */
    private int onlyInBox(int row, int col, int num) {
        int startRow = (int) (Math.floor(((float) row) / 3)*3);
        int startCol = (int) (Math.floor(((float) col) / 3)*3);
        int endRow = startRow+2;
        int endCol = startCol+2;
        int exists = 0;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (board[r][c] == num) {
                    exists++;
                }
            }
        }
        return exists;
    }

    /**
     * @return Board as int[row][col]
     */
    public int[][] toArray() {
        int[][] arr = new int[board.length][board.length];
        for (int row = 0; row < board.length; row++) {
            System.arraycopy(board[row], 0, arr[row], 0, board[row].length);
        }
        return arr;
    }

}