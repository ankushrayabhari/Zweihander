package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.core.KeyboardController;
import com.ankushrayabhari.zweihander.items.weapons.player.SampleWeapon;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Player class that wreaks havoc on your peasant minds.
 * ~/Development/Zweihander/core/src/com/ankushrayabhari/zweihander/screens
 * @author Ankush Rayabhari
 */
public class Player extends PhysicalEntity {
    private Vector2 movementDirection;
    private SampleWeapon weapon;
    private final float SPEED;
    private WalkAnimation walkAnimation;
    private Constants.DIRECTION lastDirection;
    private Texture healthbar;
    
	public Player(GameScreen game) {
		super(game, 20, 100, false, Constants.FILTER_DATA.PLAYER, new Vector2(100, 100), new Vector2(2, 2), 0, true);

		SPEED = 16;
		movementDirection = new Vector2(0,0);
        walkAnimation = new WalkAnimation(false, 13, 2/SPEED);
        lastDirection = Constants.DIRECTION.DOWN;
        healthbar = Assets.getTex("textures/lofi_halls.png");
        weapon = new SampleWeapon(game);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		
        //Movement
        movementDirection.set(0, 0);
        KeyboardController keyboardController = this.getGame().getInputController();
        if(keyboardController.isMoveUp()) {
            movementDirection.y++;
        }
        if(keyboardController.isMoveDown()) {
            movementDirection.y--;
        }
        if(keyboardController.isMoveRight()) {
            movementDirection.x++;
        }
        if(keyboardController.isMoveLeft()) {
            movementDirection.x--;
        }
        movementDirection.nor();
        if(this.getGame().getMap().isWater(this.getPosition())) {
        	movementDirection.scl(SPEED-4);
        }
        else {
        	movementDirection.scl(SPEED);
        }
        this.getBody().setLinearVelocity(movementDirection);
        
        
        //Firing
        if (this.getGame().getInputController().isFire1()) {
        	weapon.fire();
        }
        weapon.update(delta);
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
        Sprite sprite;
        boolean water = this.getGame().getMap().isWater(this.getPosition());
    	if (movementDirection.isZero()) {
	        sprite = walkAnimation.getStaticSprite(lastDirection, water);
	    }
	    else {
	        if(Math.abs(movementDirection.y) > Math.abs(movementDirection.x)) {
	            if(movementDirection.y < 0) {
	                lastDirection = Constants.DIRECTION.DOWN;
	                sprite = walkAnimation.getMovingSprite(lastDirection, water);
	            }
	            else {
	                lastDirection = Constants.DIRECTION.UP;
	                sprite = walkAnimation.getMovingSprite(lastDirection, water);
	            }
	        }
	        else {
	            if(movementDirection.x > 0) {
	                lastDirection = Constants.DIRECTION.RIGHT;
	                sprite = walkAnimation.getMovingSprite(lastDirection, water);
	            }
	            else {
	                lastDirection = Constants.DIRECTION.LEFT;
	                sprite = walkAnimation.getMovingSprite(lastDirection, water);
	            }
	        }
	    }
    	
        Vector2 position = this.getPosition();
        float height = water ? 1.5f : 2;
        sprite.setBounds(position.x-1, position.y-1, 2, height);		
		sprite.draw(batch);
		
		batch.draw(healthbar, position.x-1, position.y-1-0.25f, 2, 0.25f, 53, 561, 21, 2, false, false);
		batch.draw(healthbar, position.x-1, position.y-1-0.25f, this.health/100*2, 0.25f, 31, 561, 21, 2, false, false);
	}

	@Override
	public void onCollide(PhysicalEntity entity) {

	}
}
