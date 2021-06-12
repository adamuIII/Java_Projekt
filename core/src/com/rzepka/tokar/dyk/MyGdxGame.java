package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;


public class MyGdxGame extends Game {

	GameScreen gameScreen;
	Info info;
	Menu menu;
	KoniecGry koniecGry;
	Music audio;
	boolean czyZmienic = false;

	@Override
	public void create() {
		audio = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
		menu = new Menu(this);
		gameScreen = new GameScreen(this);
		setScreen(menu);
		audio.setVolume(0.5f);
		audio.setLooping(true);
		audio.play();
	}

	public void setScreenToGame(){
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		audio.stop();
		audio = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
		audio.setVolume(0.2f);
		audio.setLooping(true);
		audio.play();
	}
	public void setScreenToInfo(){
		info = new Info(this);
		setScreen(info);

	}
	public void setScreenToMenu(){
		menu = new Menu(this);
		audio.stop();
		audio = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
		audio.play();
		setScreen(menu);
	}
	public void setScreenToKoniec(){
		koniecGry = new KoniecGry(this);
		setScreen(koniecGry);
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}
}