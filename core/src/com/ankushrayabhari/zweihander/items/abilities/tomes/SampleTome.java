package com.ankushrayabhari.zweihander.items.abilities.tomes;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class SampleTome extends Tome {
    public SampleTome(GameScreen game) {
        super(game, 0.3f, 30, 30, "Tome", "A sample tome.", new TextureRegion(Assets.getTex("textures/lofi_obj_packA.png"), 0, 16, 8, 8), 0, 0, 0, 0, 0);
    }
}
