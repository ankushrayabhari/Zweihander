package com.ankushrayabhari.zweihander.items.weapons.actions;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.projectiles.Projectile;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class WandFireAction implements Action {
    public void execute(GameScreen game) {
        Vector2 worldPlayerPos = game.getPlayer().getPosition();
        Vector3 localPlayerPos = game.getCamera().project(new Vector3(worldPlayerPos, 0));
        Vector2 fireDirection = new Vector2(0,0);
        fireDirection.x = game.getInputController().getMouseCoordinates().x - localPlayerPos.x;
        fireDirection.y = game.getInputController().getMouseCoordinates().y - localPlayerPos.y;
        fireDirection.nor();

        game.addEntity(new Projectile(game, Constants.FILTER_DATA.ALLY_PROJECTILE, new Vector2(game.getPlayer().getPosition()), fireDirection));
    }
}
