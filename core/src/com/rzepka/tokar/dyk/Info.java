package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Info implements Screen {
    private SpriteBatch batch;
    Texture adamInfo,bartekInfo,temp;
    private MyGdxGame game;


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
        batch.draw(temp,-140,-40,1800,900);
        if(Gdx.input.getX()>1030 && Gdx.input.getX()<1115&&Gdx.input.getY()>747&&Gdx.input.getY()<827){
            if(Gdx.input.justTouched())
            {
                  temp = adamInfo;
            }
        }
        if(Gdx.input.getX()>1130 && Gdx.input.getX()<1210&&Gdx.input.getY()>747&&Gdx.input.getY()<827){
            if(Gdx.input.justTouched())
            {
                    temp = bartekInfo;
            }
        }

        if(Gdx.input.getX()>1227 && Gdx.input.getX()<1315&&Gdx.input.getY()>747&&Gdx.input.getY()<827){
            if(Gdx.input.justTouched())
            {
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
