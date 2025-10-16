package com.example.miniproyectosudoku;

import com.example.miniproyectosudoku.view.SudokuWelcomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point for the Sudoku application.
 * Launches the JavaFX application and displays the welcome stage.
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application and shows the welcome stage.
     *
     * @param primaryStage The primary stage provided by JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        SudokuWelcomeStage welcomeStage = new SudokuWelcomeStage();
        welcomeStage.show();
    }

    /**
     * The main method. Launches the JavaFX application.
     *
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}