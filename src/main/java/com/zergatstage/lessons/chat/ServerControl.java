package com.zergatstage.lessons.chat;

import com.zergatstage.lessons.chat.model.ChatServer;
import com.zergatstage.lessons.chat.model.ChatServerListener;
import com.zergatstage.lessons.chat.model.ChatServerLoggingService;
import com.zergatstage.lessons.chat.model.Message;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerControl extends JFrame implements ChatServerListener, ChatServerLoggingService {
    private final String SERVER_LABEL = "Is server working: ";
    private  JLabel serverStatusLabel;
    private  JTextArea serverMessages;
    //adding observers for server logging events
    private List<ChatServerLoggingService> observers;
    public static final int WINDOW_HIGH = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 100;
    public static void main(String[] args) {
        new ServerControl();
    }

    public ServerControl() {
        ChatServer server = new ChatServer(this);
        observers = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HIGH);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setResizable(false);
        JPanel paneAbove = new JPanel(new BorderLayout());
        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        serverMessages = new JTextArea();
        serverStatusLabel = new JLabel(SERVER_LABEL);
        JButton startServer = new JButton("Start server");
        startServer.addActionListener(e -> server.start());
        JButton stopServer = new JButton("Stop server");
        stopServer.addActionListener(e -> server.stop());

        paneAbove.add(serverStatusLabel, BorderLayout.NORTH);
        panelBottom.add(startServer, 0);
        panelBottom.add(stopServer, 1);
        paneAbove.add(panelBottom, BorderLayout.SOUTH);
        paneAbove.add(serverMessages,BorderLayout.CENTER);
        add(paneAbove);
        setVisible(true);
    }
    //announce observers about message event
    @Override
    public void logStatus(String message) {
        for (ChatServerLoggingService observer:
             observers) {
            observer.logStatus(message);
        };
    }
    @Override
    public void onMessageReceived(String message) {
        serverMessages.append("\n" + message);
        logStatus(message);
    }
}
