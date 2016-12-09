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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OptionScreen implements Screen {
	Zweihander game;
	private Stage stage;
	private TextButton back;
	private TextButtonStyle buttonStyle;
	private Table table;
	private Label title, fillerText;
	private LabelStyle style;
	private BitmapFont white;
	
	public OptionScreen(Zweihander mygame) {
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

	}

	@Override
	public void show() {
		stage = new Stage();
		style = new LabelStyle();
        white = Assets.getFont("ui/white.fnt");
		style.font = white;
		title = new Label("Options Screen", style);
		fillerText = new Label("Options are coming here...", style);
		buttonStyle = new TextButtonStyle();
		buttonStyle.font = white;
		buttonStyle.fontColor = Color.WHITE;
		buttonStyle.checkedFontColor = Color.WHITE;
		buttonStyle.downFontColor = Color.ORANGE;
		buttonStyle.overFontColor = Color.ORANGE;
		buttonStyle.checkedOverFontColor = Color.ORANGE;
		buttonStyle.pressedOffsetX = 2;
		buttonStyle.pressedOffsetY = -2;
		back = new TextButton("Back", buttonStyle);
		back.addListener(new ChangeListener(){

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.mainMenuScreen);
			}
			
		});
		table = new Table();
		table.center().top().pad(50).setFillParent(true);
		Gdx.input.setInputProcessor(stage);
		table.add(title);
		table.row();
		table.add(fillerText);
		table.row();
		table.add(back);
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
