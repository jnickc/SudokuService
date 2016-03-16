package com.sudoku.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nickc on 3/16/16.
 */
public class SudokuTest {


    Sudoku sudoku;

    @Before
    public void setup() throws Exception {
        sudoku = new Sudoku();
    }

    @Test
    public void testCheckSudokuStatus() throws Exception {

        int [][] coorrectBoard = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},

            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},

            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        sudoku.setBoard(coorrectBoard);
        assertEquals("Checking valid board", true, sudoku.checkSudokuStatus());


        int [][] wrongBoard = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},

                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 0, 8, 5, 6},

                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        sudoku.setBoard(wrongBoard);
        assertEquals("Checking valid board", false, sudoku.checkSudokuStatus());
    }

    @Test
    public void testSetCellValue() throws Exception {

        sudoku.setCellValue(0,0,3);
        assertEquals("Check cell value", 3, sudoku.getBoard()[0][0]);

        sudoku.setCellValue(0,0,5);
        assertEquals("Check cell value", 5, sudoku.getBoard()[0][0]);
    }

    @Test
    public void testCheckMove() throws Exception {
        Sudoku sudoku = new Sudoku();
        System.out.println("value " + sudoku.checkMove(1,0,6));
    }
}