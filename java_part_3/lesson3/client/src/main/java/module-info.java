module com.example.lesson3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson3 to javafx.fxml;
    exports com.example.lesson3;
    exports com.example.lesson3.models;
    opens com.example.lesson3.models to javafx.fxml;
    exports com.example.lesson3.controllers;
    opens com.example.lesson3.controllers to javafx.fxml;
}