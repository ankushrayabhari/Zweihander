package com.ankushrayabhari.zweihander.items.abilities;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.items.abilities.action.TomeAction;
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
public class AbilityFactory {
    private static ArrayList<AbilityDef> abilityDefs;
    private static Comparator<AbilityDef> comparator;

    static {
        abilityDefs = new ArrayList<AbilityDef>(8);
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal("items/abilities.json"));
        for (JsonValue component : base.get("abilities"))
        {
            AbilityDef definition = new AbilityDef();
            definition.setId(component.getInt("id"));
            definition.setName(component.getString("name"));

            JsonValue texture = component.get("texture");
            definition.setIcon(new TextureRegion(Assets.getTex(texture.getString("file")), texture.getInt("x"), texture.getInt("y"), texture.getInt("width"), texture.getInt("height")));

            definition.setManaCost(component.getInt("manaCost"));
            definition.setDelay(component.getFloat("delay"));
            definition.setHealthBonus(component.getInt("hpBonus"));
            definition.setManaBonus(component.getInt("manaBonus"));
            definition.setHealthBonus(component.getInt("attackBonus"));
            definition.setHealthBonus(component.getInt("defenseBonus"));
            definition.setHealthBonus(component.getInt("speedBonus"));

            String action = component.getString("action");
            if(action.equals("TomeAction")) {
                definition.setAction(new TomeAction(component.getInt("hpHeal")));
            }

            abilityDefs.add(definition);
        }
        comparator = new Comparator<AbilityDef>() {
            @Override
            public int compare(AbilityDef o1, AbilityDef o2) {
                return o1.getId()-o2.getId();
            }
        };
        Collections.sort(abilityDefs, comparator);
    }

    public static Ability createAbility(GameScreen game, int id) {
        AbilityDef comparable = new AbilityDef();
        comparable.setId(id);

        int index = Collections.binarySearch(abilityDefs, comparable, comparator);

        if(index >= 0) {
            return new Ability(game, abilityDefs.get(index));
        }
        else {
            return null;
        }
    }
}
