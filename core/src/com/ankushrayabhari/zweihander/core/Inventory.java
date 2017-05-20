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
        items = new ArrayList<Item>(InventorySize);
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
        int weaponIndex = -1;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) == weapon) {
                weaponIndex = i;
            }
        }
        int activeWeaponId = activeWeapon.getId();
        int weaponId = weapon.getId();
        activeWeapon = (Weapon) ItemFactory.createItem(game, weaponId, ItemFactory.ItemTypes.Weapon);

        weapon = ItemFactory.createItem(game, activeWeaponId, ItemFactory.ItemTypes.Weapon);
        if(weaponIndex != -1) items.set(weaponIndex, weapon);
        return weapon;
    }

    public Ring getActiveRing() {
        return activeRing;
    }

    public Item setActiveRing(Item ring) {
        int ringIndex = -1;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) == ring) {
                ringIndex = i;
            }
        }
        int activeRingId = activeRing.getId();
        int ringId = ring.getId();
        activeRing = (Ring) ItemFactory.createItem(game, ringId, ItemFactory.ItemTypes.Ring);

        ring = ItemFactory.createItem(game, activeRingId, ItemFactory.ItemTypes.Ring);
        if(ringIndex != -1) items.set(ringIndex, ring);
        return ring;
    }

    public Armor getActiveArmor() {
        return activeArmor;
    }

    public Item setActiveArmor(Item armor) {
        int armorIndex = -1;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) == armor) {
                armorIndex = i;
            }
        }
        int activeArmorId = activeArmor.getId();
        int armorId = armor.getId();
        activeArmor = (Armor) ItemFactory.createItem(game, armorId, ItemFactory.ItemTypes.Armor);

        armor = ItemFactory.createItem(game, activeArmorId, ItemFactory.ItemTypes.Armor);
        if(armorIndex != -1) items.set(armorIndex, armor);
        return armor;
    }

    public Ability getActiveAbility() {
        return activeAbility;
    }

    public Item setActiveAbility(Item ability) {
        int abilityIndex = -1;
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i) == ability) {
                abilityIndex = i;
            }
        }
        int activeAbilityId = activeAbility.getId();
        int abilityId = ability.getId();
        activeAbility = (Ability) ItemFactory.createItem(game, abilityId, ItemFactory.ItemTypes.Ability);

        ability = ItemFactory.createItem(game, activeAbilityId, ItemFactory.ItemTypes.Ability);
        if(abilityIndex != -1) items.set(abilityIndex, ability);
        return ability;
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
