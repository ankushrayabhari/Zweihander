package com.ankushrayabhari.zweihander.items.abilities.tomes;

import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Tome extends Ability {
    private int hpHeal;

    public Tome(GameScreen game, float cooldown, int hpHeal, int manaCost, String name, String description, TextureRegion icon, int attackBonus, int defenseBonus, int speedBonus, int healthBonus, int manaBonus) {
        super(game, cooldown, manaCost, name, description, icon, attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus);
        this.hpHeal = hpHeal;
    }

    @Override
    public void activate() {
        this.getGame().getPlayer().addHealth(hpHeal);
    }
}
