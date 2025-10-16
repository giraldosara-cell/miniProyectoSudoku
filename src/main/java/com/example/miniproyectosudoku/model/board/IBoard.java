package com.example.miniproyectosudoku.model.board;

/**
 * Interface for a generic Sudoku board.
 * Defines methods for board manipulation and validation.
 * @version 1.0
 */
public interface IBoard {

    /**
     * Gets the value at the specified cell.
     *
     * @param row The row index (0-based)
     * @param col The column index (0-based)
     * @return The value at the specified cell
     */
    int getValue(int row, int col);

    /**
     * Sets the value at the specified cell.
     *
     * @param row The row index (0-based)
     * @param col The column index (0-based)
     * @param value The value to set
     */
    void setValue(int row, int col, int value);

    /**
     * Validates if a move is valid according to Sudoku rules.
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to validate
     * @return true if the move is valid, false otherwise
     */
    boolean isValidMove(int row, int col, int value);

    /**
     * Generates the initial numbers for the board.
     */
    void generateInitialNumbers();

    /**
     * Gets the current state of the board.
     *
     * @return A 2D array representing the board
     */
    int[][] getBoard();
}