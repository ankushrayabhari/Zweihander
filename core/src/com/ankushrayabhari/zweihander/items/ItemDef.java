package com.ankushrayabhari.zweihander.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class ItemDef {
    private String name, description;
    private TextureRegion icon;
    private int attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus;

    public ItemDef() {}

    public ItemDef(String name, String description, TextureRegion icon, int attackBonus, int defenseBonus, int speedBonus, int healthBonus, int manaBonus) {
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
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TextureRegion getIcon() {
        return icon;
    }
    public void setIcon(TextureRegion icon) {
        this.icon = icon;
    }
    public int getAttackBonus() {
        return attackBonus;
    }
    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }
    public int getDefenseBonus() {
        return defenseBonus;
    }
    public void setDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
    }
    public int getSpeedBonus() {
        return speedBonus;
    }
    public void setSpeedBonus(int speedBonus) {
        this.speedBonus = speedBonus;
    }
    public int getHealthBonus() {
        return healthBonus;
    }
    public void setHealthBonus(int healthBonus) {
        this.healthBonus = healthBonus;
    }
    public int getManaBonus() {
        return manaBonus;
    }
    public void setManaBonus(int manaBonus) {
        this.manaBonus = manaBonus;
    }
}