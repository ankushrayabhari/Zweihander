package com.ankushrayabhari.zweihander.entities.physical.enemies;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.PhysicalEntity;
import com.ankushrayabhari.zweihander.entities.physical.WalkAnimation;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Enemy extends PhysicalEntity {
	private Vector2 movementDirection;
	private final float SPEED;
	private Constants.DIRECTION lastDirection;
	private WalkAnimation walkAnimation;
	private Texture healthbar;
	
	public Enemy(GameScreen game) {
		super(game, 10, 100, 100, false, Constants.FILTER_DATA.ENEMY, new Vector2(105, 105), new Vector2(2, 2), 0, true);
		movementDirection = new Vector2(0,0);
		SPEED = 10;
		walkAnimation = new WalkAnimation(true, 19, 2/SPEED);
		lastDirection = Constants.DIRECTION.DOWN;
		healthbar = Assets.getTex("textures/lofi_halls.png");
	}
	
	@Override
	public void update(float delta) {
		Vector2 playerPosition = this.getGame().getPlayer().getPosition();
		Vector2 position = this.getPosition();
		Vector2 direction = new Vector2(playerPosition.x-position.x, playerPosition.y-position.y);
		
		Vector3 lowerBound = this.getGame().getCamera().unproject(new Vector3(0, Gdx.graphics.getHeight(), 0));
		Vector3 upperBound = this.getGame().getCamera().unproject(new Vector3(Gdx.graphics.getWidth(), 0, 0));
		
		boolean inView = (position.x > lowerBound.x && position.x < upperBound.x) && (position.y > lowerBound.y && position.y < upperBound.y);
		
		if(!inView || direction.isZero(5)) {
			movementDirection.set(0,0);
		}
		else {
			movementDirection.set(direction);
			movementDirection.nor();
	        movementDirection.scl(SPEED);
		}
        this.getBody().setLinearVelocity(movementDirection);
	}

	@Override
	public void draw(SpriteBatch batch) {
		Sprite sprite;		
        if (movementDirection.isZero()) {
            sprite = walkAnimation.getStaticSprite(lastDirection, false);
        }
        else {
            if(Math.abs(movementDirection.y) > Math.abs(movementDirection.x)) {
                if(movementDirection.y < 0) {
                    lastDirection = Constants.DIRECTION.DOWN;
                    sprite = walkAnimation.getMovingSprite(lastDirection, false);
                }
                else {
                    lastDirection = Constants.DIRECTION.UP;
                    sprite = walkAnimation.getMovingSprite(lastDirection, false);
                }
            }
            else {
                if(movementDirection.x > 0) {
                    lastDirection = Constants.DIRECTION.RIGHT;
                    sprite = walkAnimation.getMovingSprite(lastDirection, false);
                }
                else {
                    lastDirection = Constants.DIRECTION.LEFT;
                    sprite = walkAnimation.getMovingSprite(lastDirection, false);
                }
            }
        }
        
        Vector2 position = this.getBody().getPosition();
		sprite.setBounds(position.x-1, position.y-1, 2, 2);
		sprite.draw(batch);
		
		batch.draw(healthbar, position.x-1, position.y-1-0.25f, 2, 0.25f, 53, 553, 21, 2, false, false);
		batch.draw(healthbar, position.x-1, position.y-1-0.25f, this.getHealthPercentage()*2, 0.25f, 31, 553, 21, 2, false, false);
	}

	@Override
	public void onCollide(PhysicalEntity entity) {
		
	}

    @Override
    public int getMaxHealth()  { return baseMaxHealth; }
}
