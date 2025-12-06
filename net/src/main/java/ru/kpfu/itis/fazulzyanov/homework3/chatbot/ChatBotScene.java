package ru.kpfu.itis.fazulzyanov.homework3.chatbot;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ChatBotScene {

    private final TextArea chatArea;
    private final TextField inputField;
    private final ChatBotLogic bot;
    private final Scene scene;
    private final Runnable onQuit;

    public ChatBotScene(Runnable onQuit) {
        this.onQuit = onQuit;
        this.bot = new ChatBotLogic();

        chatArea = new TextArea();
        chatArea.setPrefSize(580, 500);
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        inputField = new TextField();
        inputField.setPrefWidth(480);
        inputField.setPromptText("Введите команду: /list, /weather ..., /exchange ..., /quit");

        Button sendBtn = new Button("Отправить");
        sendBtn.setPrefWidth(90);

        sendBtn.setOnAction(e -> handleInput());
        inputField.setOnAction(e -> sendBtn.fire());

        AnchorPane root = new AnchorPane();
        AnchorPane.setTopAnchor(chatArea, 10.0);
        AnchorPane.setLeftAnchor(chatArea, 10.0);

        AnchorPane.setTopAnchor(inputField, 520.0);
        AnchorPane.setLeftAnchor(inputField, 10.0);

        AnchorPane.setTopAnchor(sendBtn, 520.0);
        AnchorPane.setLeftAnchor(sendBtn, 500.0);

        root.getChildren().addAll(chatArea, inputField, sendBtn);
        this.scene = new Scene(root, 600, 600);

        chatArea.appendText("Привет! Напишите /list для справки.\n");
    }

    private void handleInput() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) return;

        chatArea.appendText("Вы: " + input + "\n");
        inputField.clear();

        String response = bot.processCommand(input);

        if ("/quit".equals(response)) {
            onQuit.run();
        } else {
            chatArea.appendText(response + "\n");
        }
    }

    public Scene getScene() {
        return scene;
    }
}
