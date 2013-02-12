package org.cw.connect4ai;

import java.awt.Graphics;

public class Arrow {
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_RIGHT = 1;
    
    private int boardWidth;
    private int position;
    
    public Arrow(int boardWidth) {
        this.boardWidth = boardWidth;
        position = 3;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void move(int direction) {
        position += direction;
        if (position == boardWidth) {
            position = 0;
        } else if (position < 0) {
            position = boardWidth-1;
        }
    }
    
    public void draw(Graphics g) {
        //TODO: drawing
    }
}
