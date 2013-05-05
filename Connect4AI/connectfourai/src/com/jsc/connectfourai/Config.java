package com.jsc.connectfourai;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class Config {
	public static final boolean DEBUG = false;
	public static final boolean AI_VS_AI = false;
	
	public static final int BOARD_WIDTH = 7;
	public static final int BOARD_HEIGHT = 6;
	public static final int COUNTERS_IN_MATCH = 4;
	
	// maximum depth of the tree the AI traverses in minimax
	public static final int MAXIMUM_DEPTH = 11;
	
	// player colours
	public static final Color PLAYER_ONE_COLOUR = new Color(0, 1, 0, 1);
	public static final Color PLAYER_TWO_COLOUR = new Color(1, 0, 0, 1);
	
	// textures
	public static final Texture BOARD_TEXTURE = new Texture(Gdx.files.internal("data/board.png"));
	public static final Texture COUNTER_TEXTURE = new Texture(Gdx.files.internal("data/base_counter.png"));
	public static final Texture POPUP_WON_TEXTURE = new Texture(Gdx.files.internal("data/win.png"));
	public static final Texture POPUP_LOST_TEXTURE = new Texture(Gdx.files.internal("data/lose.png"));
	public static final Texture POPUP_DRAW_TEXTURE = new Texture(Gdx.files.internal("data/draw.png"));
	
	public static final int BOARD_TEXTURE_WIDTH = 435;
	public static final int BOARD_TEXTURE_HEIGHT = 374;
	
	public static final int POPUP_TEXTURE_WIDTH = 435;
	public static final int POPUP_TEXTURE_HEIGHT = 374;
	
	public static final int COUNTER_TEXTURE_WIDTH = 57;
	public static final int COUNTER_TEXTURE_HEIGHT = 57;
	
	// screen positions
	public static final int POPUP_POSITION_X = 100;
	public static final int POPUP_POSITION_Y = 0;
	
	public static final int BOARD_POSITION_ANDROID_X = 250;
	public static final int BOARD_POSITION_ANDROID_Y = 10;
	
	public static final int BOARD_POSITION_DESKTOP_X = 10;
	public static final int BOARD_POSITION_DESKTOP_Y = 10;
	
	// counter positioning
	public static final int COUNTER_RADIUS = 57;
	public static final int COUNTER_SPACING = 2;
	public static final int BOARD_PADDING = 11;
}