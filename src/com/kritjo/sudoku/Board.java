package com.kritjo.sudoku;

import java.util.ArrayList;

public class Board {
    private int[][] board;

    public Board(int[][] board) {
        this.board = new int[board.length][board.length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                this.board[row][col] = board[row][col];
            }
        }
    }

    public Board[] extend() {
        Board[] result = new Board[9];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == 0) {
                    ArrayList<Integer> possible = getPossible(row, col);
                    for (int i = 0; i < possible.size(); i++) {
                        int[][] newBoard = new int[board.length][board.length];
                        for (int r = 0; r < board.length; r++) {
                            for (int c = 0; c < board[r].length; c++) {
                                newBoard[r][c] = board[r][c];
                            }
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

    private ArrayList<Integer> getPossible(int row, int col) {
        ArrayList<Integer> possible = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (onlyInBox(row, col, i) == 0 && onlyInCol(col, i) == 0 && onlyInRow(row, i) == 0) {
                possible.add(i);
            }
        }
        return possible;
    }

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

    public boolean done() {
        return valid() && noZero();
    }

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

    private int onlyInRow(int row, int num) {
        int exists = 0;
        for (int col = 0; col < board[row].length; col++) {
            if (board[row][col] == num) {
                exists++;
            }
        }
        return exists;
    }

    private int onlyInCol(int col, int num) {
        int exists = 0;
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] == num) {
                exists++;
            }
        }
        return exists;
    }

    private int onlyInBox(int row, int col, int num) {
        int startRow = (int) (Math.floor(row / 3)*3);
        int startCol = (int) (Math.floor(col / 3)*3);
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

    public int[][] toArray() {
        int[][] arr = new int[board.length][board.length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                arr[row][col] = board[row][col];
            }
        }
        return arr;
    }

}
