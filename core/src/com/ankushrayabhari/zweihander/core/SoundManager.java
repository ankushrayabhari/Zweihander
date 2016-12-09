package com.ankushrayabhari.zweihander.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entries;

public class SoundManager {
	static ObjectMap<String, Sound> sounds;
	static ObjectMap<String, Music> musics;
	static Array<Music> cache;

	public static boolean play = true;
	public static boolean paused = false;

	public static void init() {
		sounds = new ObjectMap<String, Sound>();
		musics = new ObjectMap<String, Music>();
		cache = new Array<Music>();
	}

	public static void play(String name) {
		if (play && !paused)
			((Sound) sounds.get(name)).play();
	}

	public static void playMusic(String name) {
		if (((Music) musics.get(name)).getVolume() == 0) {
			((Music) musics.get(name)).setVolume(1f);
		}
		if (play && !paused) {
			((Music) musics.get(name)).play();
		}
	}

	public static void stopMusic(String name) {
		if (play)
			((Music) musics.get(name)).stop();
	}

	public static void stop(String name) {
		if (play)
			((Sound) sounds.get(name)).stop();
	}

	public static void play(String name, float volume) {
		if (play)
			((Sound) sounds.get(name)).play(volume);
	}

	public static void playRandom(String name, float volume, float min, float max) {
		if (play)
			((Sound) sounds.get(name)).play(volume, min + MathUtils.random(max - min), 0);
	}

	public static void playMusic(String name, float volume) {
		if (play) {
			Entries<String, Music> entries = musics.entries();
			while (entries.hasNext()) {
				entries.next().value.pause();
			}
			((Music) musics.get(name)).setVolume(volume);
			((Music) musics.get(name)).play();
		}
	}

	public static void loop(String name) {
		if (play && !paused)
			((Sound) sounds.get(name)).loop();
	}

	public static void loop(String name, float vol) {
		if (play && !paused)
			((Sound) sounds.get(name)).loop(vol);
	}

	public static void loadSound(String name, String path) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(name, sound);
	}

	public static void loadMusic(String name, String path) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
		musics.put(name, music);
	}

	public static void pause() {
		Entries<String, Music> entries = musics.entries();
		while (entries.hasNext()) {
			Music music = entries.next().value;
			if (music.isPlaying()) {
				cache.add(music);
				music.pause();
			}
		}
	}

	public static void resume() {
		if (play && !paused) {
			for (int i = 0; i < cache.size; i++) {
				cache.get(i).play();
			}
			cache.clear();
		}
	}

	public static void setPlay(boolean play) {
		SoundManager.play = play;
	}

	public static boolean getPlay() {
		return play;
	}
}
