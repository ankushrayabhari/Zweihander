package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants;
import com.ankushrayabhari.zweihander.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class StatusMessage extends PhysicalEntity {
    private float distance;
    private BitmapFont font;
    private String message;
    private Color color;
    private PhysicalEntity entity;
    private Vector2 originalPosition;

    public StatusMessage(PhysicalEntity entity, GameScreen game, String message, Color color, Vector2 position) {
        super(game, 75, false, Constants.PhysicalEntityTypes.MESSAGE, position, new Vector2(2,1), 0, false);
        distance = 2;
        this.message = message;
        font = Assets.getFont("ui/white.fnt");
        this.entity = entity;
        this.originalPosition = position;
        this.color = color;
    }

    @Override
    public void update(float delta) {
        this.getBody().setTransform(entity.getBody().getPosition().x, entity.getBody().getPosition().y + entity.getDimensions().y + this.getBody().getPosition().y - originalPosition.y, 0);
        originalPosition.set(entity.getBody().getPosition().x, entity.getBody().getPosition().y + entity.getDimensions().y);

        if(this.getBody().getPosition().dst2(originalPosition) > distance*distance || entity.isDead()) {
            this.setDead();
        }
        else {
            this.getBody().setLinearVelocity(0, 5);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        font.getData().setScale(1f);
        font.setColor(color);

        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Vector3 screenCoordinates = this.getGame().getCamera().project(new Vector3(this.getBody().getPosition(), 0).add(-0.25f, 0.5f, 0));
        System.out.println(screenCoordinates.toString());
        Gdx.app.log("", message);
        font.draw(batch, message, screenCoordinates.x, screenCoordinates.y, 8, Align.center, false);
    }

    public void onCollide(PhysicalEntity entity){}

    @Override
    public void onDeath() {
        this.destroyBody();
    }
}
