package com.kritjo.sudoku;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;


public class BertoSugokuApiIntegration {
    /**
     * @return A 9x9 sudoku board on hard difficulty.
     * @throws IOException Thrown whenever the API can not be called or there is an error with the call.
     */
    public static int[] @NotNull [] getBoard() throws IOException {
        return getBoard("hard");
    }

    /**
     * @param difficulty "easy", "medium" or "hard". The difficulty of the Sudoku-board.
     * @return A 9x9 sudoku board.
     * @throws IOException Thrown whenever the API can not be called or there is an error with the call.
     */
    public static int[] @NotNull [] getBoard(String difficulty) throws IOException {
        URL url = new URL("https://sugoku.herokuapp.com/board?difficulty=" + difficulty);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        Scanner in = new Scanner(con.getInputStream());
        String input = in.next();
        int[][] board = new int[9][9];
        int i = 0;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                boolean isNum = false;
                while (!isNum) {
                    try {
                        board[row][col] = Integer.parseInt(input.substring(i, i + 1));
                        i++;
                        isNum = true;
                    } catch (NumberFormatException e) {
                        i++;
                    }
                }
            }
        }
        return board;
    }

    public static boolean validate(int[][] board) throws IOException {
        StringBuilder urlParameters = encodeUrl(board);
        String request = "https://sugoku.herokuapp.com/validate";
        URL url = new URL(request);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(urlParameters.toString());
        InputStreamReader response = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(response);
        StringBuilder httpResponse = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            httpResponse.append(line);
        }

        return httpResponse.toString().equals("{\"status\":\"solved\"}");
    }

    private static StringBuilder encodeUrl(int[][] board) throws UnsupportedEncodingException {
        StringBuilder urlParameters  = new StringBuilder("[");
        for (int row = 0; row < board.length; row++) {
            urlParameters.append("[");
            for (int col = 0; col < board.length; col++) {
                urlParameters.append(board[row][col]);
                if (col < board.length-1) {
                    urlParameters.append(",");
                }
            }
            urlParameters.append("]");
            if (row < board.length -1) {
                urlParameters.append(",");
            } else {
                urlParameters.append("]");
            }
        }
        return new StringBuilder("board=" + URLEncoder.encode(urlParameters.toString(), "UTF-8"));
    }
}
