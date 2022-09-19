package com.example.lesson6.controllers;

import com.example.lesson6.ChatApplication;
import com.example.lesson6.models.Network;
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
    private TextField usernameReg;

    @FXML
    private PasswordField passwordField;

    private Network network;
    private ChatApplication startClient;

    private String authErrorMessage;
    private String regErrorMessage;

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
        authErrorMessage = network.sendAuthMessage(login, password);
        if (authErrorMessage == null) {
            startClient.openChatDialog();
        } else {
            startClient.showErrorAlert("Ошибка аутентификации", authErrorMessage);
        }
    }
    @FXML
    void signUp(ActionEvent event) {
        String login = loginReg.getText().trim();
        String password = passReg.getText().trim();
        String username = usernameReg.getText().trim();
        if (login.length() == 0 || password.length() == 0 || username.length() == 0) {
            System.out.println("Ошибка ввода при регистрации");
            System.out.println("Поля не должны быть пустыми");
            startClient.showErrorAlert("Ошибка ввода при регистрации", "Поля не должны быть пустыми");
            return;
        }
        if (login.length() > 32 || password.length() > 32 || username.length() > 32) {
            startClient.showErrorAlert("Ошибка ввода при регистрации", "Длина логина и пароля должны быть не более 32 символов");
            return;
        }
        regErrorMessage = network.sendRegMessage(login, password, username);
        if (regErrorMessage == null) {
            startClient.showInfoAlert("Регистрация", "Регистрация прошла успешно");
        } else {
            startClient.showErrorAlert("Ошибка регистрации", regErrorMessage);
        }

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
