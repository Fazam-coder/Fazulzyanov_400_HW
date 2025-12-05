package ru.kpfu.itis.fazulzyanov.homework2.fx.chat.view;

import ru.kpfu.itis.fazulzyanov.homework2.fx.chat.ChatApplication;
import javafx.scene.Parent;

public abstract class BaseView {

    private static ChatApplication chatApplication;

    public static ChatApplication getChatApplication() {
        if (chatApplication == null) {
            throw new IllegalStateException("ChatApplication has not been initialized");
        }
        return chatApplication;
    }

    public static void setChatApplication(ChatApplication chatApplication) {
        BaseView.chatApplication = chatApplication;
    }

    public abstract Parent getView();
}
