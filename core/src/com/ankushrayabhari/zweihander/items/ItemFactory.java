package com.ankushrayabhari.zweihander.items;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.items.abilities.Ability;
import com.ankushrayabhari.zweihander.items.abilities.AbilityDef;
import com.ankushrayabhari.zweihander.items.abilities.actions.TomeAction;
import com.ankushrayabhari.zweihander.items.misc.Armor;
import com.ankushrayabhari.zweihander.items.misc.Potion;
import com.ankushrayabhari.zweihander.items.misc.PotionDef;
import com.ankushrayabhari.zweihander.items.misc.Ring;
import com.ankushrayabhari.zweihander.items.weapons.Weapon;
import com.ankushrayabhari.zweihander.items.weapons.WeaponDef;
import com.ankushrayabhari.zweihander.items.weapons.actions.WandFireAction;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class ItemFactory {
    private static ArrayList<ItemDef> itemDefs;
    private static Comparator<ItemDef> comparator;

    static {
        itemDefs = new ArrayList<ItemDef>(20);
        JsonReader json = new JsonReader();

        populateWeapons(json);
        populateAbilities(json);
        populatePotions(json);
        populateItem(json, "items/armors.json", "armors");
        populateItem(json, "items/rings.json", "rings");

        comparator = new Comparator<ItemDef>() {
            @Override
            public int compare(ItemDef o1, ItemDef o2) {
                return o1.getId()-o2.getId();
            }
        };
        Collections.sort(itemDefs, comparator);
    }

    public static enum ItemTypes {
        Weapon, Ability, Armor, Ring, Potion
    }

    public static Item createItem(GameScreen game, int id, ItemTypes itemType) {
        ItemDef comparable = new ItemDef();
        comparable.setId(id);

        int index = Collections.binarySearch(itemDefs, comparable, comparator);

        if(index >= 0) {
            switch(itemType) {
                case Potion:
                    return new Potion((PotionDef) itemDefs.get(index));
                case Weapon:
                    return new Weapon(game, (WeaponDef) itemDefs.get(index));
                case Ability:
                    return new Ability(game, (AbilityDef) itemDefs.get(index));
                case Armor:
                    return new Armor((ItemDef) itemDefs.get(index));
                default:
                case Ring:
                    return new Ring((ItemDef) itemDefs.get(index));
            }
        }
        else {
            return null;
        }
    }

    private static void populateWeapons(JsonReader json) {
        JsonValue base = json.parse(Gdx.files.internal("items/weapons.json"));
        for (JsonValue component : base.get("weapons"))
        {
            WeaponDef definition = new WeaponDef();
            ItemFactory.setFields(definition, component);

            definition.setDelay(component.getFloat("delay"));
            String action = component.getString("action");
            if(action.equals("WandFireAction")) {
                definition.setAction(new WandFireAction(component.getInt("damage")));
            }
            else if (action.equals("SwordFireAction")) {
                definition.setAction(new WandFireAction(component.getInt("damage")));
            }
            itemDefs.add(definition);
        }
    }
    private static void populateAbilities(JsonReader json) {
        JsonValue base = json.parse(Gdx.files.internal("items/abilities.json"));
        for (JsonValue component : base.get("abilities"))
        {
            AbilityDef definition = new AbilityDef();
            ItemFactory.setFields(definition, component);
            definition.setManaCost(component.getInt("manaCost"));
            definition.setDelay(component.getFloat("delay"));

            String action = component.getString("action");
            if(action.equals("TomeAction")) {
                definition.setAction(new TomeAction(component.getInt("hpHeal")));
            }

            itemDefs.add(definition);
        }
    }
    private static void populatePotions(JsonReader json) {
        JsonValue base = json.parse(Gdx.files.internal("items/potions.json"));
        for (JsonValue component : base.get("potions"))
        {
            PotionDef definition = new PotionDef();
            ItemFactory.setFields(definition, component);
            definition.setHealAmount(component.getInt("healAmount"));
            definition.setManaHealAmount(component.getInt("manaHealAmount"));
            itemDefs.add(definition);
        }
    }
    private static void populateItem(JsonReader json, String fileName, String section) {
        JsonValue base = json.parse(Gdx.files.internal(fileName));
        for (JsonValue component : base.get(section))
        {
            ItemDef definition = new ItemDef();
            ItemFactory.setFields(definition, component);
            itemDefs.add(definition);
        }
    }
    private static void setFields(ItemDef itemDef, JsonValue component) {
        itemDef.setId(component.getInt("id"));
        itemDef.setName(component.getString("name"));
        itemDef.setHealthBonus(component.getInt("hpBonus"));
        itemDef.setManaBonus(component.getInt("manaBonus"));
        itemDef.setAttackBonus(component.getInt("attackBonus"));
        itemDef.setDefenseBonus(component.getInt("defenseBonus"));
        itemDef.setSpeedBonus(component.getInt("speedBonus"));
        itemDef.setWisdomBonus(component.getInt("wisdomBonus"));
        itemDef.setVitalityBonus(component.getInt("vitalityBonus"));
        itemDef.setDexterityBonus(component.getInt("dexterityBonus"));

        JsonValue texture = component.get("texture");
        itemDef.setIcon(new TextureRegion(Assets.getTex(texture.getString("file")), texture.getInt("x"), texture.getInt("y"), texture.getInt("width"), texture.getInt("height")));
    }
}
