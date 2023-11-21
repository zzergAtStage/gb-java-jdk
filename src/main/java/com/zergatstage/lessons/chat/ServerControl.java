package com.zergatstage.lessons.chat;

import javax.swing.*;
import java.awt.*;

public class ServerControl extends JFrame {
    private final String SERVER_LABEL = "Is server working: ";
    private JLabel serverStatusLabel;
    private boolean servetStatus;
    public static final int WINDOW_HIGH = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 100;
    public static void main(String[] args) {
        new ServerControl();
    }
    public ServerControl(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH,WINDOW_HIGH);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setResizable(false);
        JPanel paneAbove = new JPanel(new BorderLayout());
        JPanel panelBottom = new JPanel(new GridLayout(1,2));
        serverStatusLabel = new JLabel(SERVER_LABEL);
        JButton startServer = new JButton("Start server");
        servetStatus = false;
        startServer.addActionListener(e ->
        {
            servetStatus = true;
            serverStatusLabel.setText(SERVER_LABEL + servetStatus);
            System.out.printf("Server starts: %b\n", servetStatus);
        });
        JButton stopServer = new JButton("Stop server");
        stopServer.addActionListener(e -> {
            servetStatus = false;
            serverStatusLabel.setText(SERVER_LABEL + servetStatus);
            System.out.printf("Server starts: %b\n", servetStatus);
        });

        paneAbove.add(serverStatusLabel,BorderLayout.NORTH);
        panelBottom.add(startServer,0);
        panelBottom.add(stopServer,1);
        paneAbove.add(panelBottom,BorderLayout.SOUTH);

        add(paneAbove);
        //add(panelBottom);
        setVisible(true);
    }

}
