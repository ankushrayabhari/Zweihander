package com.ankushrayabhari.zweihander.entities.physical.projectiles;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.PhysicalEntity;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Standard Projectile
 *
 * @author Ankush Rayabhari
 */
public class Projectile extends PhysicalEntity {
    private Vector2 movementDirection, originalPosition;
    private int range, speed, damage;
    private Sprite sprite;

    public Projectile(GameScreen game, Constants.FILTER_DATA filterData, Vector2 originalPosition, Vector2 fireDirection, int speed, int range, int damange) {
        super(game, 50, 1, 1, false, filterData, originalPosition, new Vector2(1.5f,1.5f), (float) Math.atan2(-fireDirection.x, fireDirection.y), false);
        this.range = range;
        this.speed = speed;
        this.damage = 10;
        this.originalPosition = originalPosition;
        this.movementDirection = fireDirection.scl(speed);
        sprite = new Sprite(new TextureRegion(Assets.getTex("textures/lofi_obj.png"), 80, 72, 8, 8));
    }
    
    @Override
    public void draw(SpriteBatch batch) {
    	sprite.setOrigin(0.75f, 0.75f);
    	sprite.setRotation(45+(float) Math.toDegrees(this.getBody().getAngle()));
    	Vector2 position = this.getPosition().add(-0.75f, -0.75f);
    	sprite.setBounds(position.x, position.y, 1.5f, 1.5f);
    	sprite.draw(batch);
    }

    @Override
    public void update(float delta) {
        if(originalPosition.dst2(this.getBody().getPosition()) >= range) {
            this.setDead(true);
            return;
        }
        this.getBody().setLinearVelocity(movementDirection);
    }

    @Override
    public void onCollide(PhysicalEntity entity) {
        if(entity != null) entity.dealDamage(damage);
    	this.setDead(true);
    }

    @Override
    public void dealDamage(float amount) {
        this.health -= amount;
        if(this.health <= 0) {
            this.setDead(true);
        }
    }

    @Override
    public int getMaxHealth()  { return baseMaxHealth; }
}
