package com.ankushrayabhari.zweihander;

import com.ankushrayabhari.zweihander.core.Preferences;
import com.ankushrayabhari.zweihander.screens.CreditsScreen;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.ankushrayabhari.zweihander.screens.MainMenuScreen;
import com.ankushrayabhari.zweihander.screens.OptionScreen;
import com.ankushrayabhari.zweihander.screens.PlayScreen;
import com.badlogic.gdx.Game;

/**
 * Class to start the game
 * 
 * @author Austin Hsieh
 */
public class Zweihander extends Game {
	public static boolean DEBUG = true;

    public CreditsScreen creditsScreen;
    public GameScreen gameScreen;
    public PlayScreen playScreen;
    public MainMenuScreen mainMenuScreen;
    public OptionScreen optionScreen;

	public Zweihander() {
        creditsScreen = new CreditsScreen(this);
        gameScreen = new GameScreen(this);
        playScreen = new PlayScreen(this);
        mainMenuScreen = new MainMenuScreen(this);
        optionScreen = new OptionScreen(this);
	}

	@Override
	public void create() {
		Preferences.init();
		setScreen(mainMenuScreen);
	}

}
