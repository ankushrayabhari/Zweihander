package com.ankushrayabhari.zweihander.core;

import com.ankushrayabhari.zweihander.entities.physical.PhysicalEntity;

/**
 * Created by ankushrayabhari on 12/3/15.
 */

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    	
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        PhysicalEntity eA = (PhysicalEntity) contact.getFixtureA().getBody().getUserData();
        PhysicalEntity eB = (PhysicalEntity) contact.getFixtureB().getBody().getUserData();
        if(eA != null) eA.onCollide(eB);
        if(eB != null) eB.onCollide(eA);
    }
}