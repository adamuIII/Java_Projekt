package com.rzepka.tokar.dyk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {

    //Podstawowe wartosci;
    float speed;
    float healthPoints;
    int points=0;
    //pozycja
    public float xPos,yPos;
    float width,height;

    //pociski


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
        batch.draw(playerGraphics, xPos ,yPos,width,height);
    }

    public boolean intersectsPlayer(Rectangle anotherRectangle){
        Rectangle thisEnemyRectangle = new Rectangle(xPos,yPos,width,height);
        return thisEnemyRectangle.overlaps(anotherRectangle);
    }






}
