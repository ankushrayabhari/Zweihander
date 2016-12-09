package com.ankushrayabhari.zweihander.items;

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

    public Inventory(Weapon startWeapon) {
        items =  new ArrayList<Item>(6);
        activeWeapon = startWeapon;
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

    public void removeItem(int index) {
        items.remove(index);
    }

    public Weapon getActiveWeapon() {
        return activeWeapon;
    }
}
