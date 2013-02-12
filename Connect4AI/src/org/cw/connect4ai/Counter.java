package org.cw.connect4ai;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

public class Counter {
    private Point position;
    private Color colour;
    private int radius;
    
    public Counter(Point position, Color colour, int radius) {
        this.position = position;
        this.colour = colour;
        this.radius = radius;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public Color getColour() {
        return colour;
    }
    
    public void render(Graphics g) {
        draw(g);
    }
    
    public void draw(Graphics g) {
        g.setColor(colour);
        g.fillOval(position.x, position.y, radius, radius);
    }
}