package com.jsc.connectfourai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Board {
	private static final Texture texture = new Texture(Gdx.files.internal("data/board.png"));
	
    private Counter[][] counters = new Counter[7][6]; 
    private int[] heights = new int[7];
    private int x, y;
    private int width, height;
    private int counterRadius;
    private int counterSpacing;
    private int padding;
    private boolean touch = false;
    private Sprite sprite;
    
    public Board(int x, int y, int width, int height, int counterRadius, int counterSpacing, int padding) {
        this.x = x;
        this.y = y;
    	this.width = width;
        this.height = height;
        this.counterRadius = counterRadius;
        this.counterSpacing = counterSpacing;
        this.padding = padding;
        sprite = new Sprite(texture, 870, 748);
        sprite.setPosition(x, y);
    }
    
    public boolean update(float delta, Player player) {
    	if (!touch && Gdx.input.isTouched()) {
    		touch = true;    		
    	} else if (touch && !Gdx.input.isTouched()) {
    		int column = (Gdx.input.getX() - (padding + this.x))/(counterRadius+counterSpacing);
    		System.out.println(player);
        	addCounter(column, player.getColour());
    		touch = false;
    		return true;
    	}
    	return false;
    }
    
    public boolean addCounter(int column, Color colour) {        
        if (heights[column] < height) {
            counters[column][heights[column]] = new Counter(
        		x + padding + column*counterRadius + column*counterSpacing, 
        		y + padding + heights[column]*counterRadius + heights[column]*counterSpacing, 
                colour
            );
            heights[column]++;
            return true;
        }
        return false;
    }
    
    public boolean checkMatch(int column, int row) {
        return false; //TODO
    }
    
    public void render(SpriteBatch batch) {
    	for (int i = 0; i < 7; i++) {
    		for (int j = 0; j < heights[i]; j++) {
    			counters[i][j].render(batch);
    		}
    	}
    	sprite.draw(batch);    	
    }

	public int getWidth() {
		return width;
	}
}