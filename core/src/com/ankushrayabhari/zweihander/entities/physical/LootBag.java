package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.items.Item;
import com.ankushrayabhari.zweihander.items.ItemFactory;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by ankushrayabhari on 5/19/17.
 */

public class LootBag extends PhysicalEntity {
    Sprite sprite;
    private final int ItemLimit = 12;
    private ArrayList<Item> items;
    float timer;
    Vector2 pos;

    public LootBag(GameScreen game, Vector2 position, ArrayList<Item> initialItems) {
        super(game, 10, false, Constants.PhysicalEntityTypes.LOOTBAG, position, new Vector2(1.5f, 1.5f), 0, true);
        items = new ArrayList<Item>(ItemLimit);

        for(int i = 0; i < Math.min(initialItems.size(), ItemLimit); i++) {
            items.add(ItemFactory.createItem(game, initialItems.get(i).getId(), initialItems.get(i).getType()));
        }

        timer = 0;

        pos = new Vector2(position);

        sprite = new Sprite(new TextureRegion(Assets.getTex("textures/lofi_obj.png"), 0, 0, 8, 8));
        sprite.setBounds((float) this.getBody().getPosition().x-0.75f, (float) this.getBody().getPosition().y-0.75f, 1.5f, 1.5f);
    }

    @Override
    public void update(float delta) {
        if(timer >= 150 || items.isEmpty()) this.setDead();
        timer += delta;

        this.getBody().setLinearVelocity(0,0);
        this.getBody().setTransform(pos, 0);
    };

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void onCollide(PhysicalEntity entity) {
        this.getGame().getPlayer().setLootBag(this);
        this.getGame().getHud().setLootBagDisplay(true);
    }

    @Override
    public void endCollide(PhysicalEntity entity) {
        this.getGame().getPlayer().setLootBag(null);
        this.getGame().getHud().setLootBagDisplay(false);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean addItem(Item item) {
        if(items.size() == ItemLimit) {
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
}
