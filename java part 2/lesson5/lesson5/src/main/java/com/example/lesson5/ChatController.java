package com.example.lesson5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatController {
    private String text;
    private String dateFormatString;
    @FXML
    private TextField fieldChat;

    @FXML
    private TextArea messageOutput;

    @FXML
    void doSend(ActionEvent event) {

        text = fieldChat.getText();
        dateFormatString = new SimpleDateFormat("MMM d, HH:mm:ss").format(new Date());
        if (text.length()!=0) {
            messageOutput.appendText(String.format("%s %s %n", dateFormatString, text));
            fieldChat.setText("");
        }
    }

    @FXML
    void initialize() {

    }
}
