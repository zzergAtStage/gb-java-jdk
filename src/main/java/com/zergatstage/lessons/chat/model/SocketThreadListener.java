package com.zergatstage.lessons.chat.model;

import java.net.Socket;

public interface SocketThreadListener {
    void onSocketStart(Socket s);
    void onSocketStop();

    void onSocketReady(Socket socket);
    void onReceivedString(Socket s, String message);
}
