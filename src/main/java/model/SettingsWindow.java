package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    public static final int WINDOW_HIGH = 150;
    public static final int WINDOW_WIDTH = 150;
    JButton btnStart = new JButton("Start new game");
    JPanel settings;
    SettingsWindow(MainWindow mainWindow) {
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_HIGH, WINDOW_WIDTH);
        settings = new JPanel(new GridLayout(2,1));
        settings.add(new JLabel("Settings"));
        ButtonGroup bg = new ButtonGroup();

        JRadioButton btnHvsH = new JRadioButton("Human vs. human");
        JRadioButton btnHvsC = new JRadioButton("Human vs. Computer");
        bg.add(btnHvsH);
        bg.add(btnHvsC);

        settings.add(btnHvsH);
        settings.add(btnHvsC);
        //actions
        btnStart.addActionListener(e -> {
            mainWindow.playgroundMap.startNewGame(0,3,3,3);
            setVisible(false);
        });

        settings.add(btnStart);
        add(settings);
    }
}
