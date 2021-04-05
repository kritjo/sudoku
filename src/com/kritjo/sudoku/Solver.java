package com.kritjo.sudoku;

public class Solver {
    private Board board = null;

    public int[][] solve(int[][] board) {
        Board boardObj = new Board(board);
        try {
            recPartial(boardObj);
        } catch (ResultFoundException ignore) {
        }
        if (this.board != null) {
            return this.board.toArray();
        }
        return null;
    }


    private void recPartial(Board board) throws ResultFoundException {
        if (board.done()) {
            this.board = board;
            throw new ResultFoundException();
        } else {
            if (board.valid()) {
                for (Board b : board.extend()) {
                    if (b != null) {
                        if (b.valid()) {
                            recPartial(b);
                        }
                    }
                }
            }
        }
    }
}
