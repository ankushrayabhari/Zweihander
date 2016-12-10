package com.ankushrayabhari.zweihander.entities.physical;

import com.ankushrayabhari.zweihander.core.Assets;
import com.ankushrayabhari.zweihander.core.Constants.DIRECTION;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

/**
 * Class Description
 *
 * @author Ankush Rayabhari
 */
public class WalkAnimation {
    private Texture atlas;
    private TextureRegion[][] frames, waterFrames;
    private AnimatedSprite upSprite, downSprite, leftSprite, rightSprite, waterUpSprite, waterDownSprite, waterLeftSprite, waterRightSprite;
    private boolean enemy;

    public WalkAnimation(boolean enemy, int row, float duration) {
        atlas = Assets.getTex("textures/spriteSheet.png");
        int column = !enemy ? 8 : 128; 
        
        frames = new TextureRegion[4][3];
        
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
            	frames[i][j] = new TextureRegion(atlas,column+24*i+8*j, 24+8*row, 8, 8);
            }
        }
        if(!enemy) {
        	waterFrames = new TextureRegion[4][3];
        	for(int i = 0; i < 4; i++) {
            	for(int j = 0; j < 3; j++) {
            		waterFrames[i][j] = new TextureRegion(atlas, column+24*i+8*j, 24+8*row, 8, 6);
            	}
            }
        	waterRightSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(waterFrames[0]), PlayMode.LOOP));
            waterDownSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(waterFrames[1]), PlayMode.LOOP));
            waterLeftSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(waterFrames[2]), PlayMode.LOOP));
            waterUpSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(waterFrames[3]), PlayMode.LOOP));
        }


        rightSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(frames[0]), PlayMode.LOOP));
        downSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(frames[1]), PlayMode.LOOP));
        leftSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(frames[2]), PlayMode.LOOP));
        upSprite = new AnimatedSprite(new Animation(duration, new Array<TextureRegion>(frames[3]), PlayMode.LOOP));
    }

    public void setDuration(float duration) {
        upSprite.getAnimation().setFrameDuration(duration);
        downSprite.getAnimation().setFrameDuration(duration);
        leftSprite.getAnimation().setFrameDuration(duration);
        rightSprite.getAnimation().setFrameDuration(duration);
        if(!enemy) {
            waterUpSprite.getAnimation().setFrameDuration(duration);
            waterDownSprite.getAnimation().setFrameDuration(duration);
            waterLeftSprite.getAnimation().setFrameDuration(duration);
            waterRightSprite.getAnimation().setFrameDuration(duration);
        }
    }

    public AnimatedSprite getMovingSprite(DIRECTION direction, boolean water) {
    	AnimatedSprite sprite = null;
        switch (direction) {
            case RIGHT:
                sprite = water ? waterRightSprite : rightSprite;
                break;
            case DOWN:
                sprite = water ? waterDownSprite : downSprite;
                break;
            case LEFT:
                sprite = water ? waterLeftSprite : leftSprite;
                break;
            case UP:
                sprite = water ? waterUpSprite : upSprite;
                break;
        }
        return sprite;
    }
    
    public AnimatedSprite getStaticSprite(DIRECTION direction, boolean water) {
        AnimatedSprite sprite = getMovingSprite(direction, water);
        sprite.setTime(0);
        return sprite;
    }
}
