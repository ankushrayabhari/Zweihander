package com.ankushrayabhari.zweihander.items.armor;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.items.ItemDef;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class SampleArmor extends Armor {
    public SampleArmor() {
        super(new ItemDef("Armor", "A sample piece of armor", new TextureRegion(Assets.getTex("textures/lofi_obj_packA.png"), 0, 56, 8, 8), 0, 5, 0, 0, 0));
    }
}
