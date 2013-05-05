package com.jsc.connectfourai;

import com.badlogic.gdx.graphics.Color;

/**
 * Represents an artificial intelligence player
 */
public class AIPlayer extends Player {	
	/**
	 * @param colour colour of the player's counters
	 */
	public AIPlayer(Color colour) {
		super(colour);
	}

	public int update(float delta, Board board, Player opponent) {
		if (moving && !finishedMoving) {
			Move move = chooseMove(this, opponent, board, -10000, 10000, 
				Config.MAXIMUM_DEPTH);
			board.addCounter(move.getColumn(), this);
			finishedMoving = true;
			moving = false;
		  return move.getColumn();
		}
		return -1;
	}

	/**
	 * Recursive minimax method
	 * @param player player taking their move at this level of the tree
	 * @param opponent player NOT taking their move at this level of the tree
	 * @param board the current board at this level of the tree
	 * @param alpha the best score this AIPlayer object can achieve
	 * @param beta the best score the other player can achieve
	 * @param depth the current depth in the tree, 0 is a leaf node
	 * @return
	 */
	public Move chooseMove(Player player, Player opponent, Board board, 
		int alpha, int beta, int depth) {
		Move best = new Move(-1, player.equals(this) ? alpha : beta);
		// go from left to right until you find a non-full column
		for (int i = 0; i < Config.BOARD_WIDTH; i++) {
			if (board.columnHeight(i) < Config.BOARD_HEIGHT) {
				// add a counter to that column, then check for win-condition
				board.addCounter(i, player);				
				// score this move and all its children
				int score = 0;
				if (board.checkMatch(i, board.columnHeight(i)-1)) {
					// this move is a winning move for the player
					score = player.equals(this) ? 1 : -1;
				} else if (depth != 1) {
					// this move wasn't a win or a draw, so go to the next move
					score = chooseMove(opponent, player, board, alpha, beta,
						depth-1).getScore();
				}
				// -- debug output
				if (Config.DEBUG) {
					debug(player, board, depth, alpha, beta, score);
				}
				board.removeCounter(i);
				// if this move beats this player's best move so far, record it
				if (player.equals(this) && score > best.getScore()) {
					best = new Move(i, score);
					alpha = score;
				} else if (!player.equals(this) && score < best.getScore()) {
					best = new Move(i, score);
					beta = score;
				}
				// don't continue with this branch, we've already found better
				if (alpha >= beta) {
					return best;
				}
			}
		}
		return best;
	}
	
	/**
	 * Debug method for printing minimax data to the console
	 * @param player player taking their move at this level of the tree	 
	 * @param board the current board at this level of the tree
	 * @param depth the current depth in the tree, 0 is a leaf node
	 * @param alpha the best score this AIPlayer object can achieve
	 * @param beta the best score the other player can achieve
	 * @param score the current score
	 */
	private void debug(Player player, Board board, int depth, int alpha, int beta, int score) {
		System.out.println((player.equals(this) ? 
				"COMPUTER" : "HUMAN") 
				+ " (depth: " + depth + ")");
		System.out.println("Score: " + score);
		System.out.println("alpha: " + alpha + ", beta: " + beta);
		System.out.println(board);
		if (score == 0) {
			System.out.println(0);
		}
	}
}
