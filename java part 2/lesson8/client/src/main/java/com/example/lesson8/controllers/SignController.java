package com.example.lesson8.controllers;

import com.example.lesson8.ChatApplication;
import com.example.lesson8.models.Network;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField loginReg;

    @FXML
    private TextField passReg;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameReg;
    private Network network;
    private ChatApplication startClient;

    @FXML
    void checkAuth(ActionEvent event) {
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        if (login.length() == 0 || password.length() == 0) {
            System.out.println("Ошибка ввода при аутентификации");
            System.out.println("Поля не должны быть пустыми");
            startClient.showErrorAlert("Ошибка ввода при аутентификации", "Поля не должны быть пустыми");
            return;
        }
        if (login.length() > 32 || password.length() > 32) {
            startClient.showErrorAlert("Ошибка ввода при аутентификации", "Длина логина и пароля должны быть не более 32 символов");
            return;
        }
        String authErrorMessage = network.sendAuthMessage(login, password);
        if (authErrorMessage == null) {
            startClient.openChatDialog();
        } else {
            startClient.showErrorAlert("Ошибка аутентификации", authErrorMessage);
        }
    }
    @FXML
    void signUp(ActionEvent event) {

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
