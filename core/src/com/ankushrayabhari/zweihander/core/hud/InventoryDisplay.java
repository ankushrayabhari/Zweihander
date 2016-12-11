package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.ListIterator;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class InventoryDisplay extends Table {
    private float WidthConversion, HeightConversion;
    private Texture texture;
    private GameScreen game;

    public InventoryDisplay(GameScreen game, float x, float y, float width, float height) {
        this.setBounds(x, y, width, height);
        this.game = game;
        texture = Assets.getTex("textures/lofi_halls.png");
        refreshItemDisplay();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        WidthConversion = this.getWidth()/55;
        HeightConversion = this.getHeight()/31;
        //Background
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 9, 601, 55, 31, false, false);
        for(int i = 0; i < 4; i++) {
            batch.draw(texture, this.getX()+(this.getWidth()/4*i)+2*WidthConversion, this.getY()+this.getHeight()-12.5f*HeightConversion, 10*WidthConversion, 10*HeightConversion, 64, 530, 10, 10, false, false);
        }
        for(int i = 0; i < 4; i++) {
            batch.draw(texture, this.getX()+(this.getWidth()/4*i)+2*WidthConversion, this.getY()+this.getHeight()-28*HeightConversion, 10*WidthConversion, 10*HeightConversion, 64, 530, 10, 10, false, false);
        }

        super.draw(batch, parentAlpha);
    }

    public void refreshItemDisplay() {
        WidthConversion = this.getWidth()/55;
        HeightConversion = this.getHeight()/31;
        this.clearChildren();
        ListIterator<Item> iterator = this.game.getPlayer().getInventory().iterator();
        int count = 0;
        while(iterator.hasNext()) {
            if(count == 4) {
                this.row();
            }

            this.add(new ItemDisplay(game, iterator.next())).width(9*WidthConversion).height(9*HeightConversion).expand().center();
            count++;
        }
    }
}
