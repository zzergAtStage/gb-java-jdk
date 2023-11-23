package com.zergatstage.lessons.chat.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class Chat {
    private ArrayList<User> activeUsers;
    //as the key, I will keep userId - to realize feature - U2U conversation
    private Map<String, Message> messages;
    private User currentUser;

    public Chat(){
        this.messages = new HashMap<>();
        this.activeUsers = new ArrayList<>();
    }

    public void addMessage(Message message){
        messages.put(message.getUserId(),message);
    }
    public List<Message> getAllMessages(){
        return new ArrayList<>(messages.values());
    }

    public List<Message> getMessagesByUser(String userId){
        return messages.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(userId))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void addUserToChat(User user){
        this.activeUsers.add(user);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void removeUserFromChat(User user){
        for (User u :
                activeUsers) {
            if (user.equals(u)) activeUsers.remove(user);
        }
    }
}
