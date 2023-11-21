package tictactoe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Map extends JPanel {
    //panel
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;

    //images
    private static Image imageX;
    private static Image image0;


    //game engine
    private boolean isGameOver;
    private boolean isInitialized;
    private final int winSize = 3;//TODO
    private static final Random RANDOM = new Random();
    private final static int HUMAN_DOT = 1;
    private final static int AI_DOT = 2;
    private final static int EMPTY_DOT = 0;
    private int gameOverType;
    private static final int STATE_DRAW = 0;
    public static final int STATE_WIN_HUMAN = 1;
    public static final int STATE_WIN_AI = 2;

    public static final String MSG_WIN_HUMAN = "Win the meatbags!";
    public static final String MSG_WIN_AI = "All of you will die,\n meatbags!";
    private static final String MSG_DRAW = "Go to school, meatbag!";
    private int fieldSizeX = 3; //TODO
    private int fieldSizeY = 3;

    private int[][] field;

    private void initMap(int mode, int fSzX, int fSzY) {
        fieldSizeX = fSzX;//TODO
        fieldSizeY = fSzY;
        field = new int[fieldSizeX][fieldSizeY];
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
        isInitialized = true;
    }

    private boolean isValidCell(int x, int y) {
        return (x >= 0 && x < fieldSizeX) && (y >= 0 && y < fieldSizeY);
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    private void aiTurn() {
        if (turnAIWinCell()) return;
        if (turnHumanWinCell()) return;
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[x][y] = AI_DOT;
        System.out.printf("AI_TURN: x=%d, y=%d\n",x,y);
    }
    private boolean turnAIWinCell(){
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (isEmptyCell(j,i)) {
                    field[i][j] = AI_DOT;
                    if (checkWin(AI_DOT)) return true;
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }
    private boolean turnHumanWinCell(){
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (isEmptyCell(j,i)) {
                    field[i][j] = HUMAN_DOT;
                    if (checkWin(HUMAN_DOT)) return true;
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private boolean checkLine(int x, int y, int vx, int vy, int len, int c){
        final int far_x = x + (len -1) * vx;
        final int far_y = y + (len-1) * vy;
        if (!isValidCell(far_x,far_y)) return false;
        for (int i = 0; i< len; i++){
            if (field[y + i *vy][x + i * vx] != c) return false;
        }
        return true;
    }
    private boolean checkWin(int player) {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                //с помощью вектора проверяем занятые ячейки
                if (checkLine(i,j,1,0, winSize,player)) return true;
                if (checkLine(i,j,1,1, winSize,player)) return true;
                if (checkLine(i,j,0,1, winSize,player)) return true;
                if (checkLine(i,j,1,-1, winSize,player)) return true;
            }
        }
        return false;
    }

    private boolean checkIsGameOver(int player, int gameOverType) {
        if (checkWin(player)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            System.out.println("Declaring draw...");
            isGameOver = true;
            repaint();
            return true;

        }
        return false;
    }

    Map() {
        setBackground(Color.DARK_GRAY);
        isInitialized = false;
        addMouseListener(new MouseAdapter() {
                             @Override
                             public void mouseReleased(MouseEvent e) {
                                 update(e);
                             }
                         }
        );
    }

    private void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;
        if (checkIsGameOver(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.printf("x=%d, y=%d\n", cellX, cellY);
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellX][cellY] = HUMAN_DOT;
        aiTurn();
        repaint();
        if (checkIsGameOver(AI_DOT, STATE_WIN_AI)) return;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fieldSizeY; //todo set from settings
        cellWidth = panelWidth / fieldSizeX;
        try {
            imageX = ImageIO.read(new File("resource/imageX.png"));
            image0 = ImageIO.read(new File("resource/image0.png"));
            imageX = imageX.getScaledInstance(cellHeight, cellWidth, Image.SCALE_DEFAULT);
            image0 = image0.getScaledInstance(cellHeight, cellWidth, Image.SCALE_DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException("The files not found:", e);
        }
        g.setColor(Color.BLACK);
        for (int i = 0; i < fieldSizeX; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
//        for (int i = 0; i < 3; i++) {
//        }

        if (!isInitialized) return;

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == EMPTY_DOT) continue;
                if (field[x][y] == HUMAN_DOT) {
                    g.drawImage(imageX, (panelWidth / fieldSizeX * x), (panelHeight / fieldSizeY * y), null);
                } else if (field[x][y] == AI_DOT) {
                    g.drawImage(image0, (panelWidth / fieldSizeY * x), (panelHeight / fieldSizeY * y), null);
                } else {
                    throw new RuntimeException("Some unusual field: " + field[x][y] +
                            "in cell x=" + x + " y= " + y);
                }
            }
        }
        if (isGameOver) showMessageGameOver(g);
        repaint();
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.orange);
        g.setFont(new Font("Console", Font.BOLD, 48));
        switch (gameOverType) {
            case STATE_DRAW -> g.drawString(MSG_DRAW, 50, getHeight() / 2);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 50, getHeight() / 2);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 50, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected game state: " + gameOverType);
        }
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {

        System.out.printf("\n========================\n" +
                "Mode: %d;\nSize: x=%d, y=%d;\nWin length: %d\n", mode, fSzX, fSzY, wLen);
        initMap(mode,fSzX,fSzY);
        isGameOver = false;

    }

}
