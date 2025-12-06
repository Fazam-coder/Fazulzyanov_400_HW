package ru.kpfu.itis.fazulzyanov.homework3.chatbot;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatBotApp extends Application {

    private Stage primaryStage;
    private ChatBotScene chatBotScene;
    private Scene startScene;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Чат-бот");

        this.chatBotScene = new ChatBotScene(this::showStartScene);

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> showChatScene());
        startButton.setMinSize(100, 40);

        VBox vbox = new VBox(startButton);
        vbox.setAlignment(Pos.CENTER);
        startScene = new Scene(vbox, 300, 200);

        stage.setScene(startScene);
        stage.show();
    }

    private void showChatScene() {
        primaryStage.setScene(chatBotScene.getScene());
    }

    public void showStartScene() {
        primaryStage.setScene(startScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
