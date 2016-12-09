package com.ankushrayabhari.zweihander.screens;

import com.ankushrayabhari.zweihander.Zweihander;
import com.ankushrayabhari.zweihander.core.Assets;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PlayScreen implements Screen {
	Zweihander game;
	private Stage stage;
	private TextButton back, continueButton, newButton;
	private TextButtonStyle buttonStyle, backButtonStyle;
	private Table table;
	private Label title;
	private LabelStyle style;
	private BitmapFont backFont;
	
	public PlayScreen(Zweihander mygame) {
		this.game = mygame;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0,0,0,1);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		table.invalidateHierarchy();
		table.setSize(width, height);
	}

	@Override
	public void show() {
		stage = new Stage();
		
		//Label+LabelStyle
		style = new LabelStyle();
		backFont = Assets.getFont("ui/white.fnt");
		style.font = backFont;
		title = new Label("Play:", style);
		title.setFontScale(3);
		
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = backFont;
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.checkedFontColor = Color.WHITE;
		buttonStyle.downFontColor = Color.ORANGE;
		buttonStyle.overFontColor = Color.ORANGE;
		buttonStyle.checkedOverFontColor = Color.ORANGE;
		buttonStyle.pressedOffsetX = 2;
		buttonStyle.pressedOffsetY = -2;
		
		backButtonStyle = new TextButtonStyle(buttonStyle);
		backButtonStyle.font = backFont;	
		back = new TextButton("Back", backButtonStyle);
		
		continueButton = new TextButton("Continue Adventure", buttonStyle);
		continueButton.setTouchable(Touchable.disabled);
		newButton = new TextButton("New Adventure", buttonStyle);
		
		continueButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.gameScreen);
			}
		});
		
		newButton.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.gameScreen);
			}
		});
		
		back.addListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.mainMenuScreen);
			}
			
		});
		
		table = new Table();
		Gdx.input.setInputProcessor(stage);
		table.center().top().pad(50).setFillParent(true);
		table.add(title).center();
		table.row().padTop(50);
		table.add(continueButton).expand();
		table.row().padTop(50);
		table.add(newButton).expand();
		table.row();
		table.add(back).pad(25);
		
		
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
