module com.example.lesson6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson6 to javafx.fxml;
    exports com.example.lesson6;
    exports com.example.lesson6.models;
    opens com.example.lesson6.models to javafx.fxml;
    exports com.example.lesson6.controllers;
    opens com.example.lesson6.controllers to javafx.fxml;
}