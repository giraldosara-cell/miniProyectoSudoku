package com.example.miniproyectosudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the main stage (window) for the Sudoku game.
 * Responsible for initializing and displaying the Sudoku game view.
 * @version 1.0
 */
public class SudokuGameStage {

    /**
     * The JavaFX stage representing the game window.
     */
    private Stage stage;

    /**
     * Constructs a new SudokuGameStage and initializes its user interface.
     */
    public SudokuGameStage() {
        stage = new Stage();
        initUI();
    }

    /**
     * Initializes the user interface by loading the FXML layout and setting up the scene.
     */
    private void initUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyectosudoku/sudoku-game-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setTitle("Sudoku Game");
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Sudoku game stage (window).
     */
    public void show() {
        stage.show();
    }

    /**
     * Closes the Sudoku game stage (window).
     */
    public void close() {
        stage.close();
    }
}
