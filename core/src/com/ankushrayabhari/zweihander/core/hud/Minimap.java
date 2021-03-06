package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Minimap extends Actor implements Disposable {
    private GameScreen game;
    private ShapeRenderer renderer;
    private Texture texture;

    public Minimap(GameScreen game) {
        this.game = game;
        renderer = new ShapeRenderer();
        texture = Assets.getTex("textures/lofi_halls.png");
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 9, 601, 55, 31, false, false);
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.DARK_GRAY);
        renderer.box(this.getX(), this.getY(), 0, this.getWidth(), this.getHeight(), 0);
        renderer.end();

        batch.begin();
        batch.draw(game.getMap().getMinimap(), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        batch.end();

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.RED);
        Vector2 playerPosition = game.getPlayer().getPosition();
        renderer.circle(this.getX() + playerPosition.x / Constants.BOUNDS * this.getWidth(), this.getY() + playerPosition.y / Constants.BOUNDS * this.getHeight(), 3);
        renderer.end();

        batch.begin();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
