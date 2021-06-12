package com.rzepka.tokar.dyk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Tworzymy klase Player
 * Jest to statek nad ktorym gracz ma pelna kontrole
 */
public class Player {

    //Podstawowe wartosci;
    float speed;
    float healthPoints;
    int points=0;
    public float xPos,yPos;
    float width,height;




    //grafika
    TextureRegion playerGraphics;

    //Tworzymy konstruktor
    public Player(float speed, float healthPoints, float xPos, float yPos, float width, float height,TextureRegion playerGraphics) {
        this.speed = speed;
        this.healthPoints = healthPoints;
        this.xPos = xPos - width/2;
        this.yPos = yPos -height/2;
        this.width = width;
        this.height = height;
        this.playerGraphics = playerGraphics;


    }


    //Tworzenie statku na ekranie
    public void draw(Batch batch){
        batch.draw(playerGraphics, xPos ,yPos,width-7,height-5);
    }

    /**
     * Zmienna boolean intersectsPlayer sprawdza czy pocisk przeciwnika wchodzi w hitbox gracza
     */
    public boolean intersectsPlayer(Rectangle otherRectangle){
        Rectangle thisRectangle = new Rectangle(xPos+10,yPos+5,width-18,height-20);
        return thisRectangle.overlaps(otherRectangle);
    }






}
