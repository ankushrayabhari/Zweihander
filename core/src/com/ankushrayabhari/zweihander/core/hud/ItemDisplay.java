package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class ItemDisplay extends Actor {
    private float WidthConversion, HeightConversion;
    private Item item;
    private GameScreen game;

    public ItemDisplay(final GameScreen game, Item item) {
        this.item = item;

        this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(this.getTapCount() >= 2) {
                    Item clickedItem = ((ItemDisplay) event.getTarget()).getItem();
                    if(clickedItem instanceof Weapon) {
                        game.getPlayer().getInventory().setActiveWeapon(clickedItem);
                    }
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

    public Item getItem() {
        return item;
    }
}
