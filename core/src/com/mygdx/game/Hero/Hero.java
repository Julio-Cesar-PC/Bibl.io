package com.mygdx.game.Hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Hero {
    Rectangle hero;
    int width;
    int height;
    int Speed;
    private Texture texture;
    Color color;

    private final int HERO_WIDTH = 32;
    private final int HERO_HEIGHT = 32;

    public Hero(int x, int y, int Speed) {
        this.hero = new Rectangle();
        this.hero.x = x;
        this.hero.y = y;
        this.width = HERO_WIDTH;
        this.height = HERO_HEIGHT;
        this.Speed = Speed;
        this.color = Color.GREEN;
        this.texture = new Texture("boneco.png");
    }
    public void update(int x, int y) {
        this.hero.setX(x);
        this.hero.setY(y);
    }
    public void draw(SpriteBatch batch) {
        batch.begin();
        batch.draw(texture, 320, 320);
        batch.end();
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}