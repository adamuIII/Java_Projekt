package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class EnemyBullet {

    float enemyBulletSpeed;
    float xpos,ypos;
    float width, height;
    public boolean remove = false;

    TextureRegion enemyBulletGraphic;

    public EnemyBullet(float enemyBulletSpeed, float xpos, float ypos, float width, float height, TextureRegion enemyBulletGraphic) {
        this.enemyBulletSpeed = enemyBulletSpeed;
        this.xpos = xpos;
        this.ypos = ypos;
        this.width = width;
        this.height = height;
        this.enemyBulletGraphic = enemyBulletGraphic;
    }

    public void EnemyBulletMovement(int WORLD_HEIGHT, float GETTIME){
        ypos=ypos-GETTIME*enemyBulletSpeed;
        if (ypos<0)
        {
            remove=true;
        }
    }

    public void draw(Batch batch)
    {
        batch.draw(enemyBulletGraphic,xpos,ypos,width,height);
    }


    public com.badlogic.gdx.math.Rectangle getHitBoxEnemybullet(){
        return new com.badlogic.gdx.math.Rectangle(xpos,ypos,width,height);
    }
}
