package com.jsc.connectfourai;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConnectFourGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Counter counter;
	private Board board;
	private Player player1, player2, currentPlayer;
	
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1, h/w);
		camera.update();
		board = new Board(20, 20, 7, 6, 114, 4, 22);
		player1 = new Player(new Color(0, 1, 0, 1));
		player2 = new Player(new Color(1, 0, 0, 1));
		currentPlayer = player1;
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
	
	public void draw() {		
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	    batch.begin();
	    board.render(batch);
	    batch.end();
	}
	
	public void update(float delta) {
		boolean turnTaken = board.update(delta, currentPlayer);
		if (turnTaken) {
			currentPlayer = currentPlayer.equals(player1) ? player2 : player1;
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
