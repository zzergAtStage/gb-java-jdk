package com.zergatstage.lessons.l01.tictactoe;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HIGH = 250;
    private static final int WINDOW_WIDTH = 350;
    private static final String ACTUAL_FIELD_SIZE_MSG = "Actual field size: ";
    private static final String ACTUAL_WIN_SIZE_MSG = "Current win length: ";
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 10;
    private static final int DEFAULT_VALUE = 3;
    private static final JLabel CHOICE_VICTORY_LENGTH_LBL = new JLabel("Choice victory length");
    private final JRadioButton btnHvsH;
    private final JRadioButton btnHvsC;
    private final JLabel choiceFieldSizeLbl = new JLabel("Choice field size");
    private final JLabel actualFieldSizeLbl;
    public final JSlider jSliderFiledSize;
    private final JSlider jSliderWinLength;
    private final JLabel currentWinLengthLbl;
    JButton btnStart = new JButton("Start new game");
    JPanel settings;

    SettingsWindow(MainWindow mainWindow) {
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_HIGH, WINDOW_WIDTH);
        settings = new JPanel(new GridLayout(10,1));
        settings.add(new JLabel("Settings"));
        ButtonGroup bg = new ButtonGroup();

        btnHvsH = new JRadioButton("Human vs. human");
        btnHvsC = new JRadioButton("Human vs. Computer");
        bg.add(btnHvsH);
        bg.add(btnHvsC);

        settings.add(btnHvsH);
        settings.add(btnHvsC);
        //Labels and sliders. Field size
        settings.add(choiceFieldSizeLbl);
        actualFieldSizeLbl = new JLabel(ACTUAL_FIELD_SIZE_MSG);
        jSliderFiledSize = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_VALUE);
        settings.add(actualFieldSizeLbl);
        jSliderFiledSize.addChangeListener(e -> actualFieldSizeLbl.setText(ACTUAL_FIELD_SIZE_MSG
                + jSliderFiledSize.getValue()));
        settings.add(jSliderFiledSize);
        //Lables and sliders. Win length
        settings.add(CHOICE_VICTORY_LENGTH_LBL);
        currentWinLengthLbl = new JLabel(ACTUAL_WIN_SIZE_MSG);
        settings.add(currentWinLengthLbl);
        jSliderWinLength = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_VALUE);
        jSliderWinLength.addChangeListener(e -> currentWinLengthLbl.setText(ACTUAL_WIN_SIZE_MSG
                + jSliderWinLength.getValue()));
        settings.add(jSliderWinLength);
        //actions

        btnStart.addActionListener(e -> {
            mainWindow.startNewGame(0, jSliderFiledSize.getValue(), jSliderFiledSize.getValue()
                    , jSliderWinLength.getValue());
            SettingsWindow.this.setVisible(false);
            mainWindow.setVisible(true);
        });

        settings.add(btnStart);
        add(settings);
    }
}
