package com.kritjo.sudoku;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver();
        System.out.println(BertoSugokuApiIntegration.validate(solver.solve(BertoSugokuApiIntegration.getBoard())));
    }

    public static int[][] INTtextToBoard(String text){
        int[][] b = new int[9][9];
        char[] text_symbols = text.toCharArray();

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int v =  Character.getNumericValue(text_symbols[j + (i * 9)]);
                b[i][j] = v;
            }
        }
        return b;
    }
}
