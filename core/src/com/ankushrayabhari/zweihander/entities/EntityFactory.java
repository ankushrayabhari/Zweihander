package com.ankushrayabhari.zweihander.entities;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.entities.physical.projectiles.Projectile;
import com.ankushrayabhari.zweihander.entities.physical.projectiles.ProjectileDef;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
public class EntityFactory {
    private static ArrayList<ProjectileDef> projectileDefs;
    private static Comparator<ProjectileDef> projectileDefComparator;

    static {
        projectileDefs = new ArrayList<ProjectileDef>(20);
        JsonReader json = new JsonReader();
        populateProjectiles(json);

        projectileDefComparator = new Comparator<ProjectileDef>() {
            @Override
            public int compare(ProjectileDef o1, ProjectileDef o2) { return o1.getId()-o2.getId(); }
        };

        Collections.sort(projectileDefs, projectileDefComparator);
    }

    public static void spawnProjectile(GameScreen game, int id, Constants.PhysicalEntityTypes type, Vector2 position, Vector2 fireDirection, int damage, int range, int speed) {
        ProjectileDef def = new ProjectileDef();
        def.setId(id);
        int index = Collections.binarySearch(projectileDefs, def, projectileDefComparator);
        game.addEntity(new Projectile(game, projectileDefs.get(index), type, position, fireDirection, damage, range, speed));
    }


    private static void populateProjectiles(JsonReader json) {
        JsonValue base = json.parse(Gdx.files.internal("entities/projectiles/weaponprojectiles.json"));
        for (JsonValue component : base.get("weaponProjectiles"))
        {
            ProjectileDef def = new ProjectileDef();
            def.setId(component.getInt("id"));
            def.setName(component.getString("name"));
            def.setHeight(component.getFloat("height"));
            def.setWidth(component.getFloat("width"));
            JsonValue texture = component.get("texture");
            def.setIcon(new TextureRegion(Assets.getTex(texture.getString("file")), texture.getInt("x"), texture.getInt("y"), texture.getInt("width"), texture.getInt("height")));
            def.setRotation(component.getInt("rotation"));
            projectileDefs.add(def);
        }
    }
}
