package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HeadsUpDisplay extends Stage {
	private GameScreen game;
    private Minimap minimap;
    private PlayerDisplay playerDisplay;

	public HeadsUpDisplay(GameScreen game) {
        super(new ScreenViewport());
		this.game = game;

        minimap = new Minimap(game);
        minimap.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250, 250, 250);
        this.addActor(minimap);

        playerDisplay = new PlayerDisplay(game);
        playerDisplay.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 250 / 56 * 31, 250, 250 / 56 * 31);
        this.addActor(playerDisplay);
	}

    public void dispose() {
        super.dispose();
        minimap.dispose();
    }
}
