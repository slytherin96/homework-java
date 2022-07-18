module com.example.lesson8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson8 to javafx.fxml;
    exports com.example.lesson8;
    exports com.example.lesson8.models;
    opens com.example.lesson8.models to javafx.fxml;
    exports com.example.lesson8.controllers;
    opens com.example.lesson8.controllers to javafx.fxml;
}