package com.example.lesson3.models;

import com.example.lesson3.ChatApplication;
import com.example.lesson3.controllers.ChatController;
import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Network {
    private static final String AUTH_CMD_PREFIX = "/auth"; // + login + password
    private static final String AUTHOK_CMD_PREFIX = "/authok"; // + username
    private static final String AUTHERR_CMD_PREFIX = "/autherr"; // + error message
    private static final String CLIENT_MSG_CMD_PREFIX = "/cMsg"; // + msg
    private static final String SERVER_MSG_CMD_PREFIX = "/sMsg"; // + msg
    private static final String PRIVATE_MSG_CMD_PREFIX = "/pm"; // + username + msg
    private static final String STOP_SERVER_CMD_PREFIX = "/stop";
    private static final String END_CLIENT_CMD_PREFIX = "/end";
    private static final String SERVER_MSG_CLIENTS_ONLINE = "/online";
    private static final String SERVER_MSG_REGISTRATION_USER = "/reg";
    private static final String SERVER_MSG_RENAME_USER = "/rename";
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8186;

    private final String host;
    private final int port;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private ChatApplication startClient;

    public String[] getOnlineClient() {
        return onlineClient;
    }

    private String[] onlineClient;

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Network() {
        this(DEFAULT_HOST,DEFAULT_PORT);
    }

    public void connect(){
        try {
            Socket socket = new Socket(host, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            startClient.showErrorAlert("Ошибка подключения","Соединение не установлено");
        }
    }

    public void sendMessage(String message){
        try {
            out.writeUTF(String.format("%s %s",CLIENT_MSG_CMD_PREFIX, message));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
            startClient.showErrorAlert("Ошибка подключения","Ошибка при отправке сообщения");
        }
    }
    public void sendPrivateMessage(String recipient, String message){
        try {
            out.writeUTF(String.format("%s %s %s",PRIVATE_MSG_CMD_PREFIX, recipient, message));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
        }
    }

    public String sendAuthMessage(String login, String password) {
        try {
            out.writeUTF(String.format("%s %s %s", AUTH_CMD_PREFIX, login, password));
            String response = in.readUTF();
            if (response.startsWith(AUTHOK_CMD_PREFIX)) {
                this.username = response.split("\\s+", 2)[1];
                return null;
            } else {
                return response.split("\\s+", 2)[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
            return e.getMessage();
        }
    }

    public String sendRegMessage(String login, String password, String username) {
        try {
            out.writeUTF(String.format("%s %s %s %s", SERVER_MSG_REGISTRATION_USER, login, password, username));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
            return e.getMessage();
        }
        return null;
    }

    public String sendRenameUser(String newUsername){
        try {
            out.writeUTF(String.format("%s %s", SERVER_MSG_RENAME_USER, newUsername));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
            return e.getMessage();
        }
        return null;

    }


    public void waitMessage(ChatController chatController) {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    String message = in.readUTF();

                    String typeMessage = message.split("\\s+")[0];
                    if (!typeMessage.startsWith("/")) {
                        System.out.println(message);
                        System.out.println("Неверный запрос");
                    }

                    switch (typeMessage) {
                        case CLIENT_MSG_CMD_PREFIX -> {
                            String[] parts = message.split("\\s+", 3);
                            String sender = parts[1];
                            String messageFromSender = parts[2];

                            if (sender.equals(username)) {
                                sender = "Я";
                            }

                            String finalSender = sender;
                            Platform.runLater(() -> chatController.appendMessage(finalSender, messageFromSender));
                        }
                        case PRIVATE_MSG_CMD_PREFIX -> {
                            String[] parts = message.split("\\s+", 3);
                            String sender = parts[1];
                            String messageFromSender = parts[2];

                            Platform.runLater(() -> chatController.appendMessage("[pm]" + sender, messageFromSender));
                        }
                        case SERVER_MSG_CMD_PREFIX -> {
                            String[] parts = message.split("\\s+", 2);
                            String serverMessage = parts[1];

                            chatController.appendServerMessage(serverMessage);
                        }
                        case SERVER_MSG_CLIENTS_ONLINE -> {
                            String[] parts = message.split("\\s+", 2);
                            onlineClient = parts[1].split(";");
                            chatController.appendServerOnlineClients();
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        t.setDaemon(true);
        t.start();
    }

    public String getUsername() {
        return username;
    }


    public void setStartClient(ChatApplication startClient) {
        this.startClient = startClient;

    }
}
