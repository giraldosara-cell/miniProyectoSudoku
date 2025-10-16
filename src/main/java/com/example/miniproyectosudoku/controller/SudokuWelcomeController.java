package com.example.miniproyectosudoku.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for the welcome screen of the Sudoku application.
 * Handles the initialization and transition to the main game view.
 * @version 1.0
 */
public class SudokuWelcomeController {

    /**
     * The button used to start the Sudoku game.
     */
    @FXML
    private Button startButton;

    /**
     * Handles the action when the start button is clicked.
     * Loads the Sudoku game view and closes the welcome window.
     *
     * @param event The ActionEvent triggered by clicking the start button
     */
    @FXML
    private void handleStartButton(ActionEvent event) {
        try {
            // Loads the FXML of the game view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyectosudoku/sudoku-game-view.fxml"));
            Scene gameScene = new Scene(loader.load());

            Stage gameStage = new Stage();
            gameStage.setScene(gameScene);
            gameStage.setTitle("Sudoku Game");
            gameStage.show();

            // Closes the welcome window
            startButton.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}