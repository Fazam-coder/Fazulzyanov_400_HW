package ru.kpfu.itis.fazulzyanov.homework2.fx.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.client.ChatClient;
import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.model.UserConfig;
import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.view.BaseView;
import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.view.ChatView;
import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.view.UserConfigView;

public class ChatApplication extends Application {
    public UserConfig userConfig;
    private ChatClient chatClient;
    private ChatView chatView;
    private UserConfigView userConfigView;
    private BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chat");
        stage.setOnCloseRequest(event -> System.exit(0));
        BaseView.setChatApplication(this);

        userConfigView = new UserConfigView();
        chatView = new ChatView();
        chatClient = new ChatClient(this);
        root = new BorderPane();

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();

        setView(userConfigView);
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public void startChat() {
        chatClient.start();
    }

    public ChatView getChatView() {
        return chatView;
    }

    public UserConfigView getUserConfigView() {
        return userConfigView;
    }

    public void setView(BaseView view) {
        root.setCenter(view.getView());
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void appendMessage(String message) {
        chatView.append(message);
    }
}