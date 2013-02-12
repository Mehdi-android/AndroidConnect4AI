package org.cw.connect4ai;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Board {
    private Counter[][] counters;
    
    private int width;
    private int height;
    private Color colour;
    private Color backgroundColour; //TODO: I don't like this
    private int xPosition;
    private int yPosition;
    private int counterRadius;
    private int counterSpacing;
    private int padding;
    
    public Board(int width, int height, Color colour, Color backgroundColour, 
            int xPosition, int yPosition, int counterRadius, int counterSpacing, 
            int padding) {
        counters = new Counter[width][height];
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.backgroundColour = backgroundColour;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.counterRadius = counterRadius;
        this.counterSpacing = counterSpacing;
        this.padding = padding;
    }
    
    public boolean addCounter(int column, Color colour) {
        int h = columnHeight(column);
        if (h < height) {
            counters[column][h] = new Counter(
                new Point(
                    xPosition + padding + column*counterRadius + column*counterSpacing, 
                    yPosition + padding + h*counterRadius + h*counterSpacing
                ), 
                colour, counterRadius
            );
            return true;
        }
        return false;
    }
    
    public boolean checkMatch(int column, int row) {
        return false; //TODO
    }
    
    public int columnHeight(int column) {
        int i;
        for (i = 0; i < counters[column].length; i++) {
            if (counters[column][i] == null) {
                break;
            }
        }
        return i;
    }
    
    public void render(Graphics g) {
        // draw the board
        draw(g);
        // render the counters
        for (Counter[] row : counters) {
            for (Counter counter : row) {
                counter.render(g);
            }
        }
    }
    
    public void draw(Graphics g) {
        g.setColor(colour);
        g.drawRect(
            xPosition, 
            yPosition, 
            xPosition + width*counterRadius + (width-1)*counterSpacing + padding*2, 
            yPosition + height*counterRadius + (height-1)*counterSpacing + padding*2
        );
        g.setColor(backgroundColour);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.fillOval(
                    xPosition + padding + i*counterSpacing + i*counterRadius, 
                    yPosition + padding + j*counterSpacing + j*counterRadius, 
                    counterRadius, counterRadius
                );
            }
        }
    }
}