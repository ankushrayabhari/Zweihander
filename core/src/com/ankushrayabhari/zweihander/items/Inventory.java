package com.ankushrayabhari.zweihander.items;

import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.items.armor.Armor;
import com.ankushrayabhari.zweihander.items.rings.Ring;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;
import com.ankushrayabhari.zweihander.screens.GameScreen;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Inventory {
    private final int InventorySize = 8;
    private ArrayList<Item> items;
    private Weapon activeWeapon;
    private Ring activeRing;
    private Ability activeAbility;
    private Armor activeArmor;
    private GameScreen game;

    public Inventory(GameScreen game, Weapon startWeapon, Armor startArmor, Ability startAbility, Ring startRing) {
        this.game = game;
        items =  new ArrayList<Item>(InventorySize);
        activeWeapon = startWeapon;
        activeRing = startRing;
        activeArmor = startArmor;
        activeAbility = startAbility;
    }

    public boolean addItem(Item item) {
        if(items.size() == InventorySize) {
            return false;
        }
        else {
            items.add(item);
            return true;
        }
    }

    public ListIterator<Item> iterator() {
        return items.listIterator();
    }

    public void removeItem(int index) {
        items.remove(index);
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public void setActiveWeapon(Item weapon) {
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

}
