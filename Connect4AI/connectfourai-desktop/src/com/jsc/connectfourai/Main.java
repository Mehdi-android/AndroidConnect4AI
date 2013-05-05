package com.jsc.connectfourai;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "connectfourai";
		cfg.useGL20 = false;
		cfg.width = 455;
		cfg.height = 395;
		//cfg.width = 900;
		//cfg.height = 800;
		new LwjglApplication(new ConnectFourGame(), cfg);
	}
}
