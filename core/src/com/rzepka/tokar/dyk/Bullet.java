package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Klasa tworzaca pocisk gracza
 * Pocisk tworzy sie po kliknieciu spacji w miejscu dzialek gracza
 *
 */
public class Bullet {

    float bulletSpeed;
     float xPosition,yPosition;
    float width,height;
    public boolean remove = false;


    TextureRegion bulletGraphic;

    public Bullet(float bulletSpeed, float xPosition, float yPosition, float width, float height, TextureRegion bulletGraphic) {
        this.bulletSpeed = bulletSpeed;
        this.xPosition = xPosition - width/2;
        this.yPosition = yPosition -height/2;
        this.width = width;
        this.height = height;
        this.bulletGraphic = bulletGraphic;
    }

    /**
     * Poruszanie pocisku w strone przeciwnikow
     */
    public void bulletMovement(int WORLD_HEIGHT,float GETTIME){
        yPosition=yPosition+GETTIME*bulletSpeed;
        if(yPosition>WORLD_HEIGHT)
        {
            remove=true;
        }
    }

    public void draw(Batch batch){
        batch.draw(bulletGraphic,xPosition,yPosition,width,height);}

    /**
     *
     * @return Metoda zwraca prostokat dzieki ktoremu mamy hitbox pocisku
     */
    public Rectangle getHitBox(){
        return new Rectangle(xPosition,yPosition,width,height);
    }
}
