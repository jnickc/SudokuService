package com.sudoku.core;

import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Nicolai Chebanov on 3/12/16.
 */
@Component
public class Sudoku {

    /*
     * Stores sudoku board values.
     */

    private int[][] board = {
            {7, 0, 0, 0, 4, 0, 5, 3, 0},
            {0, 0, 5, 0, 0, 8, 0, 1, 0},
            {0, 0, 8, 5, 0, 9, 0, 4, 0},
            {5, 3, 9, 0, 6, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 5},
            {8, 0, 0, 7, 2, 0, 9, 0, 0},
            {9, 0, 7, 4, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 5, 7, 0, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 5, 0}
    };

    /**
     * Checks that sudoku is resolved or not
     *
     * @return true if solution found, else false
     */
    public boolean checkSudokuStatus() {
        for (int i = 0; i < 9; i++) {

            int[] row = new int[9];
            int[] test  = new int[9];
            int[] square = new int[9];

            System.arraycopy(board[i], 0, row, 0, 9);

            for (int j = 0; j < 9; j++) {
                test[j] = board[j][i];
                square[j] = board[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            if (validateStatus(test) || validateStatus(row) || validateStatus(square))
                return false;
        }
        return true;
    }


    /**
     * Checks that no missing elements in the array
     *
     * @param array - array to check
     * @return - true if element is missing, esle false
     */
    private boolean validateStatus(int[] array) {
        int i = 0;
        Arrays.sort(array);
        for (int element : array) {
            if (element != ++i)
                return true;
        }
        return false;
    }

    /**
     * Validates that all values is in right range
     *
     * @param x     - row on sudoku board
     * @param y     - column on sudoku board
     * @param value - value to set
     * @return true if in range, else false
     */
    private boolean checkRange(int x, int y, int value) {

        boolean result = true;

        if (x > 8 || x < 0) {
            result = false;
        }
        if (y > 8 || y < 0) {
            result = false;
        }
        if (value > 9 || value < 1) {
            result = false;
        }

        return result;
    }

    /**
     * Check that in params is valid, and assigns value to cell
     *
     * @param row    - row on sudoku board
     * @param column - column on sudoku board
     * @param value  - value to set
     * @return - true if successfully set the value, else false
     */
    public Boolean setCellValue(int row, int column, Integer value) {

        if (!checkRange(row, column, value)) {
            return false;
        }

        board[row][column] = value;

        return true;
    }

    /**
     * Checks if move on board is right and doesn't violates sudoku rules
     *
     * @param x     - row number on the board
     * @param y     - column number on the board
     * @param value - value to check
     * @return - true if move is valid , else false
     */
    public boolean checkMove(int x, int y, int value) {

        if (!checkRange(x, y, value)) {
            return false;
        }

        int[] column = new int[9];
        int[] row = new int[9];
        System.arraycopy(board[x], 0, row, 0, 9);

        int boxRowOffset = (x / 3) * 3;
        int boxColOffset = (y / 3) * 3;

        for (int k = 0; k < 3; k++) // box
            for (int v = 0; v < 3; v++)
                if (value == board[boxRowOffset + k][boxColOffset + v]) {
                    return false;
                }

        for (int j = 0; j < 9; j++) {
            column[j] = board[j][y];
        }

        System.out.println("Debug board value : " + board[0][0]);

        if (validateMove(column, value) || validateMove(row, value))
            return false;

        return true;
    }

    /**
     * Check that value occures in array
     *
     * @param array - array to check
     * @param value - value to check
     * @return - true if occures, else false
     */
    private boolean validateMove(int[] array, int value) {

        boolean result = false;

        for (int element : array) {
            if (value == element) {
                result = true;
                break;
            }
        }

        return result;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

}
