package com.ankushrayabhari.zweihander.core;

import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.items.ItemFactory;
import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.items.misc.Armor;
import com.ankushrayabhari.zweihander.items.misc.Ring;
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
    private final int InventorySize = 12;
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

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }

    public Item setActiveWeapon(Item weapon) {
        int activeWeaponId = activeWeapon.getId();
        activeWeapon = (Weapon) weapon;
        return ItemFactory.createItem(game, activeWeaponId, ItemFactory.ItemTypes.Weapon);
    }

    public Ring getActiveRing() {
        return activeRing;
    }

    public Item setActiveRing(Item ring) {
        int activeRingId = activeRing.getId();
        activeRing = (Ring) ring;
        return ItemFactory.createItem(game, activeRingId, ItemFactory.ItemTypes.Ring);
    }

    public Armor getActiveArmor() {
        return activeArmor;
    }

    public Item setActiveArmor(Item armor) {
        int activeArmorId = activeArmor.getId();
        activeArmor = (Armor) armor;
        return ItemFactory.createItem(game, activeArmorId, ItemFactory.ItemTypes.Armor);
    }

    public Ability getActiveAbility() {
        return activeAbility;
    }

    public Item setActiveAbility(Item ability) {
        int activeAbilityId = activeAbility.getId();
        activeAbility = (Ability) ability;
        return ItemFactory.createItem(game, activeAbilityId, ItemFactory.ItemTypes.Ability);
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
    public int getWisdomBonus() {
        return activeWeapon.getWisdomBonus()+activeRing.getWisdomBonus()+activeArmor.getWisdomBonus()+activeAbility.getWisdomBonus();
    }
    public int getVitalityBonus() {
        return activeWeapon.getVitalityBonus()+activeRing.getVitalityBonus()+activeArmor.getVitalityBonus()+activeAbility.getVitalityBonus();
    }
    public int getDexterityBonus() {
        return activeWeapon.getDexterityBonus()+activeRing.getDexterityBonus()+activeArmor.getDexterityBonus()+activeAbility.getDexterityBonus();
    }
    public ListIterator<Item> iterator() {
        return items.listIterator();
    }
}
