package com.ankushrayabhari.zweihander.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ankushrayabhari.zweihander.Zweihander;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1366;
        config.height = 768;
		new LwjglApplication(new Zweihander(), config);
	}
}
