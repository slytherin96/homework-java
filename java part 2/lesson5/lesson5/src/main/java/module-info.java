module com.example.lesson5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lesson5 to javafx.fxml;
    exports com.example.lesson5;
}