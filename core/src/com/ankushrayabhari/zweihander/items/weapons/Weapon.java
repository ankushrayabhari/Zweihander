package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.items.weapons.actions.FireAction;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Weapon extends Item {
	protected float fireDelay, fireTimeCounter;
	private GameScreen game;
    private FireAction action;

	public Weapon(GameScreen game, WeaponDef def) {
        super(def.getName(), def.getDescription(), def.getIcon(), def.getAttackBonus(), def.getDefenseBonus(), def.getSpeedBonus(), def.getHealthBonus(), def.getManaBonus());
		fireDelay = def.getDelay();
        fireTimeCounter = 0;
        this.game = game;
        this.action = def.getAction();
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
            action.execute(game, fireDirection);
        }
	}

    protected GameScreen getGame() {
        return game;
    }
}
