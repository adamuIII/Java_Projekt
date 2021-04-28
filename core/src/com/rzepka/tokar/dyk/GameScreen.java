package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

class GameScreen implements Screen{

     //ekran
    private Camera camera;
    private Viewport viewport;


    //lista wszystkich grafik
    private SpriteBatch batch;
    private Texture explosionTexture;
    //Aby zaoszczedzic miejce wszystkie uzywane grafiki laczymy w jedna za pomoca gdx-texturpackera
    private TextureAtlas textureAtlas;
    private TextureRegion background;
    private TextureRegion statekGraczaTexture, statekPrzeciwnika1Texture,statekPrzeciwnika2Texture,statekPrzeciwnika3Texture,bulletTexture,gamebackground;

//    private Texture background;


     //timing
    private int backgroundOffset;
    float clock=0;
    //world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;
//    private final int WORLD_WIDTH = 128;
//    private final int WORLD_HEIGHT = 72;


    //Objekty gry
    private Player statekGracza;
    ArrayList<Bullet> bullets;
    ArrayList<Enemy> enemies;
    private LinkedList<Explosion> explosionList;
    //Bedziemy mieli duza ilosc pociskow dlatego umiescimy je w LinkedList


    GameScreen(){
        //2d camera
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);

        //inicjacja texture atlas
        textureAtlas = new TextureAtlas("habibi.atlas");

        background = new TextureRegion();
        background =  textureAtlas.findRegion("milkyway");
        backgroundOffset = 0;

        //inicjalizacja grafik
        statekGraczaTexture = textureAtlas.findRegion("Spaceship_06_GREEN");
        bulletTexture = textureAtlas.findRegion("Flame");
        statekPrzeciwnika1Texture = textureAtlas.findRegion("Spaceship_01_RED");
        explosionTexture = new Texture("explosion.png");




        //tworzenie objektow gry na ekranie
            //tworzenie statku gracza:
        statekGracza = new Player(30, 3, WORLD_WIDTH/2, WORLD_HEIGHT/4,10,15,statekGraczaTexture);
            //tworzenie pociskow gracza:
          bullets = new ArrayList<Bullet>();
          enemies = new ArrayList<Enemy>();
            //tworzenie eksplozji
          explosionList = new LinkedList<>();





        batch = new SpriteBatch();
    }

     @Override
     public void show() {

     }

     @Override
     public void render(float delta) {
        batch.begin();

        //Input klawiszy
            //Poruszanie sie
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
             statekGracza.xPos -= Gdx.graphics.getDeltaTime() * statekGracza.speed;
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
             statekGracza.xPos += Gdx.graphics.getDeltaTime() * statekGracza.speed;
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
             statekGracza.yPos += Gdx.graphics.getDeltaTime() * statekGracza.speed;
             if(backgroundOffset<127)
             {
             backgroundOffset=backgroundOffset+1;
             }}
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
             statekGracza.yPos -= Gdx.graphics.getDeltaTime() * statekGracza.speed;


         //Strzal
         if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            bullets.add(new Bullet(60, (float) (statekGracza.xPos+7.6), (statekGracza.yPos+7), 5,10,bulletTexture));
            bullets.add(new Bullet(60, (float) (statekGracza.xPos+2.5), statekGracza.yPos+7, 5,10,bulletTexture));

         }



        //scrolling background
        backgroundOffset++;
        if(backgroundOffset % WORLD_HEIGHT ==0)
        {
            backgroundOffset=0;
        }
        batch.draw(background,0,-backgroundOffset,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(background,0,-backgroundOffset +WORLD_HEIGHT ,WORLD_WIDTH,WORLD_HEIGHT);





         ///////////////////////////przeciwnicy///////////////////////////

         ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
         ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
        for(Enemy enemy : enemies){
            for (Bullet bullet: bullets){
                //kolizja i usuniecie przeciwnika
                if (enemy.intersects(bullet.getHitBox())){

                    bulletsToRemove.add(bullet);
                    enemy.enemyHP--;
                    if(enemy.enemyHP==0)
                    {   statekGracza.points++;
                        enemiesToRemove.add(enemy);
                        explosionList.add(new Explosion(explosionTexture,new Rectangle(enemy.randomPositionX,enemy.yPos,10,10),0.7f));
                    }

                }
            }

            enemy.enemyMovement(WORLD_HEIGHT,Gdx.graphics.getDeltaTime());
            if (enemy.remove)
                enemiesToRemove.add(enemy);
        }
        enemies.removeAll(enemiesToRemove);
         for (Enemy enemy : enemies){
             enemy.draw(batch);
         }


         clock += Gdx.graphics.getDeltaTime();
         if (clock>1) {
                enemies.add(new Enemy(30,(int) ((Math.random() * (70 - 3)) + 1),WORLD_HEIGHT,5,8,15,statekPrzeciwnika1Texture));
             clock = 0; // reset your variable to 0
         }




         //gracz

         statekGracza.draw(batch);




         //POCISKI
//
        for (Bullet bullet : bullets){
            bullet.bulletMovement(WORLD_HEIGHT,Gdx.graphics.getDeltaTime());
            if (bullet.remove)
                bulletsToRemove.add(bullet);

        }
        bullets.removeAll(bulletsToRemove);

        for (Bullet bullet : bullets){
            bullet.draw(batch);
        }



         renderExplosions(delta);
         //explosions
        batch.end();
     }

     private void renderExplosions(float deltaTime){
        ListIterator<Explosion> explosionListIterator = explosionList.listIterator();
        while (explosionListIterator.hasNext()){
            Explosion explosion = explosionListIterator.next();
            explosion.update(deltaTime);
            if (explosion.isFinished()){
                explosionListIterator.remove();
            }
            else {
                explosion.draw(batch);
            }
        }
     }

     @Override
     public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
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
