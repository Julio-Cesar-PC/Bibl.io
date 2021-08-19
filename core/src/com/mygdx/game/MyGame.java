package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Hero.Hero;
import com.badlogic.gdx.graphics.GL20;


public class MyGame extends ApplicationAdapter {
	ShapeRenderer shape;
	Hero hero;
	int key;

	@Override
	public void create() {
		shape = new ShapeRenderer();
		hero = new Hero(50, 50, 5);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		inputReader();
		shape.begin(ShapeRenderer.ShapeType.Filled);
		hero.draw(shape);
		shape.end();
	}
	void inputReader() {
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			key = Input.Keys.RIGHT;
			if (hero.getX()+70 > Gdx.graphics.getWidth()) {
				this.hero.setX(this.hero.getX());
			} else {
				this.hero.setX(this.hero.getX() + hero.getSpeed());
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			key = Input.Keys.LEFT;
			if (hero.getX() < 0) {
				this.hero.setX(this.hero.getX());
			} else {
				this.hero.setX(this.hero.getX() - hero.getSpeed());
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			key = Input.Keys.UP;
			if (hero.getY()+70 > Gdx.graphics.getHeight()) {
				this.hero.setY(this.hero.getY());
			} else {
				this.hero.setY(this.hero.getY() + hero.getSpeed());
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			key = Input.Keys.DOWN;
			if (hero.getY() < 0) {
				this.hero.setY(this.hero.getY());
			} else {
				this.hero.setY(this.hero.getY() - hero.getSpeed());
			}
		}
	}

}
