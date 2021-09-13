package com.mygdx.game.Hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameObject.Bag;
import com.mygdx.game.GameObject.Book;
import com.mygdx.game.GameObject.GameObject;

import java.util.ArrayList;

public class Hero extends Sprite implements InputProcessor {
    // Move
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean shift;
    private boolean interact;



    private Vector2 velocity = new Vector2();
    private int walk = 3;
    private int run = 5;
    private TiledMapTileLayer collisionLayer;
    private String blockedKey = "blocked";
    private int targetX;
    private int targetY;

    public Hero(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        setSize(getWidth(), getHeight());
    }

    public void draw(Batch spriteBatch) {
        update();
        super.draw(spriteBatch);
    }

    public void update() {
        // move on X
        if (shift) {
            if (up && !collidesTop()) {
                setY(getY() + run);
            }
            if (down && !collidesBottom()) {
                setY(getY() - run);
            }
            if (right && !collidesRight()) {
                setX(getX() + run);
            }
            if (left && !collidesLeft()) {
                setX(getX() - run);
            }
        } else if (!shift) {
            if (up && !collidesTop()) {
                setY(getY() + walk);
            }
            if (down && !collidesBottom()) {
                setY(getY() - walk);
            }
            if (right && !collidesRight()) {
                setX(getX() + walk);
            }
            if (left && !collidesLeft()) {
                setX(getX() - walk);
            }
        }

    }

    private boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
    }

    public boolean collidesRight() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX() + getWidth(), getY() + step))
                return true;
        return false;
    }

    public boolean collidesLeft() {
        for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
            if(isCellBlocked(getX()-5, getY() + step))
                return true;
        return false;
    }

    public boolean collidesTop() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY() + getHeight()))
                return true;
        return false;
    }

    public boolean collidesBottom() {
        for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
            if(isCellBlocked(getX() + step, getY()-5))
                return true;
        return false;
    }


    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isShift() {
        return shift;
    }

    public boolean isInteract() {
        return interact;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                up = true;
                break;
            case Input.Keys.A:
                left = true;
                break;
            case Input.Keys.D:
                right = true;
                break;
            case Input.Keys.S:
                down = true;
                break;
            case Input.Keys.SHIFT_LEFT:
                shift = true;
                break;
            case Input.Keys.SPACE:
                interact = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                up = false;
                break;
            case Input.Keys.S:
                down = false;
                break;
            case Input.Keys.A:
                left = false;
                break;
            case Input.Keys.D:
                right = false;
                break;
            case Input.Keys.SHIFT_LEFT:
                shift = false;
                break;
            case Input.Keys.SPACE:
                interact = false;
                break;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}