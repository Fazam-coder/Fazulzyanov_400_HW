package ru.kpfu.itis.fazulzyanov.homework3.chatbot;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ChatBotApp extends Application {

    private TextArea chatArea;
    private TextField inputField;
    private ChatBotLogic bot;

    @Override
    public void start(Stage stage) throws Exception {
        bot = new ChatBotLogic();

        TextArea chatArea = new TextArea();
        chatArea.setPrefSize(580, 500);
        chatArea.setLayoutX(10);
        chatArea.setLayoutY(10);
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        TextField inputField = new TextField();
        inputField.setPrefWidth(480);
        inputField.setLayoutX(10);
        inputField.setLayoutY(520);
        inputField.setPromptText("Введите команду: /list, /weather ..., /exchange ..., /quit");

        Button sendBtn = new Button("Отправить");
        sendBtn.setPrefWidth(90);
        sendBtn.setLayoutX(500);
        sendBtn.setLayoutY(520);

        sendBtn.setOnAction(event -> {
            String input = inputField.getText().trim();
            if (input.isEmpty()) return;

            chatArea.appendText("Вы: " + input + "\n");
            inputField.clear();

            String response = bot.processCommand(input);

            if ("/quit".equals(response)) {
                chatArea.clear();
                chatArea.appendText("Чат очищен. Введите команду.\n");
            } else {
                chatArea.appendText(response + "\n");
            }
        });

        inputField.setOnAction(event -> sendBtn.fire());

        Group root = new Group();
        root.getChildren().addAll(chatArea, inputField, sendBtn);

        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("Чат-бот");
        stage.setScene(scene);
        stage.show();

        chatArea.appendText("Привет! Напишите /list для справки.\n");
    }

    private void handleInput() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) return;

        appendMessage("Вы: " + input);
        inputField.clear();

        String response = bot.processCommand(input);

        if ("/quit".equals(response)) {
            chatArea.clear();
            appendMessage("Чат очищен. Введите команду.");
        } else {
            appendMessage(response);
        }
    }

    private void appendMessage(String msg) {
        chatArea.appendText(msg + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
