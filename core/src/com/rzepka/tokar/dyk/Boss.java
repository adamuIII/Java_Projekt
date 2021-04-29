package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boss {
    int hp;
    float speed;
    public float xPos,yPos;
    float width,height;
    TextureRegion bossGraphics;

    public Boss(int hp, float speed, float xPos, float yPos, float width, float height,TextureRegion bossGraphics) {
        this.hp = hp;
        this.speed = speed;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.bossGraphics = bossGraphics;
    }

    public void draw(Batch batch)

    //  batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
    {
       // batch.draw(bossGraphics, xPos ,yPos,width,height);
        batch.draw(bossGraphics, xPos-110, yPos-90, xPos, yPos, width, height, 1, 1, 180);

    }

}
