package com.ankushrayabhari.zweihander.items.misc;

import com.ankushrayabhari.zweihander.entities.physical.Player;
import com.ankushrayabhari.zweihander.items.Consumable;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class Potion extends Item implements Consumable {
    int healAmount, manaHealAmount;

    public Potion(PotionDef itemDef) {
        super(itemDef);
        this.healAmount = itemDef.getHealAmount();
        this.manaHealAmount = itemDef.getManaHealAmount();
    }

    public void consume(GameScreen game) {
        Player player = game.getPlayer();
        player.increaseBaseHealth(this.getHealthBonus());
        player.increaseBaseMana(this.getManaBonus());
        player.increaseBaseAttack(this.getAttackBonus());
        player.increaseBaseDefense(this.getDefenseBonus());
        player.increaseBaseSpeed(this.getSpeedBonus());
        player.increaseBaseDexterity(this.getDexterityBonus());
        player.increaseBaseVitality(this.getVitalityBonus());
        player.increaseBaseWisdom(this.getWisdomBonus());
        player.addHealth(healAmount);
        player.addMana(manaHealAmount);
    }
}
