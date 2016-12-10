package com.ankushrayabhari.zweihander.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {
	private String name;
	private String description;
	private TextureRegion icon;
    private int attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus;

	public Item(String name, String description, TextureRegion icon,  int attackBonus, int defenseBonus, int speedBonus, int healthBonus, int manaBonus) {
		this.name = name;
		this.description = description;
		this.icon = icon;

        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.speedBonus = speedBonus;
        this.healthBonus = healthBonus;
        this.manaBonus = manaBonus;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public TextureRegion getIcon() {
		return icon;
	}

    public int getAttackBonus() {
        return attackBonus;
    }
    public int getDefenseBonus() {
        return defenseBonus;
    }
    public int getSpeedBonus() {
        return speedBonus;
    }
    public int getHealthBonus() {
        return healthBonus;
    }
    public int getManaBonus() {
        return manaBonus;
    }
}
