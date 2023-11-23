package com.zergatstage.lessons.chat.model;

public interface ChatTransport {
    void sendMessage(String message);
    Chat readChatHistory();
}
