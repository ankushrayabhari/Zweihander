package com.ankushrayabhari.zweihander.items.misc;

import com.ankushrayabhari.zweihander.items.ItemDef;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class PotionDef extends ItemDef {
    private int healAmount, manaHealAmount;

    public int getHealAmount() {
        return healAmount;
    }
    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
    public int getManaHealAmount() {
        return manaHealAmount;
    }
    public void setManaHealAmount(int manaHealAmount) {
        this.manaHealAmount = manaHealAmount;
    }
}
