package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.Entity;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * A barebones entity with a defined physics object
 *
 * @author Ankush Rayabhari
 */
public abstract class PhysicalEntity extends Entity {
	private GameScreen game;
    private Body body;
    protected float health;
    protected int baseMaxHealth;
    private Vector2 dimensions;
    
    protected PhysicalEntity(GameScreen game, int zIndex, int health, int maxHealth, boolean staticBody, Constants.FILTER_DATA filterData, Vector2 position, Vector2 dimensions, float angle, boolean massive) {
    	super(zIndex);
    	this.game = game;
        this.health = health;
        this.baseMaxHealth = maxHealth;
    	this.dimensions = dimensions;
    	
        BodyDef bodyDef = new BodyDef();
        if(staticBody) {
            bodyDef.type = BodyDef.BodyType.StaticBody;
        }
        else {
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        }
        bodyDef.position.set(position);
        bodyDef.fixedRotation = true;
        bodyDef.angle = angle;
        body = this.getGame().getWorld().createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dimensions.x/2, dimensions.y/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        if(massive) {
        	fixtureDef.density = 10;
        }
        else {
        	fixtureDef.density = 0;
        }
        fixtureDef.friction = 0;
        fixtureDef.restitution = 0;
        switch (filterData) {
            case ALLY:
                fixtureDef.filter.categoryBits = Constants.CATEGORY_ALLY;
                fixtureDef.filter.maskBits = Constants.MASK_ALLY;
                break;
            case ENEMY:
                fixtureDef.filter.categoryBits = Constants.CATEGORY_ENEMY;
                fixtureDef.filter.maskBits = Constants.MASK_ENEMY;
                break;
            case ALLY_PROJECTILE:
                fixtureDef.filter.categoryBits = Constants.CATEGORY_ALLY_PROJECTILE;
                fixtureDef.filter.maskBits = Constants.MASK_ALLY_PROJECTILE;
                body.setBullet(true);
                break;
            case ENEMY_PROJECTILE:
                fixtureDef.filter.categoryBits = Constants.CATEGORY_ENEMY_PROJECTILE;
                fixtureDef.filter.maskBits = Constants.MASK_ENEMY_PROJECTILE;
                body.setBullet(true);
                break;
            case PLAYER:
            	fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
            	fixtureDef.filter.maskBits = Constants.MASK_PLAYER;
            	break;
        }
        body.createFixture(fixtureDef);
        body.setUserData(this);
        shape.dispose();

        this.setPosition(this.body.getPosition());
    }
    
    @Override
    public void onDeath() {
    	this.getGame().getWorld().destroyBody(body);
    }
    
    @Override
    public abstract void update(float delta);
    
    @Override
    public abstract void draw(SpriteBatch batch);
    
    public abstract void onCollide(PhysicalEntity entity);

    public void dealDamage(float amount) {
    	this.health -= amount;
    	if(this.health <= 0) {
    		this.setDead(true);
    	}
    	
    }
    
    public void addHealth(float amount) {
    	this.health += amount;
        if(this.health > this.getMaxHealth()) health = getMaxHealth();
    }
 
    public Body getBody() { return body; }
   
    public Vector2 getDimensions() { return dimensions; }
    
	public GameScreen getGame() { return game; }

    abstract protected int getMaxHealth();

    public float getHealthPercentage() {
        return health/(float) getMaxHealth();
    }
    public float getHealth() { return health; }
}
