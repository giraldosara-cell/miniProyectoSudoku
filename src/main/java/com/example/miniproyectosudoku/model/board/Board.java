package com.example.miniproyectosudoku.model.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Represents a 6x6 Sudoku board with 2x3 blocks.
 * Implements board generation using backtracking algorithm and validation logic.
 * @version 1.0
 */
public class Board implements IBoard {

    private final int SIZE = 6;
    private final int[][] board = new int[SIZE][SIZE];
    private final int[][] solution = new int[SIZE][SIZE]; // Stores the complete solution
    private final HashSet<String> initialCells = new HashSet<>(); // Tracks initial (non-editable) cells

    /**
     * Gets the value at a specific cell.
     *
     * @param row The row index (0-5)
     * @param col The column index (0-5)
     * @return The value at the specified cell
     */
    @Override
    public int getValue(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets a value at a specific cell.
     *
     * @param row The row index (0-5)
     * @param col The column index (0-5)
     * @param value The value to set (1-6, or 0 for empty)
     */
    @Override
    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    /**
     * Gets the current board state.
     *
     * @return A 2D array representing the board
     */
    @Override
    public int[][] getBoard() {
        return board;
    }

    /**
     * Validates if a move is valid according to Sudoku rules.
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to validate
     * @return true if the move is valid, false otherwise
     */
    @Override
    public boolean isValidMove(int row, int col, int value) {
        // Validate row
        for (int c = 0; c < SIZE; c++) {
            if (c != col && board[row][c] == value) {
                return false;
            }
        }

        // Validate column
        for (int r = 0; r < SIZE; r++) {
            if (r != row && board[r][col] == value) {
                return false;
            }
        }

        // Validate 2x3 block
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if ((r != row || c != col) && board[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Generates initial numbers for the Sudoku puzzle.
     * First creates a complete valid solution using backtracking,
     * then removes cells to create the puzzle.
     */
    @Override
    public void generateInitialNumbers() {
        // Clear the board and solution
        clearBoard();
        initialCells.clear();

        // Step 1: Generate a complete valid Sudoku solution
        generateCompleteSolution(0, 0);

        // Step 2: Copy solution to board
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = solution[i][j];
            }
        }

        // Step 3: Remove cells to create puzzle (keep 12-15 cells)
        Random random = new Random();
        int cellsToRemove = SIZE * SIZE - (12 + random.nextInt(4)); // Keep 12-15 cells

        // Create list of all positions
        ArrayList<int[]> positions = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                positions.add(new int[]{i, j});
            }
        }
        Collections.shuffle(positions);

        // Remove cells
        int removed = 0;
        for (int[] pos : positions) {
            if (removed >= cellsToRemove) break;
            board[pos[0]][pos[1]] = 0;
            removed++;
        }

        // Track initial cells (non-zero cells are initial/non-editable)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    initialCells.add(i + "," + j);
                }
            }
        }
    }

    /**
     * Generates a complete valid Sudoku solution using backtracking.
     *
     * @param row Current row being filled
     * @param col Current column being filled
     * @return true if solution is found, false otherwise
     */
    private boolean generateCompleteSolution(int row, int col) {
        // Move to next row if we've filled the current row
        if (col == SIZE) {
            row++;
            col = 0;
        }

        // Base case: all cells filled successfully
        if (row == SIZE) {
            return true;
        }

        // Create shuffled list of numbers 1-6 for randomness
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        // Try each number
        for (int num : numbers) {
            if (isValidForSolution(row, col, num)) {
                solution[row][col] = num;

                if (generateCompleteSolution(row, col + 1)) {
                    return true;
                }

                solution[row][col] = 0; // Backtrack
            }
        }

        return false;
    }

    /**
     * Validates if a number can be placed in the solution grid.
     *
     * @param row The row index
     * @param col The column index
     * @param value The value to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidForSolution(int row, int col, int value) {
        // Check row
        for (int c = 0; c < SIZE; c++) {
            if (solution[row][c] == value) {
                return false;
            }
        }

        // Check column
        for (int r = 0; r < SIZE; r++) {
            if (solution[r][col] == value) {
                return false;
            }
        }

        // Check 2x3 block
        int startRow = (row / 2) * 2;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 2; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (solution[r][c] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Provides a hint for the player by returning the correct value
     * from the solution for an empty cell.
     *
     * @return An array [row, col, correctValue] or null if no empty cells
     */
    public int[] getHint() {
        // Find first empty cell and return the correct answer from solution
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j, solution[i][j]};
                }
            }
        }
        return null; // No empty cells
    }

    /**
     * Checks if a cell is part of the initial puzzle (non-editable).
     *
     * @param row The row index
     * @param col The column index
     * @return true if the cell is initial, false otherwise
     */
    public boolean isInitialCell(int row, int col) {
        return initialCells.contains(row + "," + col);
    }

    /**
     * Gets all empty cell positions using HashSet for efficient tracking.
     *
     * @return A HashSet containing "row,col" strings of empty cells
     */
    public HashSet<String> getEmptyCells() {
        HashSet<String> emptyCells = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    emptyCells.add(i + "," + j);
                }
            }
        }
        return emptyCells;
    }

    /**
     * Clears the entire board.
     */
    private void clearBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
                solution[i][j] = 0;
            }
        }
    }
}