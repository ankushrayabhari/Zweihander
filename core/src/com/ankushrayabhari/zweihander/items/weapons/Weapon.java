package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public abstract class Weapon extends Item {
	protected float fireDelay, fireTimeCounter;
	private GameScreen game;

	public Weapon(GameScreen game, float delay, String name, String description, TextureRegion icon, int attackBonus, int defenseBonus, int speedBonus, int healthBonus, int manaBonus) {
        super(name, description, icon, attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus);
		fireDelay = delay;
        fireTimeCounter = 0;
        this.game = game;
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
            createProjectile(fireDirection);
        }
	}

    protected abstract void createProjectile(Vector2 fireDirection);

    protected GameScreen getGame() {
        return game;
    }
}
