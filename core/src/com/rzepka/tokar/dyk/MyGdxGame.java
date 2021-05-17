package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Game;


public class MyGdxGame extends Game {

	GameScreen gameScreen;
	Menu menu;
	boolean czyZmienic = false;

	@Override
	public void create() {
		gameScreen = new GameScreen();
		menu = new Menu(this);
		setScreen(menu);
	}

	public void setScreenToGame(){
		gameScreen = new GameScreen();
		setScreen(gameScreen);
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