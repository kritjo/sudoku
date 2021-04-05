package com.kritjo.sudoku;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Solver solver = new Solver();
        System.out.println(BertoSugokuApiIntegration.validate(solver.solve(BertoSugokuApiIntegration.getBoard())));
    }
}
