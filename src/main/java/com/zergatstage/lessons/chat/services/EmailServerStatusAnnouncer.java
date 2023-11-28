package com.zergatstage.lessons.chat.services;

import com.zergatstage.lessons.chat.model.ChatServerLoggingService;

public class EmailServerStatusAnnouncer implements ChatServerLoggingService {
    @Override
    public void logStatus(String message) {
        System.out.println("EmailServerAnnouncer says: " + message);
    }
}
