package com.jsc.connectfourai;

/**
 * Represents a single move in the game
 */
public class Move {
	int column;
	int score;
	
	public Move() {}
	
	/**
	 * @param column column the move is in
	 * @param score the score of the move
	 */
	public Move(int column, int score) {
		this.column = column;
		this.score = score;
	}

	/**
	 * Get the column the move is in
	 * @return the column the move is in
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Set the column the move is in
	 * @param column column the move is in
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Get the score of the move
	 * @return the score of the move
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Set the score of the move
	 * @param score score of the move
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
