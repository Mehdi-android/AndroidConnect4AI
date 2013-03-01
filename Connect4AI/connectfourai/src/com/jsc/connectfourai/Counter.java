package com.jsc.connectfourai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Counter {
	private static final Texture texture = new Texture(Gdx.files.internal("data/base_counter.png"));
	
    private int x, y;
    private Color colour;
    private Sprite sprite;    
    
    public Counter(int x, int y, Color colour) {
    	this.x = x;
    	this.y = y;
        this.colour = colour;
        sprite = new Sprite(texture, 114, 114);
        sprite.setPosition(x, y);
        sprite.setColor(colour);
    }
    
    public Color getColour() {
        return colour;
    }
    
    public void render(SpriteBatch batch) {
    	sprite.draw(batch);
    }
}