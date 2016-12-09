package com.ankushrayabhari.zweihander.screens;

import com.ankushrayabhari.zweihander.Zweihander;
import com.ankushrayabhari.zweihander.core.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MainMenuScreen implements Screen {
	private Zweihander game;
	private Stage stage = null;
	private Table table, buttonTable;
	private TextButton playButton, creditsButton, exitButton, optionsButton;
	private BitmapFont white;
	private Label title;
	private TextButtonStyle buttonStyle;
	private LabelStyle labelStyle;
	private Image titleImage;
	private Texture titleTexture;
	
	public MainMenuScreen(Zweihander mygame) {
		this.game = mygame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		table.setFillParent(true);
		table.setBounds(0, 0, width, height);
		table.invalidateHierarchy();
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		white = Assets.getFont("ui/white.fnt");

		stage = new Stage();
		
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.center().top().pad(50).setFillParent(true);
		buttonTable = new Table();		
		
		//Button Styling
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = white;
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.checkedFontColor = Color.WHITE;
		buttonStyle.downFontColor = Color.ORANGE;
		buttonStyle.overFontColor = Color.ORANGE;
		buttonStyle.checkedOverFontColor = Color.ORANGE;
		buttonStyle.pressedOffsetX = 2;
		buttonStyle.pressedOffsetY = -2;

		playButton = new TextButton("Play", buttonStyle);
		playButton.pad(20);
		creditsButton = new TextButton("Credits", buttonStyle);
		creditsButton.pad(20);
		optionsButton = new TextButton("Options", buttonStyle);
		optionsButton.pad(20);
		exitButton = new TextButton("Exit", buttonStyle);
		exitButton.pad(20);
		
		//Button Functionality
		playButton.addListener(new ChangeListener(){
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.playScreen);
			}
			
		});
		
		creditsButton.addListener(new ChangeListener(){
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.creditsScreen);
			}
			
		});
		
		optionsButton.addListener(new ChangeListener(){
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.optionScreen);
			}
			
		});
		
		exitButton.addListener(new ChangeListener(){
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();				
			}		
			
		});
		
		//Heading Styling
		labelStyle = new LabelStyle(white, Color.WHITE);
		title = new Label("Zw\u00EBihander", labelStyle);
		title.setFontScale(7);
		
		//Image
		titleTexture = new Texture(Gdx.files.internal("textures/title.png"));
		titleImage = new Image(titleTexture);
		titleImage.setScale(2);
		titleImage.setOrigin(titleImage.getWidth()/2, titleImage.getHeight()/2);
		
		//Table Layout
		buttonTable.add(playButton).uniform();
		buttonTable.add(optionsButton).uniform();
		buttonTable.add(creditsButton).uniform();
		buttonTable.add(exitButton).uniform();
		
		table.add(title);
		table.row();
		table.add(titleImage).center().expand();
		table.row();
		table.add(buttonTable);

		//Add all Actors
		stage.addActor(table);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		if(stage != null) stage.dispose();
	}

}
