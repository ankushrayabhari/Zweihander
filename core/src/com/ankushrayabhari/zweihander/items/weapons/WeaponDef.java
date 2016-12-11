package com.ankushrayabhari.zweihander.items.weapons;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.items.ItemDef;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class WeaponDef extends ItemDef {

    private float delay;
    private Action action;

    public void setDelay(float delay) {
        this.delay = delay;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    public float getDelay() { return delay; }
    public Action getAction() { return action; }
}
