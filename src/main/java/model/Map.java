package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Map extends JPanel {
    //panel
    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    //game engine
    private static final Random RANDOM = new Random();
    private final static int  HUMAN_DOT = 1;
    private final static int AI_DOT = 2;
    private final static int EMPTY_DOT = 0;
    private final static int DOT_PADDING = 5;
    private int fieldSizeX = 3; //TODO
    private int fieldSizeY = 3;

    private int[][] field;

    private void initMap(){
        fieldSizeX = 3;//TODO
        fieldSizeY = 3;
        field = new int[fieldSizeX][fieldSizeY];
        for(int i = 0; i < fieldSizeX; i++){
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
    }
    private boolean isValidCell(int x, int y){
        return (x >= 0 && x < fieldSizeX) && (y >= 0 && y < fieldSizeY);
    }

    private boolean isEmptyCell(int x, int y){
        return field[x][y] == EMPTY_DOT;
    }

    private void aiTurn(){
        int x,y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x,y));
        field[x][y] = AI_DOT;
    }

    private boolean isMapFull(){
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
    Map(){
        setBackground(Color.DARK_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        }
        );
    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.printf("x=%d, y=%d\n", cellX, cellY);
        if (!isValidCell(cellX,cellY) || !isEmptyCell(cellX,cellY)) return;
        field[cellY][cellY] = HUMAN_DOT;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / 3; //todo set from settings
        cellWidth = panelWidth / 3;

        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            int y = i * cellHeight;
            g.drawLine(0,y,panelWidth,y);
        }
        for (int i = 0; i < 3; i++) {
            int x = i* cellWidth;
            g.drawLine(x,0,x,panelHeight);
        }
        repaint();

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == EMPTY_DOT) continue;
                if (field[x][y] == HUMAN_DOT){
                    g.setColor(Color.BLUE);
                    //g.fillOval();
                } else if (field[x][y] == AI_DOT) {

                } else {
                    throw  new RuntimeException("Some unusual field: " + field[x][y] +
                            "in cell x=" + x + " y= " + y);
                }
            }
        }

    }


    void startNewGame(int mode, int fSzX, int fSzY, int wLen){
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin length: %d\n", mode, fSzX,fSzY,wLen);
        initMap();
    }
}
