package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.entities.physical.LootBag;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class HeadsUpDisplay extends Stage {
	private GameScreen game;
    private Minimap minimap;
    private PlayerDisplay playerDisplay;
    private InventoryDisplay inventoryDisplay;
    private LootBagDisplay lootBagDisplay;

	public HeadsUpDisplay(GameScreen game) {
        super(new ScreenViewport());
		this.game = game;

        minimap = new Minimap(game);
        minimap.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250, 250, 250);
        this.addActor(minimap);

        playerDisplay = new PlayerDisplay(game);
        playerDisplay.setBounds(Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 375 / 55 * 31, 250, 375 / 55 * 31);
        this.addActor(playerDisplay);

        inventoryDisplay = new InventoryDisplay(game, Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 700 / 55 * 31, 250, 375 / 55 * 31);
        this.addActor(inventoryDisplay);

        lootBagDisplay = new LootBagDisplay(game, Gdx.graphics.getWidth() - 250, Gdx.graphics.getHeight() - 250 - 1025 / 55 * 31, 250, 375 / 55 * 31);
        this.addActor(lootBagDisplay);
        lootBagDisplay.setVisible(false);
	}

    public void dispose() {
        super.dispose();
        minimap.dispose();
    }

    public void setLootBagDisplay(boolean on) {
        if(lootBagDisplay.isVisible() && on || !lootBagDisplay.isVisible() && !on) return;

        lootBagDisplay.setVisible(on);
        lootBagDisplay.refreshItemDisplay();
    }

    public InventoryDisplay getInventoryDisplay() { return inventoryDisplay; }
    public LootBagDisplay getLootBagDisplay() { return lootBagDisplay; }
}
