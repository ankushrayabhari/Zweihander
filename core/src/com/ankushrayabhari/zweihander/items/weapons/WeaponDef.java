package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.items.weapons.actions.FireAction;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class WeaponDef {
    private int id;
    private float delay;
    private FireAction action;
    private String name, description;
    private TextureRegion icon;
    private int attackBonus, defenseBonus, speedBonus, healthBonus, manaBonus;

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
    public void setDelay(float delay) {
        this.delay = delay;
    }
    public void setAction(FireAction action) {
        this.action = action;
    }
    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }
    public void setDefenseBonus(int defenseBonus) {
        this.defenseBonus = defenseBonus;
    }
    public void setSpeedBonus(int speedBonus) {
        this.speedBonus = speedBonus;
    }
    public void setHealthBonus(int healthBonus) {
        this.healthBonus = healthBonus;
    }
    public void setManaBonus(int manaBonus) {
        this.manaBonus = manaBonus;
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
    public float getDelay() { return delay; }
    public FireAction getAction() { return action; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
