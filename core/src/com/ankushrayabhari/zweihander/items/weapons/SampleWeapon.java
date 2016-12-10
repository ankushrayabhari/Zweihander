package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.projectiles.Projectile;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class SampleWeapon extends Weapon {
    public SampleWeapon(GameScreen game) {
        super(game, 1/6f, "Weapon", "A basic weapon", new TextureRegion(Assets.getTex("textures/lofi_obj.png"), 96, 24, 8, 8), 0, 0, 0, 0, 0);
    }

    @Override
    protected void createProjectile(Vector2 fireDirection) {
        this.getGame().addEntity(new Projectile(this.getGame(), Constants.FILTER_DATA.ALLY_PROJECTILE, new Vector2(this.getGame().getPlayer().getPosition()), fireDirection));
    }
}
