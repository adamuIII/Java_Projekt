package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Klasa tworzaca przeciwnika
 * Tworzymy statek przeciwnika na losowej pozycji x
 */
public class Enemy {
    float enemySpeed;
    int randomPositionX;
    float yPos;
    float enemyHP;
    float width,height;
    public boolean remove = false;
    TextureRegion enemyGraphic;


    public Enemy(float enemySpeed, int randomPositionX, float yPos, float enemyHP, float width, float height, TextureRegion enemyGraphic) {
        this.enemySpeed = enemySpeed;
        this.randomPositionX = randomPositionX;
        this.yPos = yPos;
        this.enemyHP = enemyHP;
        this.width = width;
        this.height = height;
        this.enemyGraphic = enemyGraphic;
    }

    /**
     * Metoda enemyMovement sprawia ze statek przeciwnika zmienia swoje polozenie na zmiennej y
     * Statek porusza sie zaleznie od wartosci enemySpeed
     */
    public void enemyMovement(int WORLD_HEIGHT, float GETTIME){
        yPos=yPos-GETTIME*enemySpeed;

        if(yPos<0)
        {

            remove=true;
        }
    }

    /**
     * Metoda odpowiadajaca za odjecie punktow zycia gdy przeciwnik wyleci poza granice ekranu
     */
    public float odejmijHP(float hp)
    {
        if(yPos<0)
        {
            hp--;
        }
        return hp;
    }

    /**
     * Metoda tworzy prostokat dzieki ktoremu okreslamy hitbox przeciwnika
     */
    public boolean intersects(Rectangle otherRectangle){
        Rectangle thisRectangle = new Rectangle(randomPositionX,yPos,width,height);
        return thisRectangle.overlaps(otherRectangle);
    }

    public void draw(Batch batch)    {
        batch.draw(enemyGraphic,randomPositionX,yPos,width,height);
    }
}
