package com.ankushrayabhari.zweihander.core;

import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class HeadsUpDisplay {
	private GameScreen game;
	private ShapeRenderer renderer;
	
	public HeadsUpDisplay(GameScreen game) {
		this.game = game;
		renderer = new ShapeRenderer();
	}
	
	public void render(SpriteBatch batch) {
		float minimapX = Gdx.graphics.getWidth()-250;
		float minimapY = Gdx.graphics.getHeight()-250;
		
		Matrix4 hudMatrix = game.getCamera().combined.cpy();
		hudMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.setProjectionMatrix(hudMatrix);
		
		batch.begin();
		game.getMap().renderMinimap(batch, minimapX, minimapY, 250, 250);
        batch.end();
        
        renderer.begin(ShapeType.Filled);
        renderer.setColor(Color.PINK);
		
        Vector2 playerPosition = game.getPlayer().getPosition();
		renderer.circle(minimapX+playerPosition.x/Constants.BOUNDS*250, minimapY+playerPosition.y/Constants.BOUNDS*250, 3);
        renderer.end();
	}
}
