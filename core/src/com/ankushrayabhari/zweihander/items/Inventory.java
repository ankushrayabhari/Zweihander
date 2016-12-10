package com.ankushrayabhari.zweihander.items;

import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.items.armor.Armor;
import com.ankushrayabhari.zweihander.items.rings.Ring;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;

import java.util.ArrayList;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Inventory {
    private ArrayList<Item> items;
    private Weapon activeWeapon;
    private Ring activeRing;
    private Ability activeAbility;
    private Armor activeArmor;

    public Inventory(Weapon startWeapon, Armor startArmor, Ability startAbility, Ring startRing) {
        items =  new ArrayList<Item>(6);
        activeWeapon = startWeapon;
        activeRing = startRing;
        activeArmor = startArmor;
        activeAbility = startAbility;
    }

    public boolean addItem(Item item) {
        if(items.size() == 6) {
            return false;
        }
        else {
            items.add(item);
            return true;
        }
    }

    public int getHealthBonus() {
        return activeWeapon.getHealthBonus()+activeRing.getHealthBonus()+activeArmor.getHealthBonus()+activeAbility.getHealthBonus();
    }

    public int getAttackBonus() {
        return activeWeapon.getAttackBonus()+activeRing.getAttackBonus()+activeArmor.getAttackBonus()+activeAbility.getAttackBonus();
    }

    public int getDefenseBonus() {
        return activeWeapon.getDefenseBonus()+activeRing.getDefenseBonus()+activeArmor.getDefenseBonus()+activeAbility.getDefenseBonus();
    }

    public int getSpeedBonus() {
        return activeWeapon.getSpeedBonus()+activeRing.getSpeedBonus()+activeArmor.getSpeedBonus()+activeAbility.getSpeedBonus();
    }

    public int getManaBonus() {
        return activeWeapon.getManaBonus()+activeRing.getManaBonus()+activeArmor.getManaBonus()+activeAbility.getManaBonus();
    }


    public void removeItem(int index) {
        items.remove(index);
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void setActiveWeapon(Weapon weapon) {
        activeWeapon = weapon;
    }

    public Ring getActiveRing() {
        return activeRing;
    }

    public void setActiveRing(Ring ring) {
        activeRing = ring;
    }

    public Armor getActiveArmor() {
        return activeArmor;
    }

    public void setActiveArmor(Armor armor) {
        activeArmor = armor;
    }

    public Ability getActiveAbility() {
        return activeAbility;
    }

    public void setActiveAbility(Ability ability) {
        activeAbility = ability;
    }
}
