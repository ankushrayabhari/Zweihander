package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;

public class Weapon extends Item {
	private float fireDelay, fireTimeCounter;
	private GameScreen game;
    private Action action;

	public Weapon(GameScreen game, WeaponDef def) {
        super(def);
		fireDelay = def.getDelay();
        fireTimeCounter = 0;
        this.game = game;
        this.action = def.getAction();
	}

	public void update(Float delta) {
		fireTimeCounter += delta;
	}

	public void fire() {
		if((fireTimeCounter > (fireDelay-1/1000f*(float) game.getPlayer().getDexterity()))) {
            fireTimeCounter = 0;
            action.execute(game);
        }
	}
}
