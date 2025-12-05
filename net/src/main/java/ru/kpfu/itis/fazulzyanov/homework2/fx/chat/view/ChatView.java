package ru.kpfu.itis.fazulzyanov.homework2.fx.chat.view;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;


public class ChatView extends BaseView {

    private AnchorPane root;
    private TextArea conversation;
    private TextArea input;

    @Override
    public Parent getView() {
        if (root == null) {
            createView();
        }
        return root;
    }

    public void append(String message) {
        if (message != null) {
            conversation.appendText(message + System.lineSeparator());
        }
    }

    private void createView() {
        root = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);

        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);
        AnchorPane.setTopAnchor(conversation, 10.0);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);
        AnchorPane.setBottomAnchor(input, 10.0);

        input.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String message = input.getText();
                String username = getChatApplication().userConfig.username();
                getChatApplication().getChatClient().send(username + ": " + message);
                conversation.appendText("you" + ": " + message + System.lineSeparator());
                input.clear();
                event.consume();
            }
        });
        root.getChildren().addAll(conversation, input);
    }
}