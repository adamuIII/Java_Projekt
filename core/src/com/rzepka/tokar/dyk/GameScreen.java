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
    float clockEnemyBullet=0;
    boolean bossDestroyed = false;
    int bossesDestroyed = 0;
    int iloscPotrzebnychPkt =100;
    public float spawntime =2;
    float clockForBoss=0;

    //world parameters
 //   private final int WORLD_WIDTH = 72;
    //  private final int WORLD_HEIGHT = 128;
    private final int WORLD_WIDTH = 250;
    private final int WORLD_HEIGHT = 140;


    //Objekty gry
    private Player statekGracza;
    ArrayList<Bullet> bullets;
    ArrayList<EnemyBullet> enemyBullets;
    ArrayList<Enemy> enemies;
    private LinkedList<Explosion> explosionList;
    ArrayList<Boss> bosses;
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
        bossTexture.flip(true,true);
        //NOWE
        scoreFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        scoreFont.getData().setScale((float) 0.2);
        healthFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        healthFont.getData().setScale((float) 0.2);

        //tworzenie objektow gry na ekranie
            //tworzenie statku gracza:
        statekGracza = new Player(60, 20, WORLD_WIDTH/2, WORLD_HEIGHT/4,30,30,statekGraczaTexture);
//        boss = new Boss(60,30,WORLD_WIDTH/2-35,WORLD_HEIGHT-60,70,70,bossTexture);
        bosses = new ArrayList<Boss>();
        //tworzenie pociskow gracza:
          bullets = new ArrayList<Bullet>();
          enemies = new ArrayList<Enemy>();
          enemyBullets = new ArrayList<EnemyBullet>();
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
            //Poruszanie sie i strzelanie
         keyInput();




        //scrolling background
        backgroundOffset++;
        if(backgroundOffset % WORLD_HEIGHT ==0)
        {
            backgroundOffset=0;
        }
        batch.draw(background,0,-backgroundOffset,WORLD_WIDTH,WORLD_HEIGHT);
        batch.draw(background,0,-backgroundOffset +WORLD_HEIGHT ,WORLD_WIDTH,WORLD_HEIGHT);





         //Utworzenie listy przeciwników oraz pocisków
         ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();
         ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();
         ArrayList<EnemyBullet>EnemyBulletsToRemove = new ArrayList<EnemyBullet>();
         ArrayList<Boss>BossToRemove = new ArrayList<Boss>();
         //Metody przeciwników oraz wykrywanie kolizji
        PrzeciwnicyIkolizja(enemiesToRemove,bulletsToRemove);
        //Spawn przeciwnikow co podana ilosc sekund
        SpawnPrzeciwnikaCoXsec(spawntime);


        //rysowanie gracza
         statekGracza.draw(batch);

         //Wygenerowanie napisów Ilość zdobytych punktów oraz Ilość punktów życia
         Napisy();

         //POCISKI BOSSA
         for(EnemyBullet enemyBullet: enemyBullets){
             enemyBullet.EnemyBulletMovement(WORLD_HEIGHT,Gdx.graphics.getDeltaTime());
             if(enemyBullet.remove)
                 EnemyBulletsToRemove.add(enemyBullet);

         }
         enemyBullets.removeAll(EnemyBulletsToRemove);
         for(EnemyBullet enemyBullet: enemyBullets)
         {
             enemyBullet.draw(batch);
         }


         if(statekGracza.points>=iloscPotrzebnychPkt){
             bossDestroyed = false;
         if(bossDestroyed ==false){
             clock=0;
             if(bosses.size()<1){
            bosses.add(new Boss(60,30,WORLD_WIDTH/2-60,WORLD_HEIGHT-58,70,70,bossTexture));}
             for(Boss boss:bosses){
                 {
                     boss.draw(batch);
                     background =  textureAtlas.findRegion("badlogic");
                     clockEnemyBullet+=Gdx.graphics.getDeltaTime();
                     clockForBoss+=Gdx.graphics.getDeltaTime();
                     if(clockEnemyBullet>1)
                     {
                         enemyBullets.add(new EnemyBullet(90,boss.xPos+8,boss.yPos+20,20,20,bulletTexture));
                         enemyBullets.add(new EnemyBullet(90, (float) (boss.xPos + 42), boss.yPos + 20, 20, 20, bulletTexture));
                         enemyBullets.add(new EnemyBullet(60, (float) (boss.xPos + 31), boss.yPos +20, 20, 20, bulletTexture));
                         enemyBullets.add(new EnemyBullet(60, (float) (boss.xPos + 19), boss.yPos + 20, 20, 20, bulletTexture));
                         clockEnemyBullet=0;
                     }
                    boss.bossMovement(delta,WORLD_WIDTH,(int)clockForBoss);


                 }
                 for(Bullet bullet: bullets){
                     if(boss.intersectsBoss(bullet.getHitBox())){
                         bulletsToRemove.add(bullet);
                         boss.hp--;
                         if(boss.hp==0)
                         {
                             spawntime= (float) (spawntime/1.5);
                             bossesDestroyed++;
                             bullet.bulletSpeed=bullet.bulletSpeed*2;
                             iloscPotrzebnychPkt=iloscPotrzebnychPkt+(200*bossesDestroyed);
                             statekGracza.points+=100;
                             explosionList.add(new Explosion(explosionTexture,new Rectangle(boss.xPos,boss.yPos,40,40),0.7f));
                             BossToRemove.add(boss);
                             bossDestroyed =true;


                         }
                     }
                 }
             }
             bosses.removeAll(BossToRemove);
         }
         }






         //Zatrzymanie spawnu przeciwnikow i stworzenie Bosa







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

    //Wyjście z aplikacji po przegranej
        if(statekGracza.healthPoints<=0)
        {
            Gdx.app.exit();
        }

         //Renderowanie eksplozji
         renderExplosions(delta);



        batch.end();
     }


     private void keyInput(){
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

         if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
             bullets.add(new Bullet(60, (float) (statekGracza.xPos+7.3), statekGracza.yPos+15, 12,12,bulletTexture));
             bullets.add(new Bullet(60, (float) (statekGracza.xPos+22.9), statekGracza.yPos+15, 12,12,bulletTexture));
            if(bossesDestroyed>=1) {
                bullets.add(new Bullet(60, (float) (statekGracza.xPos + 13.9), statekGracza.yPos + 15, 12, 12, bulletTexture));
            }
             if(bossesDestroyed>=3) {
                 bullets.add(new Bullet(60, (float) (statekGracza.xPos + 17.9), statekGracza.yPos + 15, 12, 12, bulletTexture));
             }
         }
     }


     private void Napisy(){
         GlyphLayout scoreLayout = new GlyphLayout(scoreFont,"Points: "+statekGracza.points);
         scoreFont.draw(batch,scoreLayout, 5,WORLD_HEIGHT);
         GlyphLayout healthLayout = new GlyphLayout(healthFont,"Health: "+(int)statekGracza.healthPoints);
         healthFont.draw(batch,healthLayout,215,WORLD_HEIGHT);
     }
    private void PrzeciwnicyIkolizja(ArrayList enemiesToRemove,ArrayList bulletsToRemove){
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
            enemy.enemyMovement(WORLD_HEIGHT, Gdx.graphics.getDeltaTime());
        }



        for(Enemy enemy: enemies) {
            statekGracza.healthPoints = enemy.odejmijHP(statekGracza.healthPoints);
            if (enemy.remove)
                enemiesToRemove.add(enemy);
        }

        //Dodanie tekstury do przeciwnika
        enemies.removeAll(enemiesToRemove);
        for (Enemy enemy : enemies){
            enemy.draw(batch);
        }


    }

    private void SpawnPrzeciwnikaCoXsec(float SprawnTime)
    {
        clock += Gdx.graphics.getDeltaTime();
        if (clock>SprawnTime) {
            enemies.add(new Enemy(30,(int) (Math.random() * (200)) + 10,WORLD_HEIGHT,5,25,25,statekPrzeciwnika1Texture));
            clock = 0; // reset your variable to 0
        }
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
