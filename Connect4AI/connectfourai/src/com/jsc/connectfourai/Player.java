package com.jsc.connectfourai;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents an abstract "player"
 */
public abstract class Player {
	private static int idCounter = 0;
	private int id = Player.idCounter++;
	protected Color colour;
	protected Boolean moving = false; // is it currently this player's move?
	protected Boolean finishedMoving = false;
	
	/**
	 * @param colour colour of the player's counters
	 */
	public Player(Color colour) {
		this.colour = colour;
	}
	
	/**
	 * Get the colour of the player's counters
	 * @return the colour of the player's counters
	 */
	public Color getColour() {
		return colour;
	}

	public boolean equals (Object obj) {
		if (obj instanceof Player) {
			if (this.id == ((Player)obj).id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tell the player is is their turn to make a move
	 */
	public void makeMove() {
		moving = true;
		finishedMoving = false;
	}

	/**
	 * Check if it is this player's move
	 * @return true it is this player's move, false otherwise
	 */
	public Boolean isMoving() {
		return moving;
	}

	/**
	 * Check if this player has finished making their move
	 * @return true if the player has finished, false otherwise
	 */
	public Boolean isFinishedMoving() {
		return finishedMoving;
	}
	
	public abstract int update(float delta, Board board, Player opponent);
}