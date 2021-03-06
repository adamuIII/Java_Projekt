package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Klasa info zmieni scene gdy w menu klikniemy w przycisk info
 * Wyswietli nam ona scene ktora wyswietla informacje o autorach taka jak wykonana praca w projekcie oraz link do githuba
 * W dolnej czesci ekranu znajduja sie przyciski dzieki ktorym mozemy zmienic wyswietlanego autora lub wrocic do menu
 */
public class Info implements Screen {
    private SpriteBatch batch;
    Texture adamInfo,bartekInfo,temp;
    private MyGdxGame game;
    Sound click;
    public float volume = 0.5f;

    public Info(MyGdxGame game) {
        batch = new SpriteBatch();
        adamInfo = new Texture("info1_adam.jpg");
        bartekInfo = new Texture("info1_kefak.jpg");
        temp = new Texture("info1_adam.jpg");
        this.game = game;
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        System.out.println(Gdx.input.getY());
        batch.draw(temp,0,0,1800,900);
        click = Gdx.audio.newSound(Gdx.files.internal("infocus.mp3"));
        if(Gdx.input.getX()>1177 && Gdx.input.getX()<1255&&Gdx.input.getY()>805&&Gdx.input.getY()<858){
            if(Gdx.input.justTouched())
            {
                  click.play(volume);
                  temp = adamInfo;
            }
        }
        if(Gdx.input.getX()>1272 && Gdx.input.getX()<1346&&Gdx.input.getY()>805&&Gdx.input.getY()<858){
            if(Gdx.input.justTouched())
            {
                    click.play(volume);
                    temp = bartekInfo;
            }
        }

        if(Gdx.input.getX()>1366 && Gdx.input.getX()<1478&&Gdx.input.getY()>805&&Gdx.input.getY()<858){
            if(Gdx.input.justTouched())
            {
                click.play(volume);
                game.setScreenToMenu();
            }
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
