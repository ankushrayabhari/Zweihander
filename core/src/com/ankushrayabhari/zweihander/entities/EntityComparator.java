package com.ankushrayabhari.zweihander.entities;

import java.util.Comparator;

/**
 * Compares entities by z-index
 *
 * @author Ankush Rayabhari
 */
public class EntityComparator implements Comparator<Entity> {
    @Override
    public int compare(Entity entity1, Entity entity2) {
        return entity1.getzIndex()-entity2.getzIndex();
    }
}
