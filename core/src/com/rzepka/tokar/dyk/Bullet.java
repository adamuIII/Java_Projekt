package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

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

    public void bulletMovement(int WORLD_HEIGHT,float GETTIME){
        yPosition=yPosition+GETTIME*bulletSpeed;
        if(yPosition>WORLD_HEIGHT)
        {
            remove=true;
        }
    }

    public void draw(Batch batch){
        batch.draw(bulletGraphic,xPosition,yPosition,width,height);}


    public Rectangle getHitBox(){
        return new Rectangle(xPosition,yPosition,width,height);
    }
}
