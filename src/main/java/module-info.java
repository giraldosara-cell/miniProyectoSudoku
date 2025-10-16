module com.example.miniproyectosudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miniproyectosudoku to javafx.fxml;
    opens com.example.miniproyectosudoku.controller to javafx.fxml;

    exports com.example.miniproyectosudoku;
}