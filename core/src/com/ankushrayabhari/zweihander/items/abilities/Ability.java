package com.ankushrayabhari.zweihander.items.abilities;

import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public abstract class Ability extends Item {
    protected float fireDelay, fireTimeCounter;
    private int manaCost;
    private GameScreen game;

    public Ability(GameScreen game, float cooldown, int manaCost, String name, String description, TextureRegion icon, int attackBonus, int defenseBonus, int speedBonus, int healthBonus, int manaBonus) {
        super(name, description, icon, attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus);
        fireDelay = cooldown;
        fireTimeCounter = 0;
        this.game = game;
        this.manaCost = manaCost;
    }

    public void update(Float delta) {
        fireTimeCounter += delta;
    }

    public void fire() {
        if((fireTimeCounter > fireDelay)) {
            fireTimeCounter = 0;
            activate();
            this.getGame().getPlayer().dealMana(manaCost);
        }
    };

    protected abstract void activate();

    protected GameScreen getGame() {
        return game;
    }

    public int getManaCost() { return manaCost; }
}
