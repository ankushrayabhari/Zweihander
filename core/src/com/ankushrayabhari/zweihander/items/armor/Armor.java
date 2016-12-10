package com.ankushrayabhari.zweihander.items.armor;

import com.ankushrayabhari.zweihander.items.Item;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Armor extends Item {
    public Armor(String name, String description, TextureRegion icon, int attackBonus, int defenseBonus, int speedBonus, int healthBonus, int manaBonus) {
        super(name, description, icon, attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus);
    }
}
