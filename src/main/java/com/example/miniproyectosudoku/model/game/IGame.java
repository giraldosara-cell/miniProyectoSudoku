package com.example.miniproyectosudoku.model.game;

/**
 * Interface for the Sudoku game logic.
 * Defines methods for game management, move validation, and gameplay state.
 * @version 1.0
 */
public interface IGame {

    /**
     * Starts a new Sudoku game and initializes the board.
     */
    void startNewGame();

    /**
     * Attempts to make a move on the board.
     *
     * @param row The row index (0-based)
     * @param col The column index (0-based)
     * @param value The value to place (1-6, or 0 to clear)
     * @return true if the move was made, false otherwise
     */
    boolean makeMove(int row, int col, int value);

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
     * Checks if the game is over (all cells filled correctly).
     *
     * @return true if the game is over, false otherwise
     */
    boolean isGameOver();

    /**
     * Gets the current state of the board.
     *
     * @return A 2D array representing the board
     */
    int[][] getBoard();

    /**
     * Provides a hint for the player (the correct value for an empty cell).
     *
     * @return An array [row, col, value] or null if no hints available
     */
    int[] getHint();
}