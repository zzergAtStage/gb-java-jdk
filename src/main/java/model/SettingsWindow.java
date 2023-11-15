package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    public static final int WINDOW_HIGH = 230;
    public static final int WINDOW_WIDTH = 350;
    JButton btnStart = new JButton("Start new game");

    SettingsWindow(MainWindow mainWindow) {
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_HIGH, WINDOW_WIDTH);
        //actions
        btnStart.addActionListener(e -> {
            mainWindow.playgroundMap.startNewGame(0,3,3,3);
            setVisible(false);
        });
        add(btnStart);
    }
}
