package com.example.lesson6.controllers;

import com.example.lesson6.ChatApplication;
import com.example.lesson6.models.Network;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    private TextField fieldRename;

    @FXML
    private Button sendButton;
    private Network network;
    private String selectedRecipient;
    private ChatApplication startClient;
    private String renameErrorMessage;
    private ArrayList<String> messageHistory;

    private final int COUNT_CHAT_HISTORY = 100;

    @FXML
    void initialize() {
        /*usersList.setItems(FXCollections.observableArrayList("Super_Sonic", "Bender", "Super_Mario", "Гендальф_Серый"
                , "Брюс_Уэйн", "Martin_Superstar"));*/

        sendButton.setOnAction(event -> sendMessage());
        fieldChat.setOnAction(event -> sendMessage());
        messageHistory = new ArrayList<>();
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

    @FXML
    void sendRename(ActionEvent event) {
        String newUsername = fieldRename.getText().trim();
        if (newUsername.length() == 0) {
            System.out.println("Ошибка ввода при смены имени");
            System.out.println("Поля не должны быть пустыми");
            //startClient.showErrorAlert("Ошибка смены имени", "Поля не должны быть пустыми");
            return;
        }
        if (newUsername.length() > 32) {
            startClient.showErrorAlert("Ошибка смены имени", "Длина имени должны быть не более 32 символов");
            return;
        }
        renameErrorMessage = network.sendRenameUser(newUsername);
        if (renameErrorMessage == null) {
            //startClient.openChatDialog();
        } else {
            startClient.showErrorAlert("Ошибка смены имени", renameErrorMessage);
        }

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
        String outText = timeStamp + System.lineSeparator() + String.format("%s: %s", sender, message) + System.lineSeparator() + System.lineSeparator();
        messageOutput.appendText(outText);

        messageHistory.add(outText);
        if (messageHistory.size()>COUNT_CHAT_HISTORY){
            messageHistory.remove(0);
        }
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*messageOutput.appendText(timeStamp);
        messageOutput.appendText(System.lineSeparator());
        messageOutput.appendText(String.format("%s: %s", sender, message));
        messageOutput.appendText(System.lineSeparator());
        messageOutput.appendText(System.lineSeparator());*/


      //  messageOutput.setText(new StringBuilder(messageOutput.getText()).insert(0,message).toString());
    }

    public void appendServerMessage(String message) {
        String outText = String.format("Внимание! %s", message)+System.lineSeparator()+System.lineSeparator();
        messageOutput.appendText(outText);

        messageHistory.add(outText);
        if (messageHistory.size()>COUNT_CHAT_HISTORY){
            messageHistory.remove(0);
        }
        try {
            writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*messageOutput.appendText(String.format("Внимание! %s", message));
        messageOutput.appendText(System.lineSeparator());
        messageOutput.appendText(System.lineSeparator());*/

    }

    private void writeFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(String.format("src/main/resources/lib/history_chat_%s.txt",network.getUsername()), false);

        fileOutputStream.write(String.join("", messageHistory).getBytes(StandardCharsets.UTF_8));

        fileOutputStream.close();
    }

    public void readFile() throws IOException {

        File file = new File(String.format("src/main/resources/lib/history_chat_%s.txt",network.getUsername()));
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 200);

            int i;
            while ((i = bufferedReader.read()) != -1) {
                messageOutput.appendText(String.valueOf((char) i));
            }

            for (String s:messageOutput.getText().split("\n\n")) {
                messageHistory.add(s+System.lineSeparator()+System.lineSeparator());
            }
        }
    }
    public void appendServerOnlineClients() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                usersList.getItems().clear();
                usersList.setItems(FXCollections.observableArrayList(network.getOnlineClient()));
            }
        });
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
