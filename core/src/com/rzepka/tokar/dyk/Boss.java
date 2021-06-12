package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Klasa tworzaca Bosa.
 * Bos pojawia sie po zdobyciu odpowiedniej ilosci punktow. Pojawienie sie bosa zatrzymuje spawn przeciwnikow.
 *
 */
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

    /**
     * Tworzymy prostokat na podstawie wysokosci i wielkosci png bosa dzieki ktoremu mozemy otrzymac hibox bosa.
     */
    public boolean intersectsBoss(Rectangle otherRectangle){
        Rectangle thisRectangle = new Rectangle(xPos+18,yPos+18,width-38,height);
        return thisRectangle.overlaps(otherRectangle);
    }

    /**
     * Metoda dzieki ktorej bos moze sie poruszac. Przemieszcza sie tylko po x. Gdy doleci do kranca ekranu to zmienia kierunek w ktorym sie porusza.
     */
    public void bossMovement(float GETTIME,int WORLD_WIDTH,int clock){
        System.out.println(xPos);
        if(zmianaKierunku)
        {
            if(xPos>=WORLD_WIDTH-width)
            {
                zmianaKierunku=false;
            }
            xPos=xPos+GETTIME*speed;

        }
        else
        {

            if(xPos<=0)
            {
                zmianaKierunku=true;
            }
            xPos=xPos-GETTIME*speed;
        }



    }

    public void draw(Batch batch)

    {
        batch.draw(bossGraphics,xPos,yPos,width,height);
    }

}
