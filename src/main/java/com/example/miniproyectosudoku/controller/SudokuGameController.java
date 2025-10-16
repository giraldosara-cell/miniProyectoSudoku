package com.example.miniproyectosudoku.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import com.example.miniproyectosudoku.model.game.Game;

/**
 * Controller for the Sudoku game view.
 * Handles user interactions and updates the UI accordingly.
 * @version 1.0
 */
public class SudokuGameController {

    private Game game;

    @FXML private TextField a1, a2, a3, a4, a5, a6;
    @FXML private TextField b1, b2, b3, b4, b5, b6;
    @FXML private TextField c1, c2, c3, c4, c5, c6;
    @FXML private TextField d1, d2, d3, d4, d5, d6;
    @FXML private TextField e1, e2, e3, e4, e5, e6;
    @FXML private TextField f1, f2, f3, f4, f5, f6;

    @FXML private Button helpButton;

    private TextField[][] cells;

    /**
     * Initializes the controller and sets up the game board.
     */
    @FXML
    public void initialize() {
        game = new Game();
        game.startNewGame();

        cells = new TextField[][]{
                {a1, a2, a3, a4, a5, a6},
                {b1, b2, b3, b4, b5, b6},
                {c1, c2, c3, c4, c5, c6},
                {d1, d2, d3, d4, d5, d6},
                {e1, e2, e3, e4, e5, e6},
                {f1, f2, f3, f4, f5, f6}
        };

        renderBoard();
    }

    /**
     * Renders the board on the UI based on the current game state.
     */
    private void renderBoard() {
        int[][] current = game.getBoard();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (current[i][j] != 0) {
                    cells[i][j].setText(String.valueOf(current[i][j]));
                    cells[i][j].setEditable(false);
                    cells[i][j].setStyle("-fx-background-color: #D0E6FF; -fx-font-weight: bold;");
                } else {
                    int row = i;
                    int col = j;

                    // Use inner class for key event handling
                    cells[i][j].setOnKeyTyped(new CellKeyTypedHandler(row, col));

                    // Use inner class for text change validation
                    cells[i][j].textProperty().addListener(new CellTextChangeListener(row, col));
                }
            }
        }
    }

    /**
     * Handles the help button click event.
     */
    @FXML
    private void onHelpClicked() {
        int[] hint = game.getHint();
        if (hint == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sin Sugerencias");
            alert.setHeaderText(null);
            alert.setContentText("No hay más sugerencias disponibles.");
            alert.showAndWait();
            return;
        }

        int row = hint[0];
        int col = hint[1];
        int value = hint[2];

        // Display the hint visually
        cells[row][col].setText(String.valueOf(value));
        cells[row][col].setStyle("-fx-background-color: #FFF5BA; -fx-border-color: orange; -fx-border-width: 2px;");
        game.makeMove(row, col, value);

        // Check if game is over after hint
        checkGameOver();
    }

    /**
     * Checks if the game is over and displays a victory message.
     */
    private void checkGameOver() {
        if (game.isGameOver()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Felicitaciones!");
            alert.setHeaderText("Ganaste!");
            alert.setContentText("Has completado satisfactoriamente el Sudoku!");
            alert.showAndWait();
        }
    }


    /**
     * Inner class to handle key typed events in cells.
     * Restricts input to numbers 1-6 only.
     */
    private class CellKeyTypedHandler implements EventHandler<KeyEvent> {
        private final int row;
        private final int col;

        /**
         * Constructor for CellKeyTypedHandler.
         *
         * @param row The row of the cell
         * @param col The column of the cell
         */
        public CellKeyTypedHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Handles the key typed event.
         *
         * @param event The KeyEvent
         */
        @Override
        public void handle(KeyEvent event) {
            String character = event.getCharacter();
            if (!character.matches("[1-6]")) {
                event.consume(); // Block any character that's not 1-6
            }
        }
    }

    /**
     * Inner class to handle text property changes in cells.
     * Validates moves in real-time and provides visual feedback.
     */
    private class CellTextChangeListener implements ChangeListener<String> {
        private final int row;
        private final int col;

        /**
         * Constructor for CellTextChangeListener.
         *
         * @param row The row of the cell
         * @param col The column of the cell
         */
        public CellTextChangeListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Handles text changes in the cell.
         *
         * @param observable The observable value
         * @param oldValue The old text value
         * @param newValue The new text value
         */
        @Override
        public void changed(ObservableValue<? extends String> observable,
                            String oldValue, String newValue) {
            // Limit to single character
            if (newValue.length() > 1) {
                cells[row][col].setText(newValue.substring(0, 1));
                return;
            }

            // Handle empty cell
            if (newValue.isEmpty()) {
                game.makeMove(row, col, 0);
                cells[row][col].setStyle("-fx-background-color: white; -fx-border-color: #C0C0C0;");
                return;
            }

            // Validate number input
            if (newValue.matches("[1-6]")) {
                int val = Integer.parseInt(newValue);

                // Validate Sudoku rules
                if (game.isValidMove(row, col, val)) {
                    game.makeMove(row, col, val);
                    cells[row][col].setStyle("-fx-background-color: #E6FFD0; -fx-border-color: #7ACC4A; -fx-border-width: 2;");

                    // Check if game is complete after valid move
                    checkGameOver();
                } else {
                    // Invalid input → red border
                    cells[row][col].setStyle("-fx-background-color: #FFD0D0; -fx-border-color: red; -fx-border-width: 2;");
                }
            } else {
                cells[row][col].setText("");
            }
        }
    }
}