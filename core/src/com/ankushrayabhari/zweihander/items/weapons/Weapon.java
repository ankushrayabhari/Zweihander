package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;

public class Weapon extends Item {
	private float fireDelay, fireTimeCounter, delayReduction;
	private GameScreen game;
    private Action action;

	public Weapon(GameScreen game, WeaponDef def) {
        super(def);
		fireDelay = def.getDelay();
        fireTimeCounter = 0;
        this.game = game;
        this.action = def.getAction();
        delayReduction = 0;
	}

	public void update(Float delta) {
		fireTimeCounter += delta;
	}
    public void setDelayReduction(Float delayReduction) { this.delayReduction = delayReduction; }
	public void fire() {
		if((fireTimeCounter > (fireDelay-delayReduction))) {
            fireTimeCounter = 0;
            action.execute(game);
        }
	}
}
