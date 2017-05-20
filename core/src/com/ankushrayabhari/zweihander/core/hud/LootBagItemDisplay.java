package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.items.Consumable;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.items.misc.Armor;
import com.ankushrayabhari.zweihander.items.misc.Potion;
import com.ankushrayabhari.zweihander.items.misc.Ring;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by ankushrayabhari on 5/19/17.
 */

public class LootBagItemDisplay extends Actor {
    private float WidthConversion, HeightConversion;
    private Item item;
    private GameScreen game;

    public LootBagItemDisplay(final GameScreen game, Item initialItem) {
        this.item = initialItem;

        this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

                if (this.getTapCount() >= 2) {
                    Item currentItem = getItem();

                    if(game.getPlayer().getInventory().addItem(currentItem)) {
                        game.getPlayer().getCurrentBag().removeItem(currentItem);
                    }

                    game.getHud().getInventoryDisplay().refreshItemDisplay();
                    game.getHud().getLootBagDisplay().refreshItemDisplay();
                }
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        WidthConversion = this.getWidth()/10;
        HeightConversion = this.getHeight()/10;

        batch.draw(item.getIcon(), this.getX() + 1 * WidthConversion, this.getY() + 1 * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
