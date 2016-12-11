package com.ankushrayabhari.zweihander.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {
	private String name;
	private String description;
	private TextureRegion icon;
    private int attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus;

	public Item(ItemDef itemDef) {
        this.name = itemDef.getName();
		this.description = itemDef.getDescription();
		this.icon = itemDef.getIcon();

        this.attackBonus = itemDef.getAttackBonus();
        this.defenseBonus = itemDef.getDefenseBonus();
        this.speedBonus = itemDef.getSpeedBonus();
        this.healthBonus = itemDef.getHealthBonus();
        this.manaBonus = itemDef.getManaBonus();
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
