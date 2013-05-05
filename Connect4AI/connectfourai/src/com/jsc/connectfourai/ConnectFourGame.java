package com.jsc.connectfourai;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main game class
 */
public class ConnectFourGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Board board;
	private Player playerOne, playerTwo, currentPlayer;
	private Player winner;
	private boolean gameEnded = false;
	private Popup popup;
	
	@Override
	public void create() {	
		// LibGDX screen setup
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1, h/w);
		camera.update();	
		
		// game setup
		if (Config.AI_VS_AI) {
			playerOne = new AIPlayer(Config.PLAYER_ONE_COLOUR);
		} else {
			playerOne = new HumanPlayer(Config.PLAYER_ONE_COLOUR);
		}		
		playerTwo = new AIPlayer(Config.PLAYER_TWO_COLOUR);
		newGame();		
	}
	
	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render() {		
		update(Gdx.graphics.getDeltaTime());
	    draw();
	}
	
	/**
	 * Main update method for the game, calls the other objects' update methods
	 * @param delta the time that has passed since the last frame in seconds
	 */
	private void update(float delta) {
		if (!gameEnded) {
			continueGame(delta);
		} else {
			endGame(delta);
		}
	}
	
	/**
	 * Continue with the normal game processing
	 * @param delta the time that has passed since the last frame in seconds
	 */
	private void continueGame(float delta) {
		if (currentPlayer.isFinishedMoving()) {
			nextTurn();
		}
		int lastMove = currentPlayer.update(delta, board, 
				currentPlayer.equals(playerOne) ? playerTwo : playerOne);			
		if (lastMove != -1 && board.checkMatch(lastMove, 
				board.columnHeight(lastMove)-1)) {
			winner = currentPlayer;
			gameEnded = true;
		} else if (board.isFull()) {
			gameEnded = true;
		}
	}
	
	/**
	 * Do the endgame processing, such as showing a popup
	 * @param delta the time that has passed since the last frame in seconds
	 */
	private void endGame(float delta) {
		if (popup == null) {
			// popup isn't shown, so show it
			Popup.GameStatus status;
			if (winner == null) {
				status = Popup.GameStatus.DRAW;
			} else {
				status = winner.equals(playerOne) ? Popup.GameStatus.WIN 
						: Popup.GameStatus.LOSS;
			}
			popup = new Popup(Config.POPUP_POSITION_X, 
					Config.POPUP_POSITION_Y, status);
		} else {
			// update the popup and start a new game on input
			if (popup.update(delta)) {
				newGame();
			}
		}
	}
	
	/**
	 * Main render method for the game, calls the other objects' render methods
	 */
	private void draw() {		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    batch.begin();
	    board.render(batch);
	    if (popup != null) {
			popup.render(batch);
		}
	    batch.end();
	}
	
	/**
	 * Move to the next player's turn
	 */
	private void nextTurn() {		
		currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
		currentPlayer.makeMove();
	}
	
	/**
	 * Begin a new game
	 */
	private void newGame() {		
		switch(Gdx.app.getType()) {
			case Android:
				board = new Board(Config.BOARD_POSITION_ANDROID_X, 
						Config.BOARD_POSITION_ANDROID_Y);
				break;
			case Desktop:
				board = new Board(Config.BOARD_POSITION_DESKTOP_X, 
						Config.BOARD_POSITION_DESKTOP_Y);
				break;
			default:
				break;
		}
		winner = null;
		popup = null;
		gameEnded = false;
		currentPlayer = playerOne;
		currentPlayer.makeMove();
	}

	@Override
	public void resize(int width, int height) { }
	@Override
	public void pause() { }
	@Override
	public void resume() { }
}
