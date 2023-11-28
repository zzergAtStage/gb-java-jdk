package com.zergatstage.lessons.chat.model;

public class ChatServer {
    private boolean isServerWorking;
    private final ChatServerListener listener;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
    }
}
