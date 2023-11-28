package com.zergatstage.lessons.chat.model;

public class ChatServer {
    private boolean isServerWorking;
    private final ChatServerListener listener;

    public ChatServer(ChatServerListener listener) {
        this.listener = listener;
        this.isServerWorking = false;
    }

    public void start(){
        if (isServerWorking){
            listener.onMessageReceived("server is already working.");
            return;
        }
        listener.onMessageReceived("Server started.");
        isServerWorking = true;
    }
    public void stop(){
        if (!isServerWorking){
            listener.onMessageReceived("server is already stopped.");
            return;
        }
        listener.onMessageReceived("Server stopped.");
        isServerWorking = false;
    }
}
