package com.ankushrayabhari.zweihander.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ankushrayabhari.zweihander.Zweihander;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = 1920;
		config.height = 1080;
		new LwjglApplication(new Zweihander(), config);
	}
}
