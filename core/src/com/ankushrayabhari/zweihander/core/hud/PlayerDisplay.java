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
        HeightConversion = this.getHeight()/31/1.5f;
        //Background
        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 9, 601, 55, 31, false, false);

        //Active Item slots
        for(int i = 0; i < 4; i++) {
            batch.draw(texture, this.getX()+(this.getWidth()/4*i)+2*WidthConversion, this.getY()+this.getHeight()-43.5f*HeightConversion, 10*WidthConversion, 10*HeightConversion, 64, 530, 10, 10, false, false);
        }

        //Active Equipment
        batch.draw(game.getPlayer().getInventory().getActiveWeapon().getIcon(), this.getX() + (this.getWidth() / 4 * 0) + 3 * WidthConversion, this.getY() + this.getHeight() - 42.5f * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);
        batch.draw(game.getPlayer().getInventory().getActiveAbility().getIcon(), this.getX() + (this.getWidth() / 4 * 1) + 3 * WidthConversion, this.getY() + this.getHeight() - 42.5f * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);
        batch.draw(game.getPlayer().getInventory().getActiveArmor().getIcon(), this.getX() + (this.getWidth() / 4 * 2) + 3 * WidthConversion, this.getY() + this.getHeight() - 42.5f * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);
        batch.draw(game.getPlayer().getInventory().getActiveRing().getIcon(), this.getX() + (this.getWidth() / 4 * 3) + 3 * WidthConversion, this.getY() + this.getHeight() - 42.5f * HeightConversion, 8 * WidthConversion, 8 * HeightConversion);

        //Level
        font.setColor(Color.GRAY);
        String level = Integer.toString(game.getPlayer().getLevel());
        font.getData().setScale(0.75f);
        font.draw(batch, "Level", this.getX() + 2 * WidthConversion, this.getY() + this.getHeight() - 4.5f * HeightConversion, 0, 5, 11 * WidthConversion, Align.center, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, level, this.getX() + 2 * WidthConversion, this.getY() + this.getHeight() - 9.5f * HeightConversion, 0, level.length(), 11 * WidthConversion, Align.center, false, null);

        //xp bar
        font.setColor(Color.WHITE);
        batch.draw(texture, this.getX() + 15 * WidthConversion, this.getY() + this.getHeight() - 5 * HeightConversion, 37 * WidthConversion, 4 * HeightConversion, 53, 561, 21, 2, false, false);
        batch.draw(texture, this.getX() + 15 * WidthConversion, this.getY() + this.getHeight() - 5 * HeightConversion, 37 * game.getPlayer().getXpPercentage() * WidthConversion, 4 * HeightConversion, 31, 561, 21, 2, false, false);
        String xpText = Integer.toString((int) Math.floor(game.getPlayer().getXpPercentage()*game.getPlayer().getMaxXp())) + "/" + Integer.toString(game.getPlayer().getMaxXp());
        font.getData().setScale(0.65f);
        font.draw(batch, xpText, this.getX() + 30f * WidthConversion, this.getY() + this.getHeight() - 1.5f * HeightConversion, 0, xpText.length(), 7 * WidthConversion, Align.center, false, null);

        //health bar
        batch.draw(texture, this.getX() + 15 * WidthConversion, this.getY() + this.getHeight() - 10f * HeightConversion, 37 * WidthConversion, 4 * HeightConversion, 53, 553, 21, 2, false, false);
        batch.draw(texture, this.getX() + 15 * WidthConversion, this.getY() + this.getHeight() - 10f * HeightConversion, 37 * game.getPlayer().getHealthPercentage() * WidthConversion, 4 * HeightConversion, 31, 553, 21, 2, false, false);
        String hpText = Integer.toString((int) Math.floor(game.getPlayer().getHealthPercentage()*game.getPlayer().getMaxHealth())) + "/" + Integer.toString(game.getPlayer().getMaxHealth());
        font.draw(batch, hpText, this.getX() + 30f * WidthConversion, this.getY() + this.getHeight() - 6.5f * HeightConversion, 0, hpText.length(), 7*WidthConversion, Align.center, false, null);

        //mana bar
        batch.draw(texture, this.getX() + 15 * WidthConversion, this.getY() + this.getHeight() - 15 * HeightConversion, 37 * WidthConversion, 4 * HeightConversion, 53, 557, 21, 2, false, false);
        batch.draw(texture, this.getX() + 15 * WidthConversion, this.getY() + this.getHeight() - 15 * HeightConversion, 37 * game.getPlayer().getManaPercentage() * WidthConversion, 4 * HeightConversion, 31, 557, 21, 2, false, false);
        String manaText = Integer.toString((int) Math.floor(game.getPlayer().getManaPercentage()*game.getPlayer().getMaxMana())) + "/" + Integer.toString(game.getPlayer().getMaxMana());
        font.draw(batch, hpText, this.getX() + 30f * WidthConversion, this.getY() + this.getHeight() - 11.5f * HeightConversion, 0, manaText.length(), 7*WidthConversion, Align.center, false, null);

        //stats
        String atk = Integer.toString(game.getPlayer().getAttack());
        String def = Integer.toString(game.getPlayer().getDefense());
        String dex = Integer.toString(game.getPlayer().getDexterity());
        String spd = Integer.toString(game.getPlayer().getSpeed());
        String wis = Integer.toString(game.getPlayer().getWisdom());
        String vit = Integer.toString(game.getPlayer().getVitality());

        font.getData().setScale(0.75f);
        font.setColor(Color.GRAY);
        font.draw(batch, "ATK", this.getX() + 3 * WidthConversion, this.getY() + this.getHeight() - 19 * HeightConversion, 0, 3, 5 * WidthConversion, Align.left, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, atk, this.getX() + 12 * WidthConversion, this.getY() + this.getHeight() - 19 * HeightConversion, 0, atk.length(), 5 * WidthConversion, Align.left, false, null);

        font.setColor(Color.GRAY);
        font.draw(batch, "DEF", this.getX() + 20.5f * WidthConversion, this.getY() + this.getHeight() - 19 * HeightConversion, 0, 3, 5 * WidthConversion, Align.left, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, def, this.getX() + 29.5f * WidthConversion, this.getY() + this.getHeight() - 19 * HeightConversion, 0, def.length(), 5 * WidthConversion, Align.left, false, null);

        font.setColor(Color.GRAY);
        font.draw(batch, "DEX", this.getX() + 38 * WidthConversion, this.getY() + this.getHeight() - 19 * HeightConversion, 0, 3, 5 * WidthConversion, Align.left, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, dex, this.getX() + 47 * WidthConversion, this.getY() + this.getHeight() - 19 * HeightConversion, 0, dex.length(), 5 * WidthConversion, Align.left, false, null);

        font.setColor(Color.GRAY);
        font.draw(batch, "VIT", this.getX() + 3 * WidthConversion, this.getY() + this.getHeight() - 27 * HeightConversion, 0, 3, 5 * WidthConversion, Align.left, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, vit, this.getX() + 12 * WidthConversion, this.getY() + this.getHeight() - 27 * HeightConversion, 0, vit.length(), 5 * WidthConversion, Align.left, false, null);

        font.setColor(Color.GRAY);
        font.draw(batch, "WIS", this.getX() + 20.5f * WidthConversion, this.getY() + this.getHeight() - 27 * HeightConversion, 0, 3, 5 * WidthConversion, Align.left, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, wis, this.getX() + 29.5f * WidthConversion, this.getY() + this.getHeight() - 27 * HeightConversion, 0, wis.length(), 5 * WidthConversion, Align.left, false, null);

        font.setColor(Color.GRAY);
        font.draw(batch, "SPD", this.getX() + 38 * WidthConversion, this.getY() + this.getHeight() - 27 * HeightConversion, 0, 3, 5 * WidthConversion, Align.left, false, null);
        font.setColor(Color.LIGHT_GRAY);
        font.draw(batch, spd, this.getX() + 47 * WidthConversion, this.getY() + this.getHeight() - 27 * HeightConversion, 0, spd.length(), 5 * WidthConversion, Align.left, false, null);
    }
}
