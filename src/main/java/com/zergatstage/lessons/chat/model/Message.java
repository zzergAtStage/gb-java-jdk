package com.zergatstage.lessons.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String userId;
    private String toUserId;
    private LocalDateTime messageSendTime;
    private String messageBody;
}
