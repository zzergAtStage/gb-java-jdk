package model;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    //properties
    public static final int WINDOW_HIGH = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 100;

    Map playgroundMap;

    SettingsWindow settings;

    JButton buttonStart = new JButton("Start game");

    JButton buttonEndGame = new JButton("End game");
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


        buttonStart.addActionListener(e -> playgroundMap.startNewGame(0,settings.jSliderFiledSize.getValue()
                ,settings.jSliderFiledSize.getValue(),3));

        buttonEndGame.addActionListener(e -> System.exit(0));
        setVisible(true);
        settings = new SettingsWindow(this);
        settings.setVisible(true);
    }
    public void startNewGame(int mode, int fSzX, int fSzY, int wLen){
        playgroundMap.startNewGame(mode, fSzX, fSzY, wLen);
    }
}
