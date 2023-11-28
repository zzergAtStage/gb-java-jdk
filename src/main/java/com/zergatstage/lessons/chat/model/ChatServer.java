package com.zergatstage.lessons.chat.model;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements ServerSocketThreadListener{
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

    @Override
    public void onServerStart() {
        listener.onMessageReceived("Server thread started.");
    }

    @Override
    public void onServerStop() {
        listener.onMessageReceived("Server thread stopped");
    }

    @Override
    public void onServerSocketCreated(ServerSocket socket) {

    }

    @Override
    public void onServerSocketTimeout(ServerSocket socket) {

    }

    @Override
    public void onSocketAccepted(ServerSocket s, Socket client) {

    }

    @Override
    public void onServerException(Throwable exception) {

    }
}
