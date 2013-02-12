package org.cw.connect4ai;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
//TODO: import arraylist

public class Game {
    // constants
    private static final int BOARD_WIDTH = 7;
    private static final int BOARD_HEIGHT = 6;
    
    private static final int COUNTER_RADIUS = 40; //pixels
    private static final int SCREEN_X_PADDING = 40;
    private static final int SCREEN_Y_PADDING = 60;
    private static final int BOARD_PADDING = 20;
    private static final int COUNTER_SPACING = 20;
    
    private static final int SCREEN_WIDTH = SCREEN_X_PADDING * 2 + BOARD_PADDING * 2 + COUNTER_RADIUS*2 * BOARD_WIDTH + COUNTER_SPACING * (BOARD_WIDTH - 1);
    private static final int SCREEN_HEIGHT = SCREEN_Y_PADDING * 2 + BOARD_PADDING * 2 + COUNTER_RADIUS*2 * BOARD_HEIGHT + COUNTER_SPACING * (BOARD_HEIGHT - 1);
    
    private static final int PLAYER1_COUNTER_COLOUR = 0xA80000;
    private static final int PLAYER2_COUNTER_COLOUR = 0xECCD35;
    private static final int BACKGROUND_COLOUR = 0xFFFFFF;
    private static final int BOARD_COLOUR = 0x0000FF;
    
    private static final String NAME = "Connect 4";
    private static final int FRAMERATE = 1000/60;
    
    // variables
    private static boolean arrowMoved = false;
    private static boolean addingCounter = false;
    private static int position = 0;
    private static boolean isPlayer1Turn = true;
    private static Image screen;
    
    private static Board board;
    private static Arrow arrow;
    
    private static JFrame frame;

    public static void main(String[] args) {
        System.out.println("Start");

        frame = new JFrame(NAME);
        
        frame.pack();
        frame.getContentPane().setBackground(new Color(BACKGROUND_COLOUR));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            //board = ImageIO.read(new File("res/board.png"));
            //arrow = ImageIO.read(new File("res/arrow.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        run();
    }
    
    public static void run() {
        //screen = createVolatileImage(size.width, size.height);
        board = new Board(
                BOARD_WIDTH, BOARD_HEIGHT, new Color(BOARD_COLOUR), 
                new Color(BACKGROUND_COLOUR), SCREEN_X_PADDING, 
                SCREEN_Y_PADDING, COUNTER_RADIUS, COUNTER_SPACING, 
                BOARD_PADDING);

        while (true) {
            update();
            render();

            try {
                Thread.sleep(FRAMERATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void update() {
        handleKeyPresses();
        // if AI turn, do calculations
    }
    
    private static void handleKeyPresses() {
        //if (isPlayer1Turn && keyDown[S])
    }

    private static void render() {
        Graphics g = screen.getGraphics();
        
        board.draw(g);
        arrow.draw(g);
    }
}