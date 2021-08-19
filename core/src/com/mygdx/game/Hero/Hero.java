package com.mygdx.game.Hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Hero {
    Rectangle hero;
    int width;
    int height;
    int Speed;
    private Texture texture;
    Color color;

    private final int HERO_WIDTH = 70;
    private final int HERO_HEIGHT = 70;

    public Hero(int x, int y, int Speed) {
        this.hero = new Rectangle();
        this.hero.x = x;
        this.hero.y = y;
        this.width = HERO_WIDTH;
        this.height = HERO_HEIGHT;
        this.Speed = Speed;
        this.color = Color.GREEN;
    }
    public void update() {
        hero.x = Gdx.input.getX();
        hero.y = Gdx.graphics.getHeight() - Gdx.input.getY();
    }
    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.rect(hero.x, hero.y, width, height);
    }

    public float getX() {
        return hero.x;
    }

    public void setX(float x) {
        this.hero.x = x;
    }

    public float getY() {
        return hero.y;
    }

    public void setY(float y) {
        this.hero.y = y;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int Speed) {
        this.Speed = Speed;
    }

}