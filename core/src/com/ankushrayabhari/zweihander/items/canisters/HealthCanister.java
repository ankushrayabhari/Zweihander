package com.ankushrayabhari.zweihander.items.canisters;

import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HealthCanister extends Item {
	private GameScreen game;
	
	public HealthCanister(GameScreen game) {
		super("Health Canister", "Adds Health", new TextureRegion(new Texture("textures/lofi_obj.png"), 120, 56, 8, 8));
		this.game = game;
	}
	
	public void consume() {
		
	}

}
