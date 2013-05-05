package com.jsc.connectfourai;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;

/**
 * Represents a coloured counter
 */
public class Counter {
    Color colour;
    private Sprite sprite;    
    
    /**
     * @param x x-coordinate of the counter on the screen
     * @param y y-coordinate of the counter on the screen
     * @param colour colour of the counter
     */
    public Counter(int x, int y, Color colour) {
        this.colour = colour;
        sprite = new Sprite(Config.COUNTER_TEXTURE, 
        		Config.COUNTER_TEXTURE_WIDTH, Config.COUNTER_TEXTURE_HEIGHT);
        sprite.setPosition(x, y);
        sprite.setColor(colour);
    }
    
    /**
     * Renders the counter to a sprite batch
     * @param batch sprite batch to render to
     */
    public void render(SpriteBatch batch) {
    	sprite.draw(batch);
    }
    
    /**
     * Get the colour of the counter
     * @return colour of the counter
     */
    public Color getColour() {
        return colour;
    }
    
    public String toString() {
    	return colour.equals(Config.PLAYER_ONE_COLOUR) ? "X" : "O";
    }
}