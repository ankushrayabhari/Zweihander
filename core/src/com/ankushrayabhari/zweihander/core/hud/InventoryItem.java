package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.items.Item;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class InventoryItem extends Actor {
    private float WidthConversion, HeightConversion;
    private Item item;
    public InventoryItem(Item item) {
        this.item = item;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        WidthConversion = this.getWidth()/10;
        HeightConversion = this.getHeight()/10;

        batch.draw(item.getIcon(), this.getX()+1*WidthConversion, this.getY()+1*HeightConversion, 8*WidthConversion, 8*HeightConversion);
    }
}
