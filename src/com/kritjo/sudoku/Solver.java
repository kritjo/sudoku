package com.kritjo.sudoku;

/**
 * Offers recursive solving of sudoku board.
 */
public class Solver {
    private Board board = null;

    /**
     * @param board Board to be solved.
     * @return Solved board.
     */
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


    /**
     * Actual recursive solving function. Set instance variable when solved.
     * @param board Board to be solved.
     * @throws ResultFoundException Thrown when board is solved.
     */
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
