package com.ankushrayabhari.zweihander.entities.physical.enemies;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.Damageable;
import com.ankushrayabhari.zweihander.entities.physical.PhysicalEntity;
import com.ankushrayabhari.zweihander.entities.physical.StatusMessage;
import com.ankushrayabhari.zweihander.entities.physical.WalkAnimation;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;

public class Enemy extends PhysicalEntity implements Damageable {
	private Vector2 movementDirection;
	private final float SPEED;
	private Constants.DIRECTION lastDirection;
	private WalkAnimation walkAnimation;
	private Texture healthbar;
    private float health;
    private LinkedList<String> statusMessages;
    private int maxHeath, defense;
	
	public Enemy(GameScreen game) {
		super(game, 10, false, Constants.PhysicalEntityTypes.ENEMY, new Vector2(105, 105), new Vector2(2, 2), 0, true);
		movementDirection = new Vector2(0,0);
		SPEED = 10;
		walkAnimation = new WalkAnimation(true, 19, 2/SPEED);
		lastDirection = Constants.DIRECTION.DOWN;
		healthbar = Assets.getTex("textures/lofi_halls.png");
        health = 1000;
        maxHeath = 1000;
        statusMessages = new LinkedList<String>();
	}
	
	@Override
	public void update(float delta) {
        for(String message : this.statusMessages) {
            this.getGame().addEntity(new StatusMessage(this, this.getGame(), message, com.badlogic.gdx.graphics.Color.RED, new Vector2(this.getBody().getPosition().x, this.getBody().getPosition().y+this.getDimensions().y)));
        }
        this.statusMessages.clear();
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
		batch.draw(healthbar, position.x - 1, position.y - 1 - 0.25f, this.getHealthPercentage() * 2, 0.25f, 31, 553, 21, 2, false, false);
	}

    @Override
    public void onDeath() {
        this.destroyBody();
    }

    @Override
    public void onCollide(PhysicalEntity entity) {}

    //Damageable Methods
    @Override
    public void dealHealth(float amount) {
        if(getDefense() > 0.85*amount) amount *= 0.15;
        health -= amount;
        this.statusMessages.add("-"+Integer.toString((int) amount));
        if(this.health < 0) {
            this.setDead();
        }
    }
    @Override
    public void addHealth(float amount) {
        this.health += amount;
        if(this.health > getMaxHealth()) {
            this.health = getMaxHealth();
        }
    }
    @Override
    public int getMaxHealth() { return this.maxHeath; }
    @Override
    public float getHealthPercentage() { return health/ (float) getMaxHealth(); }
    @Override
    public float getHealth() { return health; }
    @Override
    public int getDefense() { return defense; }
    @Override
    public void setDefense(int amount) { this.defense = amount; }
}
