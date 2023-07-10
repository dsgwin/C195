module com.example.c195 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens MainApplication to javafx.fxml;
    exports MainApplication;
    exports controller;
    opens controller to javafx.fxml;
}