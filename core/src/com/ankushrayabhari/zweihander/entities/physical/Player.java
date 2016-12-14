package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.core.Inventory;
import com.ankushrayabhari.zweihander.core.KeyboardController;
import com.ankushrayabhari.zweihander.items.ItemFactory;
import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.items.misc.Armor;
import com.ankushrayabhari.zweihander.items.misc.Ring;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.LinkedList;

/**
 * Player class
 *
 * @author Ankush Rayabhari
 */
public class Player extends PhysicalEntity implements Damageable {
    private Vector2 movementDirection;
    private WalkAnimation walkAnimation;
    private Constants.DIRECTION lastDirection;
    private Inventory inventory;
    private int level, xp, maxXp, baseMaxMana, baseDefense, baseAttack, baseSpeed, baseWisdom, baseVitality, baseDexterity, baseMaxHealth;
    private float mana, health;
    private LinkedList<String> statusMessages;
	public Player(GameScreen game) {
		super(game, 20, false, Constants.PhysicalEntityTypes.ALLY, new Vector2(100, 100), new Vector2(2, 2), 0, true);

        inventory = new Inventory(
                game,
                (Weapon) ItemFactory.createItem(game, 4, ItemFactory.ItemTypes.Weapon),
                (Armor) ItemFactory.createItem(game, 60, ItemFactory.ItemTypes.Armor),
                (Ability) ItemFactory.createItem(game, 30, ItemFactory.ItemTypes.Ability),
                (Ring) ItemFactory.createItem(game, 90, ItemFactory.ItemTypes.Ring)
        );

        statusMessages = new LinkedList<String>();
        level = 1;

        baseMaxHealth = 100;
        baseMaxMana = 100;
        baseDefense = 10;
        baseAttack = 10;
        baseSpeed = 14;
        baseWisdom = 10;
        baseVitality = 10;
        baseDexterity = 10;


        xp = 50;
        maxXp = 100;

        mana = getMaxMana();
        health = getMaxHealth();

		movementDirection = new Vector2(0,0);
        walkAnimation = new WalkAnimation(false, 19, 2/getSpeed());
        lastDirection = Constants.DIRECTION.DOWN;

        inventory.addItem(ItemFactory.createItem(game, 1, ItemFactory.ItemTypes.Weapon));
        inventory.addItem(ItemFactory.createItem(game, 2, ItemFactory.ItemTypes.Weapon));
        inventory.addItem(ItemFactory.createItem(game, 3, ItemFactory.ItemTypes.Weapon));
        inventory.addItem(ItemFactory.createItem(game, 61, ItemFactory.ItemTypes.Armor));
        inventory.addItem(ItemFactory.createItem(game, 31, ItemFactory.ItemTypes.Ability));
        inventory.addItem(ItemFactory.createItem(game, 121, ItemFactory.ItemTypes.Potion));
	}

	@Override
	public void update(float delta) {
        for(String message : this.statusMessages) {
            this.getGame().addEntity(new StatusMessage(this, this.getGame(), message, com.badlogic.gdx.graphics.Color.RED, new Vector2(this.getBody().getPosition().x, this.getBody().getPosition().y+this.getDimensions().y)));
        }
        this.statusMessages.clear();

        this.addHealth(1 / 600f * getVitality());
        this.addMana(1/600f*getWisdom());

        inventory.getActiveWeapon().setDelayReduction(1/1000f*(float) getDexterity());

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

        //Ability
        if(this.getGame().getInputController().isFire2()) {
            inventory.getActiveAbility().fire();
        }
        inventory.getActiveAbility().update(delta);
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
        sprite.setBounds(position.x - 1, position.y - 1, 2, height);
        sprite.draw(batch);
	}

    //XP and Leveling Methods
    public int getLevel() { return level; }
    public float getXpPercentage() { return (float) xp / (float) getMaxXp(); }
    public int getXp() { return xp; }
    public int getMaxXp() { return maxXp; }
    public void addXp(int amount) {
        xp += amount;
        if(xp > 100) {
            xp = 0;
            level++;
        }
    }

    //Increase Statistics
    public void increaseBaseAttack(int amount) { this.baseAttack += amount; }
    public void increaseBaseSpeed(int amount) { this.baseSpeed += amount; }
    public void increaseBaseDexterity(int amount) { this.baseDexterity += amount; }
    public void increaseBaseVitality(int amount) { this.baseVitality += amount; }
    public void increaseBaseWisdom(int amount) { this.baseWisdom += amount; }
    public void increaseBaseHealth(int amount) { this.baseMaxHealth += amount; }
    public void increaseBaseMana(int amount) { this.baseMaxMana += amount; }
    public void increaseBaseDefense(int amount) { this.baseDefense += amount; }

    //Standard Statistics
    public int getAttack() { return baseAttack+inventory.getAttackBonus(); }
    public int getSpeed() { return baseSpeed+inventory.getSpeedBonus(); }
    public int getWisdom() { return baseWisdom+inventory.getWisdomBonus(); }
    public int getVitality() { return baseVitality+inventory.getVitalityBonus(); }
    public int getDexterity() { return baseDexterity+inventory.getDexterityBonus(); }

    //Mana Related Methods
    public void dealMana(float amount) {
        mana -= amount;
        if(mana < 0) {
            mana = 0;
        }
    }
    public void addMana(float amount) {
        mana += amount;
        if(mana > getMaxMana()) mana = getMaxMana();
    }
    public float getMana() { return mana; }
    public float getManaPercentage() { return mana/(float) getMaxMana(); }
    public int getMaxMana() { return baseMaxMana+inventory.getManaBonus(); }

    //Damageable Methods
    @Override
    public void dealHealth(float amount) {
        if(getDefense() > 0.85*amount) amount *= 0.15;
        health -= amount;
        this.statusMessages.add("-"+Float.toString(amount));
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
    public int getMaxHealth() { return this.baseMaxHealth +inventory.getHealthBonus(); }
    @Override
    public float getHealthPercentage() { return health/ (float) getMaxHealth(); }
    @Override
    public float getHealth() { return health; }
    @Override
    public int getDefense() { return baseDefense+inventory.getDefenseBonus(); }
    @Override
    public void setDefense(int amount) { this.baseDefense = amount; }

    //Misc Getters and Entity Methods
    @Override
    public void onDeath() {
        this.destroyBody();
    }

    @Override
    public void onCollide(PhysicalEntity entity) {}
    public Inventory getInventory() { return inventory; }
}
