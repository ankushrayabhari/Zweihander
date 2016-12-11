package com.ankushrayabhari.zweihander.items.weapons.actions;

import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.projectiles.Projectile;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class WandFireAction implements FireAction {
    public void execute(GameScreen game, Vector2 fireDirection) {
        game.addEntity(new Projectile(game, Constants.FILTER_DATA.ALLY_PROJECTILE, new Vector2(game.getPlayer().getPosition()), fireDirection));
    }
}
