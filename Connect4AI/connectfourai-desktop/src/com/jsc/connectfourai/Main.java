package com.jsc.connectfourai;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "connectfourai";
		cfg.useGL20 = false;
		cfg.width = 870 + 20*2;
		cfg.height = 748 + 20*2;
		
		new LwjglApplication(new ConnectFourGame(), cfg);
	}
}
