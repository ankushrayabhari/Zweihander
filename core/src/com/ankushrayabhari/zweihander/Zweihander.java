package com.ankushrayabhari.zweihander;

import com.ankushrayabhari.zweihander.core.Preferences;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Game;

/**
 * Class to start the game
 * 
 * @author Austin Hsieh
 */
public class Zweihander extends Game {
	public static boolean DEBUG = true;

	public Zweihander() {
	}

	@Override
	public void create() {
// DEBUG = false; //comment/uncomment for easy testing

		Preferences.init(); // initialize this service so we can save data
		setScreen(new GameScreen(this));
	}

}
