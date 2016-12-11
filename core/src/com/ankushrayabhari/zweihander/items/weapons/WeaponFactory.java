package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.core.Assets;
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
public class WeaponFactory {
    private static ArrayList<WeaponDef> weaponDefs;
    private static Comparator<WeaponDef> comparator;

    static {
        weaponDefs = new ArrayList<WeaponDef>(8);
        JsonReader json = new JsonReader();
        JsonValue base = json.parse(Gdx.files.internal("items/weapons.json"));
        for (JsonValue component : base.get("weapons"))
        {
            WeaponDef definition = new WeaponDef();
            definition.setId(component.getInt("id"));
            definition.setName(component.getString("name"));

            JsonValue texture = component.get("texture");
            definition.setIcon(new TextureRegion(Assets.getTex(texture.getString("file")), texture.getInt("x"), texture.getInt("y"), texture.getInt("width"), texture.getInt("height")));

            definition.setDelay(component.getFloat("delay"));
            definition.setHealthBonus(component.getInt("hpBonus"));
            definition.setManaBonus(component.getInt("manaBonus"));
            definition.setHealthBonus(component.getInt("attackBonus"));
            definition.setHealthBonus(component.getInt("defenseBonus"));
            definition.setHealthBonus(component.getInt("speedBonus"));

            String action = component.getString("action");
            if(action.equals("WandFireAction")) {
                definition.setAction(new WandFireAction());
            }
            else if (action.equals("SwordFireAction")) {
                definition.setAction(new WandFireAction());
            }

            weaponDefs.add(definition);
        }
        comparator = new Comparator<WeaponDef>() {
            @Override
            public int compare(WeaponDef o1, WeaponDef o2) {
                return o1.getId()-o2.getId();
            }
        };
        Collections.sort(weaponDefs, comparator);
    }

    public static Weapon createWeapon(GameScreen game, int id) {
        WeaponDef comparable = new WeaponDef();
        comparable.setId(id);

        int index = Collections.binarySearch(weaponDefs, comparable, comparator);

        if(index >= 0) {
            return new Weapon(game, weaponDefs.get(index));
        }
        else {
            return null;
        }
    }
}
