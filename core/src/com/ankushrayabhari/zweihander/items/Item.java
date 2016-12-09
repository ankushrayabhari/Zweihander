package com.ankushrayabhari.zweihander.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {
	private String name;
	private String description;
	private TextureRegion icon;
	
	public Item(String name, String description, TextureRegion icon) {
		this.name = name;
		this.description = description;
		this.icon = icon;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public TextureRegion getIcon() {
		return icon;
	}
}
