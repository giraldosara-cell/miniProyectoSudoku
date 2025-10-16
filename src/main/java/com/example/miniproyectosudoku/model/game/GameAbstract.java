package com.example.miniproyectosudoku.model.game;

import com.example.miniproyectosudoku.model.board.Board;
import java.util.HashSet;

/**
 * Abstract base class for Sudoku game implementations.
 * Provides common functionality for game management.
 * @version 1.0
 */
public abstract class GameAbstract implements IGame {
    protected Board board;

    /**
     * Constructor that initializes the board.
     */
    public GameAbstract() {
        this.board = new Board();
    }

    /**
     * Gets the current game board.
     *
     * @return A 2D array representing the board state
     */
    @Override
    public int[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Validates if a move is legal according to Sudoku rules.
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to validate
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean isValidMove(int row, int col, int value) {
        return board.isValidMove(row, col, value);
    }

    /**
     * Attempts to make a move on the board.
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to place
     * @return true if the move was successful, false otherwise
     */
    @Override
    public boolean makeMove(int row, int col, int value) {
        if (value == 0 || isValidMove(row, col, value)) {
            board.setValue(row, col, value);
            return true;
        }
        return false;
    }

    /**
     * Provides a hint for the next move.
     *
     * @return An array [row, col, value] or null if no hints available
     */
    @Override
    public int[] getHint() {
        return board.getHint();
    }

    /**
     * Checks if the game has been completed successfully.
     *
     * @return true if all cells are filled, false otherwise
     */
    @Override
    public boolean isGameOver() {
        int[][] grid = board.getBoard();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) return false;
            }
        }
        return true;
    }

    /**
     * Gets all empty cell positions.
     * Uses HashSet from Board for efficient tracking.
     *
     * @return A HashSet of empty cell positions in "row,col" format
     */
    public HashSet<String> getEmptyCells() {
        return board.getEmptyCells();
    }

    /**
     * Checks if a cell is part of the initial puzzle.
     *
     * @param row The row index
     * @param col The column index
     * @return true if the cell is from the initial puzzle, false otherwise
     */
    public boolean isInitialCell(int row, int col) {
        return board.isInitialCell(row, col);
    }
}