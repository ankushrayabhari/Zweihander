package com.ankushrayabhari.zweihander.items.weapons.player;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class SampleWeapon extends Weapon {
    public SampleWeapon(GameScreen game) {
        super(game, true, 1/6f, "Weapon", "A basic weapon", new TextureRegion(Assets.getTex("textures/lofi_obj.png"), 64, 24, 8, 8));
    }
}
