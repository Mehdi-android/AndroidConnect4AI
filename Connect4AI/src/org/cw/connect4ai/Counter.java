package org.cw.connect4ai;

import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class Counter extends DrawableEntity {
    private Point position;
    private int colour;
    private int radius;
    
    public Counter(Point position, int colour, int radius) {
    	super(position);
        this.colour = colour;
        this.radius = radius;
        
        this.drawable = new ShapeDrawable(new OvalShape());
        this.drawable.getPaint().setColor(this.colour);
        this.drawable.setBounds(
    		position.x, position.y, 
    		position.x + this.radius*2, position.y + this.radius*2
        );
    }
    
    public Point getPosition() {
        return position;
    }
    
    public int getColour() {
        return colour;
    }
}