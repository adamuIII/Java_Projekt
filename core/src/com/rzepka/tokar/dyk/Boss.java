package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.bullet.collision._btMprSupport_t;

import java.util.Random;

public class Boss {
    int hp;
    float speed;
    public float xPos,yPos;
    float width,height;
    TextureRegion bossGraphics;
    boolean zmianaKierunku = true;


    public Boss(int hp, float speed, float xPos, float yPos, float width, float height,TextureRegion bossGraphics) {
        this.hp = hp;
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.bossGraphics = bossGraphics;


    }




    public boolean intersectsBoss(Rectangle otherRectangle){
        Rectangle thisRectangle = new Rectangle(xPos+18,yPos+18,width-38,height);
        return thisRectangle.overlaps(otherRectangle);
    }

    public void bossMovement(float GETTIME,int WORLD_WIDTH,int clock){


        System.out.println(xPos);
        if(zmianaKierunku)
        {
            if(xPos>=WORLD_WIDTH)
            {
                System.out.println("dupa");
                zmianaKierunku=false;
            }
            xPos=xPos+GETTIME*speed;

        }
        else
        {

            if(xPos<=0)
            {
                System.out.println("dupa");
                zmianaKierunku=true;
            }
            xPos=xPos-GETTIME*speed;
        }



    }

    public void draw(Batch batch)

    {
//        batch.draw(bossGraphics, xPos-110, yPos-90, xPos, yPos, width, height, 1, 1, 180);
        batch.draw(bossGraphics,xPos,yPos,width,height);
    }

}
