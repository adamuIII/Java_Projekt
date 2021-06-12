package com.rzepka.tokar.dyk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rzepka.tokar.dyk.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 900;
		config.width = 1800;
//		config.fullscreen = true;
		config.title = "Space Shooter";
		new LwjglApplication(new MyGdxGame(), config);
	}
}
