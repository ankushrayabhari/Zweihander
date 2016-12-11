package com.ankushrayabhari.zweihander.items.abilities;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.items.ItemDef;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class AbilityDef extends ItemDef {
    private int manaCost;
    private float delay;
    private Action action;

    public int getManaCost() {
        return manaCost;
    }
    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }
    public void setDelay(float delay) {
        this.delay = delay;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    public float getDelay() { return delay; }
    public Action getAction() { return action; }
}
