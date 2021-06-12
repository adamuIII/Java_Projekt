package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KoniecGry implements Screen {
    private SpriteBatch batch;
    Texture koniecgry;
    private MyGdxGame game;

    public KoniecGry(MyGdxGame game){
        batch = new SpriteBatch();
        koniecgry = new Texture("koniecgry.jpg");
        this.game = game;
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

        batch.begin();

        batch.draw(koniecgry,0,0);
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
