package com.sudoku.web;

import com.sudoku.core.Sudoku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * Controller for RESTful web services
 */
@RestController
public class SudokuController {

    /**
     * Sudoku board managed by spring
     */
    @Autowired
    private Sudoku sudokuBoard;

    /**
     * RESTful sercive to Get board values as 2D array
     * @return 2 dimensional array
     */
    @RequestMapping("/getBoard")
    public int [][] getBoard() {
        return sudokuBoard.getBoard();
    }

    /**
     * RESTful sercive to init sudoku board
     */
    @RequestMapping("/initBoard")
    public void getBoard(@RequestParam(value="board", required = true) int[][] board) {
        sudokuBoard.setBoard(board);
    }

    /**
     * RESTful sercive to set the cell value of sudoku board
     * @param row - row on sudoku board
     * @param column - column on sudoku board
     * @param value - value to set
     * @return - true if successfully set the value, else false
     */
    @RequestMapping("/setCellValue")
    public Boolean setCellValue(@RequestParam(value="row", required = true) Integer row,
                            @RequestParam(value="column", required = true) Integer column,
                            @RequestParam(value="value", required = true) Integer value){

        return sudokuBoard.setCellValue(row, column, value);
    };

    /**
     * RESTful service to check if candidate for cell doesn't violate the rules
     * @param row - row on sudoku board
     * @param column - column on sudoku board
     * @param value - value to set
     * @return - false if violates sudoku the rules, else true
     */
    @RequestMapping("/checkMove")
    public Boolean checkMove(@RequestParam(value="row", required = true) Integer row,
                            @RequestParam(value="column", required = true) Integer column,
                            @RequestParam(value="value", required = true) Integer value){

        return sudokuBoard.checkMove(row, column, value);
    };

    /**
     * RESTful service to check if sudoku is resolved
     * @return - true if resolved, else false
     */
    @RequestMapping("/checkSudokuStatus")
    public Boolean checkSudokuStatus(){

        return sudokuBoard.checkSudokuStatus();
    };
}
