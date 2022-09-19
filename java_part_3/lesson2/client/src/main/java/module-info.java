module com.example.lesson2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson2 to javafx.fxml;
    exports com.example.lesson2;
    exports com.example.lesson2.models;
    opens com.example.lesson2.models to javafx.fxml;
    exports com.example.lesson2.controllers;
    opens com.example.lesson2.controllers to javafx.fxml;
}