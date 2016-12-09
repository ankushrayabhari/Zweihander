package com.ankushrayabhari.zweihander.map.generation;
import com.ankushrayabhari.zweihander.core.Assets;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public enum BiomeData {
	OCEAN("oceanwater.png", 0.267f, 0.267f, 0.478f), 
	LAKE("lakewater.png", 0.2f, 0.4f, 0.6f), 
	BEACH("darksand.png", 0.627f, 0.565f, 0.467f), 
	SNOW("scorched.png", 1.0f, 1.0f, 1.0f),
    TUNDRA("darkforestgreen.png", 0.733f, 0.733f, 0.667f), 
    BARE("shrubland.png", 0.533f, 0.533f, 0.533f), 
    SCORCHED("scorched.png", 0.333f, 0.333f, 0.333f), 
    TAIGA("darkforestgreen.png", 0.6f, 0.667f, 0.467f),
    SHRUBLAND("shrubland.png", 0.533f, 0.6f, 0.467f), 
    TEMPERATE_DESERT("lightsand.png", 0.788f, 0.824f, 0.608f),
    TEMPERATE_RAIN_FOREST("forestgreen.png", 0.267f, 0.533f, 0.333f), 
    TEMPERATE_DECIDUOUS_FOREST("forestgreen.png", 0.404f, 0.580f, 0.349f),
    GRASSLAND("darkgreen.png", 0.533f, 0.667f, 0.333f), 
    SUBTROPICAL_DESERT("lightsand.png", 0.824f, 0.725f, 0.545f),
    ICE("scorched.png", 0.64f, 1.0f, 1.0f), 
    MARSH("mediumgreen.png", 0.6f, 1.0f, 1.0f), 
    TROPICAL_RAIN_FOREST("verydarkgreen.png", 0.2f, 0.467f, 0.333f),
    TROPICAL_SEASONAL_FOREST("mediumgreen.png", 0.333f, 0.6f, 0.267f), 
    COAST("darksand.png", 0.2f, 0.2f, 0.353f),
    LAKESHORE("darksand.png", 0.135f, 0.333f, 0.533f), 
    RIVER("lakewater.png", 0.133f, 0.333f, 0.533f);
    
	public final Texture texture;
	public final Color color;

    BiomeData(String location, float r, float g, float b) {
    	this.texture = Assets.getTex("textures/map/"+location);
    	this.texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
    	this.color = new Color(r, g, b, 0.65f);
    }
}
