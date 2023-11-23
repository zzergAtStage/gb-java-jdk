package com.zergatstage.lessons.chat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class ServerControl extends JFrame {
    private final String SERVER_LABEL = "Is server working: ";
    private  JLabel serverStatusLabel;
    private  JTextArea serverMessages;
    private boolean servetStatus;
    public static final int WINDOW_HIGH = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 100;

    public static void main(String[] args) {
        new ServerControl();
    }

    public ServerControl() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HIGH);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setResizable(false);
        JPanel paneAbove = new JPanel(new BorderLayout());
        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        serverMessages = new JTextArea();
        serverStatusLabel = new JLabel(SERVER_LABEL);
        JButton startServer = new JButton("Start server");
        servetStatus = false;
        startServer.addActionListener(e ->
        {
            if (isNotServerInCurrentStatus(this, true)) {
                servetStatus = true;
                serverStatusLabel.setText(SERVER_LABEL + servetStatus);
                showServerMessage(serverMessages,String.format("Server starts: %b\n", servetStatus));
            }
        });
        JButton stopServer = new JButton("Stop server");
        stopServer.addActionListener(e -> {
            if (isNotServerInCurrentStatus(this, false)) {
                servetStatus = false;
                serverStatusLabel.setText(SERVER_LABEL + servetStatus);
                showServerMessage(serverMessages,String.format("Server starts: %b\n", servetStatus));
            }
        });

        paneAbove.add(serverStatusLabel, BorderLayout.NORTH);
        panelBottom.add(startServer, 0);
        panelBottom.add(stopServer, 1);
        paneAbove.add(panelBottom, BorderLayout.SOUTH);
        paneAbove.add(serverMessages,BorderLayout.CENTER);
        add(paneAbove);
        //add(panelBottom);
        setVisible(true);
    }

    private boolean isNotServerInCurrentStatus(ServerControl e, boolean newStatus) {
        if (e.servetStatus == newStatus) {
            e.serverMessages.append(String.format("Server is already: %s", e.servetStatus));
            return false;
        }
        return true;
    }
    private void showServerMessage(JTextArea serverMessagesArea, String message){
        serverMessagesArea.append("\n" + message);
    }
}
