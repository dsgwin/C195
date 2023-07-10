module com.example.c195 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.C195 to javafx.fxml;
    exports com.example.C195;
    exports controller;
    opens controller to javafx.fxml;
}