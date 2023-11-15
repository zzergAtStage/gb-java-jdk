package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    //properties
    public static final int WINDOW_HIGH = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 300;

    Map playgroundMap;

    SettingsWindow settings;

    JButton buttonStart = new JButton("Start game");

    JButton buttonEndGame = new JButton("End the game");
    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH,WINDOW_HIGH);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setTitle("KrestikinOliki");
        setResizable(false);

        playgroundMap = new Map();
        JPanel paneBottom = new JPanel(new GridLayout(1,2));
        paneBottom.add(buttonStart);
        paneBottom.add(buttonEndGame);
        add(paneBottom, BorderLayout.SOUTH);
        add(playgroundMap);


        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playgroundMap.startNewGame(0,3,3,3);
            }
        });

        buttonEndGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
        settings = new SettingsWindow(this);
        settings.setVisible(true);
    }
}
