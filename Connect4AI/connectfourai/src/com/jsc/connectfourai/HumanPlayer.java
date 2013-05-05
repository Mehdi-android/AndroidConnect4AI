package com.jsc.connectfourai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

/**
 * Represents a human player
 */
public class HumanPlayer extends Player {
	protected Boolean touched = false;

	/**
	 * @param colour colour of the player's counters
	 */
	public HumanPlayer(Color colour) {
		super(colour);
	}

	public int update(float delta, Board board, Player opponent) {
		if (moving && !finishedMoving) {
			if (!touched && Gdx.input.isTouched()) {
	    		touched = true;
	    	} else if (touched && !Gdx.input.isTouched()) {
	        	if (board.addCounter(board.getColumnAtX(Gdx.input.getX()), this)) {
		    		touched = false;
		    		finishedMoving = true;
		    		moving = false;
		    		return board.getColumnAtX(Gdx.input.getX());
	        	}	        	
	    	}
	    }
		return -1;
	}
}