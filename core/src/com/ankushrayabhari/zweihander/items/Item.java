package com.ankushrayabhari.zweihander.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {
	private String name;
	private String description;
	private TextureRegion icon;
    private int id, attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus, wisdomBonus, vitalityBonus, dexterityBonus;

	public Item(ItemDef itemDef) {
        this.name = itemDef.getName();
        this.description = itemDef.getDescription();
        this.icon = itemDef.getIcon();
        this.id = itemDef.getId();
        this.attackBonus = itemDef.getAttackBonus();
        this.defenseBonus = itemDef.getDefenseBonus();
        this.speedBonus = itemDef.getSpeedBonus();
        this.healthBonus = itemDef.getHealthBonus();
        this.manaBonus = itemDef.getManaBonus();
        this.vitalityBonus = itemDef.getVitalityBonus();
        this.wisdomBonus = itemDef.getWisdomBonus();
        this.dexterityBonus = itemDef.getDexterityBonus();
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
    public int getWisdomBonus() { return wisdomBonus; }
    public int getVitalityBonus() { return vitalityBonus; }
    public int getDexterityBonus() {
        return dexterityBonus;
    }
    public int getId() { return id; }
}
