package com.ankushrayabhari.zweihander.core;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;

/**
 * Class consisting of static methods for loading and caching assets to reduce
 * load times.
 * 
 * @author Austin Hsieh
 */
public class Assets {
	/**
	 * Store textures based on path to image
	 */
	private static HashMap<String, Texture> textureCache = new HashMap<String, Texture>();
	private static Skin skin;
	private static LabelStyle labelStyle;
	private static BitmapFont font;
	
	/**
	 * Returns the default skin
	 * 
	 * @return default skin
	 */
	public static Skin getSkin() {
		if (skin == null) {
			skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		}
		return skin;
	}

	/**
	 * Returns the default label style for UIs.
	 * 
	 * @return default label style
	 */
	public static LabelStyle getLabelStyle() { // replace with whatever default
// font
		if (labelStyle == null) {
			labelStyle = new LabelStyle();
			labelStyle.font = getFont("ui/opensans.fnt");
			labelStyle.fontColor = Color.BLACK;
		}
		return labelStyle;
	}

	/**
	 * Shorthand way to load most textures
	 * 
	 * @param path
	 *            path location of image
	 * @return new or cached texture depending on whether it was already
	 *         requested.
	 */
	public static Texture getTex(String path) {
		return getTex(path, FileType.Internal);
	}

	/**
	 * @param path
	 *            location of image
	 * @param fileType
	 *            internal/local/etc.
	 * @return new or cached texture depending on whether it was already
	 *         requested.
	 */
	public static Texture getTex(String path, FileType fileType) {
		if (textureCache.containsKey(path))
			return textureCache.get(path);

		Texture tex = new Texture(Gdx.files.getFileHandle(path, fileType));
		tex.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		textureCache.put(path, tex);
		return tex;
	}
	
	public static BitmapFont getFont(String path) {
		if(font == null) {
			font = new BitmapFont(Gdx.files.internal(path));
		}
		return font;
	}

}
