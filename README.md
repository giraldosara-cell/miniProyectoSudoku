# Mini Sudoku Project

## Overview
This project is a 6x6 Sudoku game developed in Java using JavaFX and FXML for the graphical user interface. The application demonstrates principles of user experience (UX), modular event handling, proper data structures, and follows the Model-View-Controller (MVC) architecture.

## Features
- Clear and user-friendly graphical interface (GUI) built with JavaFX and Scene Builder.
- MVC architecture: separation of Model, View, and Controller for maintainability.
- Modular event handling using internal classes and interfaces.
- Two-dimensional data structures for Sudoku logic.
- Game logic with validation, hints, and game-over detection.
- All code and documentation written completely in English, following Java style conventions.
- Javadoc documentation provided and exported to HTML.
- Version control managed with Git and GitHub.

## How to Run
1. Clone this repository:
    bash
    git clone https://github.com/<your-username>/<your-repo>.git
    
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Make sure you have Java 11 or later and JavaFX libraries installed.
4. Run the Main class to start the application.

## Project Structure
- controller/ - JavaFX controllers for GUI event management.
- model/board/ - Board data structures and logic.
- model/game/ - Game logic, rules, and validation.
- view/ - JavaFX stages and FXML views.
- Main.java - Entry point for the application.

## Documentation
The source code is fully documented with Javadoc in English. To generate the documentation:
bash
javadoc -d doc src/**/*.java

The generated HTML documentation can be found in the doc/ directory.

## Version Control
- All commits are tracked and tagged for versioning.
- Pull requests and issues are managed via GitHub.
- This repository is public and includes this README for guidance.

## Author
Your Name

## License
This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
