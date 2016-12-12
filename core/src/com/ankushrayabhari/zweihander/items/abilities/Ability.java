package com.ankushrayabhari.zweihander.items.abilities;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Ability extends Item {
    private float fireDelay, fireTimeCounter;
    private int manaCost;
    private GameScreen game;
    private Action action;

    public Ability(GameScreen game, AbilityDef def) {
        super(def);
        fireDelay = def.getDelay();
        fireTimeCounter = 0;
        this.game = game;
        this.manaCost = def.getManaCost();
        this.action = def.getAction();
    }

    public void update(Float delta) {
        fireTimeCounter += delta;
    }

    public void fire() {
        if((fireTimeCounter > fireDelay && game.getPlayer().getMana() > manaCost)) {
            fireTimeCounter = 0;
            action.execute(game);
            game.getPlayer().dealMana(manaCost);
        }
    };
}
