package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
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
    private TextureRegion statekGraczaTexture, statekPrzeciwnika1Texture,statekPrzeciwnika2Texture,bossTexture,bulletTexture,gamebackground;

//    private Texture background;


     //timing
    private int backgroundOffset;
    float clock=0;

    //world parameters
 //   private final int WORLD_WIDTH = 72;
    //  private final int WORLD_HEIGHT = 128;
    private final int WORLD_WIDTH = 250;
    private final int WORLD_HEIGHT = 140;


    //Objekty gry
    private Player statekGracza;
    ArrayList<Bullet> bullets;
    ArrayList<Enemy> enemies;
    private LinkedList<Explosion> explosionList;
    private Boss boss;
    //Bedziemy mieli duza ilosc pociskow dlatego umiescimy je w LinkedList

    BitmapFont scoreFont,healthFont;


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
        bossTexture = textureAtlas.findRegion("Spaceship_04_RED");
        explosionTexture = new Texture("explosion.png");

        //NOWE
        scoreFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        scoreFont.getData().setScale((float) 0.2);
        healthFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        healthFont.getData().setScale((float) 0.2);

        //tworzenie objektow gry na ekranie
            //tworzenie statku gracza:
        statekGracza = new Player(60, 20, WORLD_WIDTH/2, WORLD_HEIGHT/4,30,30,statekGraczaTexture);
        boss = new Boss(50,30,WORLD_WIDTH/2-35,WORLD_HEIGHT-60,70,70,bossTexture);

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
             statekGracza.xPos -= Gdx.graphics.getDeltaTime() * (statekGracza.speed+30);
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
             statekGracza.xPos += Gdx.graphics.getDeltaTime() * (statekGracza.speed+30);
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
             statekGracza.yPos += Gdx.graphics.getDeltaTime() * (statekGracza.speed-20);
             if(backgroundOffset<139)
             {
             backgroundOffset=backgroundOffset+1;
             }}
         if(Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
             statekGracza.yPos -= Gdx.graphics.getDeltaTime() * statekGracza.speed;




         //Strzal
         if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            bullets.add(new Bullet(60, (float) (statekGracza.xPos+7.3), statekGracza.yPos+15, 12,12,bulletTexture));
            bullets.add(new Bullet(60, (float) (statekGracza.xPos+22.9), statekGracza.yPos+15, 12,12,bulletTexture));

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
                    {   statekGracza.points+=10;
                        enemiesToRemove.add(enemy);
                        explosionList.add(new Explosion(explosionTexture,new Rectangle(enemy.randomPositionX,enemy.yPos,25,25),0.7f));
                    }

                }

            }

            enemy.enemyMovement( WORLD_HEIGHT,Gdx.graphics.getDeltaTime());


            statekGracza.healthPoints=enemy.odejmijHP(WORLD_HEIGHT, statekGracza.healthPoints);
            if (enemy.remove)
                enemiesToRemove.add(enemy);
        }
        enemies.removeAll(enemiesToRemove);
         for (Enemy enemy : enemies){
             enemy.draw(batch);
         }



        //Zatrzymanie spawnu przeciwnikow i stworzenie Bosa
        if(statekGracza.points>=50)
        {
            boss.draw(batch);
            clock =0;
            background =  textureAtlas.findRegion("badlogic");
        }
         clock += Gdx.graphics.getDeltaTime();
         if (clock>1) {
             enemies.add(new Enemy(30,(int) (Math.random() * (200)) + 10,WORLD_HEIGHT,5,25,25,statekPrzeciwnika1Texture));
             clock = 0; // reset your variable to 0
         }
         //gracz

         statekGracza.draw(batch);


         GlyphLayout scoreLayout = new GlyphLayout(scoreFont,"Points: "+statekGracza.points);
         scoreFont.draw(batch,scoreLayout, 5,WORLD_HEIGHT);
         GlyphLayout healthLayout = new GlyphLayout(healthFont,"Health: "+(int)statekGracza.healthPoints);
         healthFont.draw(batch,healthLayout,215,WORLD_HEIGHT);




         //POCISKI

        for (Bullet bullet : bullets){
            bullet.bulletMovement( WORLD_HEIGHT,Gdx.graphics.getDeltaTime());
            if (bullet.remove)
                bulletsToRemove.add(bullet);

        }
        bullets.removeAll(bulletsToRemove);

        for (Bullet bullet : bullets){
            bullet.draw(batch);
        }


        if(statekGracza.healthPoints<=0)
        {
//            Gdx.app.exit();
        }


         renderExplosions(delta);
         //explosions



        batch.end();
     }


     private void keyInput(float deltaTime){

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
