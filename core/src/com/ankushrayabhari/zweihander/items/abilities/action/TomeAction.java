package com.ankushrayabhari.zweihander.items.abilities.action;

import com.ankushrayabhari.zweihander.items.Action;
import com.ankushrayabhari.zweihander.screens.GameScreen;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class TomeAction implements Action {
    private int hpHeal;

    public TomeAction(int hpHeal) {
        this.hpHeal = hpHeal;
    }

    @Override
    public void execute(GameScreen game) {
        game.getPlayer().addHealth(hpHeal);
    }
}
