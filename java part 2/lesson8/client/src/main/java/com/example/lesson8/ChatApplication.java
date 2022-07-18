package com.example.lesson8;

import com.example.lesson8.controllers.ChatController;
import com.example.lesson8.controllers.SignController;
import com.example.lesson8.models.Network;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ChatApplication extends Application {

    private Network network;
    private Stage primaryStage;
    private Stage authStage;
    private ChatController chatController;
    private SignController signController;
    private PauseTransition delay;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        network = new Network();
        network.setStartClient(this);
        network.connect();

        openAuthDialog();
        createChatDialog();

    }

    private void openAuthDialog() throws IOException {
        FXMLLoader authLoader = new FXMLLoader(ChatApplication.class.getResource("auth-view.fxml"));
        authStage = new Stage();
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.initOwner(primaryStage);
        Scene scene = new Scene(authLoader.load(), 600, 400);
        authStage.setTitle("Authentication!");
        authStage.setScene(scene);
        authStage.show();

        delay = new PauseTransition(Duration.seconds(120));
        delay.setOnFinished(event -> {
            authStage.close();
            showInfoAlert("Истекло время ожидания", "Истекло время ожидания");
        });
        delay.play();

        signController = authLoader.getController();
        signController.setNetwork(network);
        signController.setStartClient(this);
    }

    private void createChatDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        primaryStage.setScene(scene);
        //primaryStage.setY(1400);
        //primaryStage.setX(650);
        primaryStage.setAlwaysOnTop(true);
        //primaryStage.show();


        chatController = fxmlLoader.getController();
        chatController.setNetwork(network);
        chatController.setStartClient(this);

    }


    public void openChatDialog() {
        delay.stop();
        authStage.close();
        primaryStage.show();
        primaryStage.setTitle(network.getUsername());

        network.waitMessage(chatController);
    }

    public void showErrorAlert(String title, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(errorMessage);
        alert.show();
    }

    public void showInfoAlert(String title, String infoMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(infoMessage);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}