package com.example.lesson8.controllers;

import com.example.lesson8.ChatApplication;
import com.example.lesson8.models.Network;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.text.DateFormat;
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
    private String selectedRecipient;
    private ChatApplication startClient;

    @FXML
    void initialize() {
        /*usersList.setItems(FXCollections.observableArrayList("Super_Sonic", "Bender", "Super_Mario", "Гендальф_Серый"
                , "Брюс_Уэйн", "Martin_Superstar"));*/

        sendButton.setOnAction(event -> sendMessage());
        fieldChat.setOnAction(event -> sendMessage());

        usersList.setCellFactory(lv -> {
            MultipleSelectionModel<String> selectionModel = usersList.getSelectionModel();
            ListCell<String> cell = new ListCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                usersList.requestFocus();
                if (!cell.isEmpty()) {
                    int index = cell.getIndex();
                    if (selectionModel.getSelectedIndices().contains(index)) {
                        selectionModel.clearSelection(index);
                        selectedRecipient = null;
                    } else {
                        selectionModel.select(index);
                        selectedRecipient = cell.getItem();
                    }
                    event.consume();
                }
            });
            return cell;
        });

    }

    private void sendMessage(){
        message = fieldChat.getText().trim();
        fieldChat.clear();

        if(message.isEmpty()){
            return;
        }
        if (selectedRecipient != null) {
            network.sendPrivateMessage(selectedRecipient, message);
        } else {
            network.sendMessage(message);
        }

    }
    public void appendMessage(String sender, String message) {
        String timeStamp = DateFormat.getInstance().format(new Date());

        messageOutput.appendText(timeStamp);
        messageOutput.appendText(System.lineSeparator());
        messageOutput.appendText(String.format("%s: %s", sender, message));
        messageOutput.appendText(System.lineSeparator());
        messageOutput.appendText(System.lineSeparator());

      //  messageOutput.setText(new StringBuilder(messageOutput.getText()).insert(0,message).toString());
    }

    public void appendServerMessage(String message) {

        messageOutput.appendText(String.format("Внимание! %s", message));
        messageOutput.appendText(System.lineSeparator());
        messageOutput.appendText(System.lineSeparator());

    }

    public void appendServerOnlineClients() {

        usersList.getItems().clear();
        usersList.setItems(FXCollections.observableArrayList(network.getOnlineClient()));
        //  messageOutput.setText(new StringBuilder(messageOutput.getText()).insert(0,message).toString());

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setStartClient(ChatApplication startClient) {
        this.startClient = startClient;
    }
    public ChatApplication getStartClient() {
        return startClient;
    }
}
