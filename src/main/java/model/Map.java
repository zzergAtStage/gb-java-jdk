package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
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

    private void initMap() {
        fieldSizeX = 3;//TODO
        fieldSizeY = 3;
        field = new int[fieldSizeX][fieldSizeY];
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                field[i][j] = EMPTY_DOT;
            }
        }
        try {
            imageX = ImageIO.read(new File("src/main/java/model/imageX.png"));
            image0 = ImageIO.read(new File("src/main/java/model/image0.png"));
            imageX = imageX.getScaledInstance(cellHeight, cellWidth, Image.SCALE_DEFAULT);
            image0 = image0.getScaledInstance(cellHeight, cellWidth, Image.SCALE_DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException("The files not found:", e);
        }
        isInitialized = true;

    }

    private boolean isValidCell(int[][] board, int x, int y) {
        return (x >= 0 && x < fieldSizeX) && (y >= 0 && y < fieldSizeY);
    }

    private boolean isEmptyCell(int x, int y) {
        return field[x][y] == EMPTY_DOT;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[x][y] = AI_DOT;
        System.out.printf("AI_TURN: x=%d, y=%d\n",x,y);
//        minimax(field, AI_DOT);
    }

    private int evaluate(int player) {
        if (checkWin(HUMAN_DOT, winSize)) {
            return 10;
        } else if (checkWin(AI_DOT, winSize)) {
            return -10;
        } else {
            return 0;
        }

    }

    // The main Minimax algorithm.
    private MoveResult minimax(int[][] board, int player) {
        // Check if the game is over or if it's a leaf node in the game tree.
        // Evaluate the board and return the score.

        // Your code to evaluate the board goes here...

        // If the game is over, return the score.
        if (isGameOver) {
            int score = evaluate(player);
            return new MoveResult(score);
        }

        List<MoveResult> moves = new ArrayList<>();

        // Iterate through all possible moves.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (isValidCell(board, i, j)) {
                    // Make the move.
                    board[i][j] = player;

                    // Recursively call minimax for the opponent.
                    MoveResult result = minimax(board, (player == HUMAN_DOT) ? AI_DOT : HUMAN_DOT);

                    // Undo the move.
                    board[i][j] = 0;

                    // Store the result.
                    result.row = i;
                    result.col = j;
                    moves.add(result);
                }
            }
        }

        // Find the best move for the current player.
        return (player == AI_DOT) ? Collections.max(moves, Comparator.comparing(m -> m.score)) :
                Collections.min(moves, Comparator.comparing(m -> m.score));
    }


    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }

    private boolean checkWin(int player, int winSize) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        int rows = fieldSizeX;
        int cols = fieldSizeY;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == player){
                    for (int[] direction :
                            directions) {
                        int rowInc = direction[0];
                        int colInc = direction[1];
                        int count = 1;

                        int nextRow = i + rowInc;
                        int nextCol = j + colInc;
                        while (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol <cols
                                && field[nextRow][nextCol] == player){
                            count++;
                            nextRow += rowInc;
                            nextCol += rowInc;
                            System.out.printf("Check your algorithms, meatbag!\n" +
                                            "player=%d, count=%d, rowInc=%d, colInc=%d\n"+
                                            "nextRow=%d, nextCol=%d\n",
                                    player,count,rowInc,colInc,nextRow,nextCol);
                            if (count >= winSize) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean checkIsGameOver(int player, int gameOverType) {
        if (checkWin(player, winSize)) {
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
        if (checkIsGameOver(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        System.out.printf("x=%d, y=%d\n", cellX, cellY);
        if (!isValidCell(field, cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
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

        cellHeight = panelHeight / 3; //todo set from settings
        cellWidth = panelWidth / 3;
        panelWidth = getWidth();
        panelHeight = getHeight();
        if (!isInitialized) return;

        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
//        for (int i = 0; i < 3; i++) {
//        }


        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[x][y] == EMPTY_DOT) continue;
                if (field[x][y] == HUMAN_DOT) {
                    g.drawImage(imageX, (panelWidth / 3 * x), (panelHeight / 3 * y), null);
                } else if (field[x][y] == AI_DOT) {
                    g.drawImage(image0, (panelWidth / 3 * x), (panelHeight / 3 * y), null);
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
        initMap();
        isGameOver = false;
    }

}
