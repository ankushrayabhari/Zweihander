package com.ankushrayabhari.zweihander.items.weapons.actions;

import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public interface FireAction {
    public abstract void execute(GameScreen game, Vector2 fireDirection);
}
