package com.ankushrayabhari.zweihander.entities.physical;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public interface Damageable {
    void dealHealth(float amount);
    void addHealth(float amount);
    int getMaxHealth();
    float getHealthPercentage();
    float getHealth();
    int getDefense();
    void setDefense(int amount);
}
