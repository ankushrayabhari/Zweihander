package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HeadsUpDisplay extends Stage {
	private GameScreen game;
    private Minimap minimap;
    private PlayerDisplay playerDisplay;
    private InventoryDisplay inventoryDisplay;
    private Texture texture;

	public HeadsUpDisplay(GameScreen game) {
        super(new ScreenViewport());
		this.game = game;

        minimap = new Minimap(game);
        minimap.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250, 250, 250);
        this.addActor(minimap);

        playerDisplay = new PlayerDisplay(game);
        playerDisplay.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 250 / 55 * 31, 250, 250 / 55 * 31);
        this.addActor(playerDisplay);

        inventoryDisplay = new InventoryDisplay(game, Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 450 / 55 * 31, 250, 250 / 55 * 31);
        this.addActor(inventoryDisplay);

        texture = Assets.getTex("textures/lofi_halls.png");
	}

    @Override
    public void draw() {
        this.getBatch().begin();
        this.getBatch().draw(texture, Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 450 / 56 * 31, 250, 250 + 450 / 56 * 31, 9, 601, 55, 31, false, false);
        this.getBatch().end();

        super.draw();
    }

    public void dispose() {
        super.dispose();
        minimap.dispose();
    }

    public InventoryDisplay getInventoryDisplay() { return inventoryDisplay; }
}
