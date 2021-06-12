package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Tworzymy klase typu screen ktora wyswietli swoja scene gdy gracz przegra lub wygra
 * Wyswietlany jest komunikad od tworcow a pod spodem trzy przyciski dzieki ktorym mozemy wyjsc z gry, zagrac ponownie lub wyjsc do menu
 */
public class KoniecGry implements Screen {
    private SpriteBatch batch;
    Texture koniecgry,playButtonActive;
    private MyGdxGame game;
    Sound click;
    public float volume = 0.5f;

    public KoniecGry(MyGdxGame game){
        batch = new SpriteBatch();
        koniecgry = new Texture("koniecgry.jpg");
        playButtonActive = new Texture("PlayButtonActive.png");
        this.game = game;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        batch.begin();
        System.out.println(Gdx.input.getY());
        batch.draw(koniecgry,0,0,1800,900);
        click = Gdx.audio.newSound(Gdx.files.internal("infocus.mp3"));
        if(Gdx.input.getX()>346 && Gdx.input.getX()<597&&Gdx.input.getY()>760&&Gdx.input.getY()<827){
            if(Gdx.input.justTouched())
            {
                click.play(volume);
                game.setScreenToGame();
            }
        }
        if(Gdx.input.getX()>767 && Gdx.input.getX()<1032&&Gdx.input.getY()>760&&Gdx.input.getY()<827){
            if(Gdx.input.justTouched())
            {
                click.play(volume);
                game.setScreenToMenu();
            }
        }
        if(Gdx.input.getX()>1175 && Gdx.input.getX()<1428&&Gdx.input.getY()>760&&Gdx.input.getY()<827){
            if(Gdx.input.justTouched())
            {
                click.play(volume);
                Gdx.app.exit();
            }
        }

        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
