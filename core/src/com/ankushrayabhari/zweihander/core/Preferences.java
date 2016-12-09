package com.ankushrayabhari.zweihander.core;

import com.badlogic.gdx.Gdx;

/**
 * Wrapper class for using {@link com.badlogic.gdx.Preferences}
 * 
 * @author Austin Hsieh
 */
public class Preferences {
	/**
	 * 
	 */
	public static com.badlogic.gdx.Preferences prefs;

	/**
	 * initialization
	 */
	public static void init() {
		prefs = Gdx.app.getPreferences("FBLA2016");
		// add initial checks
		prefs.flush();
	}
}
