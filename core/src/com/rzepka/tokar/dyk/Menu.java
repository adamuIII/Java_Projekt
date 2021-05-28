package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rzepka.tokar.dyk.GameScreen;
import javafx.scene.layout.Background;

public class Menu implements Screen {
    private SpriteBatch batch;
    Texture menubackground,playButton,playButtonActive,infoButton,infoButtonActive,exitButton,exitButtonActive;
    private MyGdxGame game;
    Sound infocus;
    public float volume = 0.5F;

    public Menu(MyGdxGame game){
        batch=new SpriteBatch();
        playButton = new Texture("PlayButton.png");
        playButtonActive = new Texture("PlayButtonActive.png");
        infoButton = new Texture("InfoButton.png");
        infoButtonActive = new Texture("InfoButtonActive.png");
        menubackground = new Texture("menubackground.jpg");
        exitButton = new Texture("exitButton.png");
        exitButtonActive = new Texture("exitButtonActive.png");
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0,2,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        infocus = Gdx.audio.newSound(Gdx.files.internal("infocus.mp3"));
        batch.begin();
        batch.draw(menubackground,0,0,1800,900);
        if(Gdx.input.getX()>86 && Gdx.input.getX()<436&&Gdx.input.getY()>200&&Gdx.input.getY()<350){
            batch.draw(playButtonActive,86,500,350,150);
            if(Gdx.input.justTouched())
            {
                infocus.play(volume);
                game.setScreenToGame();
            }

        }else{
            batch.draw(playButton,86,500,350,150);
        }
        if(Gdx.input.getX()>86 && Gdx.input.getX()<436&&Gdx.input.getY()>400&&Gdx.input.getY()<550){
            batch.draw(infoButtonActive,86,300,350,150);
            if(Gdx.input.justTouched())
            {
                infocus.play(volume);
                game.setScreenToInfo();
            }
        }else{
            batch.draw(infoButton,86,300,350,150);
        }
        if(Gdx.input.getX()>86 && Gdx.input.getX()<436&&Gdx.input.getY()>600&&Gdx.input.getY()<750){
            batch.draw(exitButtonActive,86,100,350,150);
            if(Gdx.input.isTouched())
            {
                infocus.play();
                Gdx.app.exit();
            }
        }else{
            batch.draw(exitButton,86,100,350,150);
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