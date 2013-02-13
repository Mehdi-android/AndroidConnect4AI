package org.cw.connect4ai;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class Board extends DrawableEntity {
    private Counter[][] counters;    
    private int width;
    private int height;
    private int colour;
    private int counterRadius;
    private int counterSpacing;
    private int padding;
    
    public Board(Point position, int width, int height, int colour, 
            int counterRadius, int counterSpacing, 
            int padding) {
        super(position);
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.counterRadius = counterRadius;
        this.counterSpacing = counterSpacing;
        this.padding = padding;
        
        this.drawable = new ShapeDrawable(new RectShape());
        this.drawable.getPaint().setColor(this.colour);
        this.drawable.setBounds(
        	position.x, 
        	position.y, 
        	position.x + width*counterRadius + (width-1)*counterSpacing + padding*2, 
            position.y + height*counterRadius + (height-1)*counterSpacing + padding*2
        );
    }
    
    public boolean addCounter(int column, int colour) {
        int h = columnHeight(column);
        if (h < height) {
            counters[column][h] = new Counter(
                new Point(
                    position.x + padding + column*counterRadius + column*counterSpacing, 
                    position.y + padding + h*counterRadius + h*counterSpacing
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
    
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (Counter[] row : counters) {
        	for (Counter counter : row) {
        		counter.draw(canvas);
        	}
        }
    }
}