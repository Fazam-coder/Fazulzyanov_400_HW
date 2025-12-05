package ru.kpfu.itis.fazulzyanov.homework2.fx.chat.view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.model.UserConfig;

public class UserConfigView extends BaseView {

    private AnchorPane root;
    private VBox box;
    private TextField username;
    private TextField host;
    private TextField port;
    private Button start;


    @Override
    public Parent getView() {
        if (root == null) {
            createView();
        }
        return root;
    }

    public void createView() {
        root = new AnchorPane();
        box = new VBox();

        Label usernameLabel = new Label("Username:");
        username = new TextField();
        Label hostLabel = new Label("Host:");
        host = new TextField();
        host.setPromptText("127.0.0.1");
        Label portLabel = new Label("Port:");
        port = new TextField();
        port.setPromptText("5555");
        start = new Button("Start");

        start.setOnAction(event -> {
            if (start == event.getSource()) {
                UserConfig userConfig = new UserConfig(
                        username.getText(), host.getText(), Integer.parseInt(port.getText())
                );

                getChatApplication().setUserConfig(userConfig);
                getChatApplication().startChat();
                getChatApplication().setView(getChatApplication().getChatView());
            }
        });

        box.getChildren().addAll(
                usernameLabel, username, hostLabel,
                host, portLabel, port, start
        );
        root.getChildren().addAll(box);
    }

}
