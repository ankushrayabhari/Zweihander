package com.ankushrayabhari.zweihander.core.hud;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class PlayerDisplay extends Actor {
    private GameScreen game;
    private BitmapFont font;
    private Texture texture;
    private float WidthConversion, HeightConversion;

    public PlayerDisplay(GameScreen game) {
        this.game = game;
        font = Assets.getFont("ui/white.fnt");
        texture = Assets.getTex("textures/lofi_halls.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        WidthConversion = this.getWidth()/55;
        HeightConversion = this.getHeight()/31;
        //Background
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 9, 569, 55, 31, false, false);

        //Active Equipment
        batch.draw(game.getPlayer().getInventory().getActiveWeapon().getIcon(), this.getX() + 4 * WidthConversion, this.getY() + this.getHeight() - 10 * HeightConversion, 6 * WidthConversion, 6 * HeightConversion);
        batch.draw(game.getPlayer().getInventory().getActiveAbility().getIcon(), this.getX() + 3 * WidthConversion, this.getY() + this.getHeight() - 28 * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);
        batch.draw(game.getPlayer().getInventory().getActiveArmor().getIcon(), this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 28 * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);
        batch.draw(game.getPlayer().getInventory().getActiveRing().getIcon(), this.getX() + 25 * WidthConversion, this.getY() + this.getHeight() - 28 * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);

        //Level
        font.setColor(Color.LIGHT_GRAY);
        String level = Integer.toString(game.getPlayer().getLevel());
        font.getData().setScale(1);
        font.draw(batch, level, this.getX() + 13 * WidthConversion, this.getY() + this.getHeight() - 13 * HeightConversion, 0, level.length(), 12 * WidthConversion, Align.left, false, null);

        //xp bar
        batch.draw(texture, this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 4 * HeightConversion, 24 * WidthConversion, 2 * HeightConversion, 53, 561, 21, 2, false, false);
        batch.draw(texture, this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 4 * HeightConversion, 24*WidthConversion, 2*HeightConversion, 31, 561, 21, 2, false, false);

        //health bar
        batch.draw(texture, this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 7.5f * HeightConversion, 24 * WidthConversion, 2 * HeightConversion, 53, 553, 21, 2, false, false);
        batch.draw(texture, this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 7.5f * HeightConversion, game.getPlayer().getHealthPercentage()*24*WidthConversion, 2*HeightConversion, 31, 553, 21, 2, false, false);

        //mana bar
        batch.draw(texture, this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 11 * HeightConversion, 24*WidthConversion, 2*HeightConversion, 53, 557, 21, 2, false, false);
        batch.draw(texture, this.getX() + 14 * WidthConversion, this.getY() + this.getHeight() - 11 * HeightConversion, game.getPlayer().getManaPercentage() * 24 * WidthConversion, 2 * HeightConversion, 31, 557, 21, 2, false, false);

        //stats
        String attack = Integer.toString(game.getPlayer().getAttack());
        String defense = Integer.toString(game.getPlayer().getDefense());
        String speed = Integer.toString(game.getPlayer().getSpeed());
        font.getData().setScale(0.8f);
        font.draw(batch, attack, this.getX() + 35 * WidthConversion, this.getY() + this.getHeight() - 6.5f * HeightConversion, 0, attack.length(), 9 * WidthConversion, Align.right, false, null);
        font.draw(batch, defense, this.getX() + 35 * WidthConversion, this.getY() + this.getHeight() - 14.5f * HeightConversion, 0, attack.length(), 9 * WidthConversion, Align.right, false, null);
        font.draw(batch, speed, this.getX() + 35 * WidthConversion, this.getY() + this.getHeight() - 22.5f * HeightConversion, 0, attack.length(), 9 * WidthConversion, Align.right, false, null);

    }
}
