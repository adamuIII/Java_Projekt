package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

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

    public boolean intersectsBoss(Rectangle otherRectangle){
        Rectangle thisRectangle = new Rectangle(xPos,yPos,width,height);
        return thisRectangle.overlaps(otherRectangle);
    }

    public void bossMovement(float GETTIME,int WORLD_WIDTH,float clock){
            if(clock>2||clock>4||clock>6||clock>8||clock>10||clock>12)
                xPos=xPos-GETTIME*speed;
            else
                xPos=xPos+GETTIME*speed;
            clock=0;


    }

    public void draw(Batch batch)

    {
        batch.draw(bossGraphics, xPos-110, yPos-90, xPos, yPos, width, height, 1, 1, 180);
    }

}
