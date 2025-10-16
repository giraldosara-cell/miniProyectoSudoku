package com.example.miniproyectosudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Represents the welcome stage (window) for the Sudoku application.
 * Responsible for initializing and displaying the welcome view.
 * @version 1.0
 */
public class SudokuWelcomeStage {

    /**
     * The JavaFX stage representing the welcome window.
     */
    private Stage stage;

    /**
     * Constructs a new SudokuWelcomeStage and initializes its user interface.
     */
    public SudokuWelcomeStage() {
        stage = new Stage();
        initUI();
    }

    /**
     * Initializes the user interface by loading the FXML layout and setting up the scene.
     */
    private void initUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyectosudoku/sudoku-welcome-view.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Sudoku");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the welcome stage (window).
     */
    public void show() {
        stage.show();
    }

    /**
     * Closes the welcome stage (window).
     */
    public void close() {
        stage.close();
    }
}