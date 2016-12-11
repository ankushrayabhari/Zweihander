package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.core.KeyboardController;
import com.ankushrayabhari.zweihander.items.Inventory;
import com.ankushrayabhari.zweihander.items.abilities.tomes.SampleTome;
import com.ankushrayabhari.zweihander.items.armor.SampleArmor;
import com.ankushrayabhari.zweihander.items.rings.SampleRing;
import com.ankushrayabhari.zweihander.items.weapons.WeaponFactory;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Player class
 *
 * @author Ankush Rayabhari
 */
public class Player extends PhysicalEntity {
    private Vector2 movementDirection;
    private WalkAnimation walkAnimation;
    private Constants.DIRECTION lastDirection;
    private Texture healthbar;
    private Inventory inventory;
    private int level, mana, baseMaxMana, baseDefense, baseAttack, baseSpeed;

	public Player(GameScreen game) {
		super(game, 20, 100, 100, false, Constants.FILTER_DATA.PLAYER, new Vector2(100, 100), new Vector2(2, 2), 0, true);

        inventory = new Inventory(game, WeaponFactory.createWeapon(game, 4), new SampleArmor(), new SampleTome(game), new SampleRing());

        level = 100;
        mana = 100;
        baseMaxMana = 100;
        baseDefense = 10;
        baseAttack = 10;
        baseSpeed = 15;

		movementDirection = new Vector2(0,0);
        walkAnimation = new WalkAnimation(false, 19, 2/getSpeed());
        lastDirection = Constants.DIRECTION.DOWN;
        healthbar = Assets.getTex("textures/lofi_halls.png");
	}

	@Override
	public void update(float delta) {
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
        	movementDirection.scl(getSpeed()*2f/3f);
        }
        else {
        	movementDirection.scl(getSpeed());
        }
        this.getBody().setLinearVelocity(movementDirection);
        
        
        //Firing
        if (this.getGame().getInputController().isFire1()) {
        	inventory.getActiveWeapon().fire();
        }
        inventory.getActiveWeapon().update(delta);
	}

	@Override
	public void draw(SpriteBatch batch) {
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
	}

	@Override
	public void onCollide(PhysicalEntity entity) {}
    public Inventory getInventory() {
        return inventory;
    }
    public void levelUp() {
        level++;
    }
    public int getLevel() {
        return level;
    }

    public float getManaPercentage() {
        return (float) mana/(float) getMaxMana();
    }

    public int getAttack() {
        return baseAttack+inventory.getAttackBonus();
    }

    public int getDefense() {
        return baseDefense+inventory.getDefenseBonus();
    }

    public int getSpeed() {
        return baseSpeed+inventory.getSpeedBonus();
    }

    public int getMaxMana() {
        return baseMaxMana+inventory.getManaBonus();
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth+inventory.getHealthBonus();
    }

    public void dealMana(int amount) {
        mana -= amount;
        if(mana < 0) {
            mana = 0;
        }
    }
    public void addMana(int amount) {
        mana += amount;
        if(mana > getMaxMana()) mana = getMaxMana();
    }
}
