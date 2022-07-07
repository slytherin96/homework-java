package com.example.lesson6.controllers;

import com.example.lesson6.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatController {
    private String dateFormatString;

    private String message;
    @FXML
    private TextField fieldChat;

    @FXML
    private TextArea messageOutput;

    @FXML
    private ListView <String> usersList;

    @FXML
    private Button sendButton;
    private Network network;

    @FXML
    void initialize() {
        usersList.setItems(FXCollections.observableArrayList("Виталий", "Олег", "Андрей", "Артем"));

        sendButton.setOnAction(event -> sendMessage());
        fieldChat.setOnAction(event -> sendMessage());

    }

    private void sendMessage(){
        message = fieldChat.getText().trim();
        fieldChat.clear();

        if(message.isEmpty()){
            return;
        }
        network.sendMessage(message);
    }
    public void appendMessage(String message) {
        dateFormatString = new SimpleDateFormat("MMM d, HH:mm:ss").format(new Date());
        message = String.format("%s %s", dateFormatString, message);
        message += System.lineSeparator();
        messageOutput.setText(new StringBuilder(messageOutput.getText()).insert(0,message).toString());
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

}
