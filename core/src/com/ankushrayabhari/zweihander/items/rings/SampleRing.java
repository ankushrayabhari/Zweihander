package com.ankushrayabhari.zweihander.items.rings;

import com.ankushrayabhari.zweihander.core.Assets;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class SampleRing extends Ring {
    public SampleRing() {
        super("Ring", "A sample ring", new TextureRegion(Assets.getTex("textures/lofi_obj.png"), 89, 16, 7, 8), 0, 1, 0, 0, 0);
    }
}

