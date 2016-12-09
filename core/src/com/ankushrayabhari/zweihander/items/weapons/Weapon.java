package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.core.Constants.FILTER_DATA;
import com.ankushrayabhari.zweihander.entities.physical.projectiles.Projectile;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Weapon extends Item {
	private float fireDelay, fireTimeCounter;
	private GameScreen game;
	private FILTER_DATA filterData;
	
	public Weapon(GameScreen game, boolean ally, float delay, String name, String description, TextureRegion icon) {
        super(name, description, icon);
		fireDelay = delay;
        fireTimeCounter = 0;
        this.game = game;
        if(ally) {
        	filterData = FILTER_DATA.ALLY_PROJECTILE;
        }
        else {
        	filterData = FILTER_DATA.ENEMY_PROJECTILE;
        }
	}
	
	public void update(Float delta) {
		fireTimeCounter += delta;
	}
	
	public void fire() {
		if((fireTimeCounter > fireDelay)) {
            fireTimeCounter = 0;
            Vector2 worldPlayerPos = game.getPlayer().getPosition();
            Vector3 localPlayerPos = game.getCamera().project(new Vector3(worldPlayerPos, 0));
            Vector2 fireDirection = new Vector2(0,0);
            fireDirection.x = game.getInputController().getMouseCoordinates().x - localPlayerPos.x;
            fireDirection.y = game.getInputController().getMouseCoordinates().y - localPlayerPos.y;
            fireDirection.nor();
            game.addEntity(new Projectile(game, filterData, new Vector2(game.getPlayer().getPosition()), fireDirection));
        }
	}
}
