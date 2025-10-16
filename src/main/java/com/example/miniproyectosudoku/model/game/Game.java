package com.example.miniproyectosudoku.model.game;

/**
 * Concrete implementation of the Sudoku game.
 * Handles game logic and player moves.
 * @version 1.0
 */
public class Game extends GameAbstract {

    /**
     * Starts a new Sudoku game by generating initial numbers.
     */
    @Override
    public void startNewGame() {
        board.generateInitialNumbers();
    }

    /**
     * Attempts to make a move on the board.
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to place (1-6, or 0 to clear)
     * @return true if the move was made, false otherwise
     */
    @Override
    public boolean makeMove(int row, int col, int value) {
        // Allow setting 0 (clearing a cell) or valid moves
        if (value == 0 || isValidMove(row, col, value)) {
            board.setValue(row, col, value);
            return true;
        }
        return false;
    }
}
