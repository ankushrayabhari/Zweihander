package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class PlayerDisplay extends Actor {
    private GameScreen game;

    public PlayerDisplay(GameScreen game) {
        this.game = game;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Assets.getTex("textures/lofi_halls.png"), this.getX(), this.getY(), this.getWidth(), this.getHeight(), 8, 569, 56, 31, false, false);
    }
}
