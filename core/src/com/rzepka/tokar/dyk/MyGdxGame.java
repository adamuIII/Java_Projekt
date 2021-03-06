package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;


/**
 * Klasa glowna naszej gry
 * Tutaj ladujemy wszystkie obiekty naszej gry oraz zmieniamy sceny
 */
public class MyGdxGame extends Game {

	GameScreen gameScreen;
	Info info;
	Menu menu;
	KoniecGry koniecGry;
	Music audio;

	@Override
	public void create() {
		/**
		 * Dodanie muzyki do menu
		 */
		audio = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
		menu = new Menu(this);
		/**
		 * Ustawienie wyswietlanej sceny na game Screen
		 */
		gameScreen = new GameScreen(this);
		setScreen(menu);
		audio.setVolume(0.5f);
		audio.setLooping(true);
		audio.play();
	}

	/**
	 * Ustawienie sceny gry na rozgrywke
	 */
	public void setScreenToGame(){
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
		audio.stop();
		audio = Gdx.audio.newMusic(Gdx.files.internal("game.mp3"));
		audio.setVolume(0.2f);
		audio.setLooping(true);
		audio.play();
	}

	/**
	 * Ustawienie sceny gry na Info
	 */
	public void setScreenToInfo(){
		info = new Info(this);
		audio.stop();
		audio = Gdx.audio.newMusic(Gdx.files.internal("info.mp3"));
		audio.setVolume(0.2f);
		audio.play();
		setScreen(info);

	}

	/**
	 * Ustawienie sceny gry na menu
	 */
	public void setScreenToMenu(){
		menu = new Menu(this);
		audio.stop();
		audio = Gdx.audio.newMusic(Gdx.files.internal("menu.mp3"));
		audio.play();
		setScreen(menu);
	}

	/**
	 * Ustawienie sceny gry na menu koncowe ktore wyswietla sie po wygraniu lub przegraniu
	 */
	public void setScreenToKoniec(){
		koniecGry = new KoniecGry(this);
		audio.stop();
		audio = Gdx.audio.newMusic(Gdx.files.internal("end.mp3"));
		audio.setVolume(0.2f);
		audio.play();
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