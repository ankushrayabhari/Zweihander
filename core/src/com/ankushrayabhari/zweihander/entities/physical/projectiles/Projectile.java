package com.ankushrayabhari.zweihander.entities.physical.projectiles;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.Damageable;
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

    public Projectile(GameScreen game, ProjectileDef def, Constants.PhysicalEntityTypes type, Vector2 position, Vector2 fireDirection, int damage, int range, int speed) {
        super(game, 10, false, type, position, new Vector2(1.0f,1.5f), (float) Math.atan2(-fireDirection.x, fireDirection.y), false);
        this.range = range;
        this.speed = speed;
        this.damage = damage;
        this.originalPosition = position;
        this.movementDirection = fireDirection.scl(speed);
        sprite = new Sprite(new TextureRegion(Assets.getTex("textures/lofi_obj.png"), 80, 96, 8, 8));
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
            this.setDead();
            return;
        }
        this.getBody().setLinearVelocity(movementDirection);
    }

    @Override
    public void onCollide(PhysicalEntity entity) {
        if(entity instanceof Damageable) ((Damageable) entity).dealHealth(damage);
    	this.setDead();
    }

    @Override
    public void onDeath() {
        this.destroyBody();
    };
}
