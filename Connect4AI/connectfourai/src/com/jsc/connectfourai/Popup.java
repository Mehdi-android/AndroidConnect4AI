package com.jsc.connectfourai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;

/**
 * A popup message that appears when the game has ended
 */
public class Popup {
    private Sprite sprite;    
    private boolean touched = false;
    private boolean visible = true;
    
    public static enum GameStatus {WIN, LOSS, DRAW};
    
    /**
     * @param x x-coordinate of the popup on the screen
     * @param y y-coordinate of the popup on the screen
     * @param status whether the game is a win, loss, or draw
     */
    public Popup(int x, int y, GameStatus status) {
    	Texture texture;
    	switch (status) {
    	case WIN:
    		texture = Config.POPUP_WON_TEXTURE;
    		break;
    	case LOSS:
    		texture = Config.POPUP_LOST_TEXTURE;
    		break;
    	default:
    		texture = Config.POPUP_DRAW_TEXTURE;
    		break;
    	}
        sprite = new Sprite(texture, Config.POPUP_TEXTURE_WIDTH, 
        		Config.POPUP_TEXTURE_HEIGHT);
        sprite.setPosition(x, y);
    }

    /**
     * Render the popup to a sprite batch if it is visible
     * @param batch sprite batch to render to
     */
    public void render(SpriteBatch batch) {
    	if (visible) {
    		sprite.draw(batch);
    	}
    }
    
    public boolean update(float delta) {
    	if (!touched && Gdx.input.isTouched()) {
    		touched = true;
    	} else if (touched && !Gdx.input.isTouched()) {
        	touched = false;
        	visible = false;
        	return true;
    	}
    	return false;
    }
}