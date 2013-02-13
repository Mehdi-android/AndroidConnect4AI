package org.cw.connect4ai;

import android.graphics.Canvas;
import android.graphics.Point;

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
    //private static Image screen;
    private static Canvas canvas;
    
    private static Board board;
    
    private static Player playerOne;
    private static Player playerTwo;
    
    //private static JFrame frame;

    public static void main(String[] args) {
        System.out.println("Start");

        run();
    }
    
    public static void run() {
        //screen = createVolatileImage(size.width, size.height);
        board = new Board(new Point(0,0), BOARD_WIDTH, BOARD_HEIGHT, 
        		BOARD_COLOUR, COUNTER_RADIUS, COUNTER_SPACING, BOARD_PADDING);

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
        //Graphics g = screen.getGraphics();
        
        //board.draw(g);
        //arrow.draw(g);
    }
}