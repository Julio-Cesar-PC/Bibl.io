package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Hero.Hero;
import com.badlogic.gdx.graphics.GL20;


public class GameScreen implements Screen {
	// Game
	final Biblio game;
	final Hero hero;
	SpriteBatch batch;
	// Maps
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer renderer;
	private float MapWidth;
	private float MapHeight;
	private FitViewport viewport;
	private OrthographicCamera camera;

	public GameScreen(final Biblio game){
		this.game = game;
		batch = new SpriteBatch();
		hero = new Hero(320, 320, 5);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		inputReader();

		camera.position.x = hero.getX();
		camera.position.y = hero.getY();
		camera.update();


		//camera.position.set(hero.getX(), hero.getY(),0);
		//camera.update();
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		renderer.setView(camera);
		renderer.render();
		hero.draw(batch);
		System.out.println("\n" + hero.getX() + "\t" + hero.getY());
	}
	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void show() {
		tiledMap = new TmxMapLoader().load("level.tmx");
		renderer = new OrthogonalTiledMapRenderer(tiledMap, 1);
		MapWidth = tiledMap.getProperties().get("width", Integer.class) * tiledMap.getProperties().get("tilewidth", Integer.class);
		MapHeight = tiledMap.getProperties().get("height", Integer.class) * tiledMap.getProperties().get("tileheight", Integer.class);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 1280);
		camera.zoom = 1;
		camera.update();
		viewport = new FitViewport(640,640, camera);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		dispose();
	}

	void inputReader() {
		int key;
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			key = Input.Keys.RIGHT;

			if (hero.getX() + hero.getWidth() > MapWidth - hero.getSpeed()) {
				this.hero.setX(this.hero.getX());
			} else if (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				this.hero.setX(this.hero.getX() + (hero.getSpeed())+1);
			}
			else {
				this.hero.setX(this.hero.getX() + hero.getSpeed());
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			key = Input.Keys.LEFT;
			if (hero.getX() < hero.getSpeed()) {
				this.hero.setX(this.hero.getX());
			} else if (Gdx.input.isKeyPressed(Input.Keys.A) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				this.hero.setX(this.hero.getX() - (hero.getSpeed())-1);
			}
			else {
				this.hero.setX(this.hero.getX() - hero.getSpeed());
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			key = Input.Keys.UP;
			if (hero.getY()+ hero.getHeight() > MapHeight - hero.getSpeed()) {
				this.hero.setY(this.hero.getY());
			} else if (Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				this.hero.setY(this.hero.getY() + (hero.getSpeed())+1);
			}
			else {
				this.hero.setY(this.hero.getY() + hero.getSpeed());
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			key = Input.Keys.DOWN;
			if (hero.getY() < hero.getSpeed()) {
				this.hero.setY(this.hero.getY());
			} else if (Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				this.hero.setY(this.hero.getY() - (hero.getSpeed())-1);
			}
			else {
				this.hero.setY(this.hero.getY() - hero.getSpeed());
			}
		}
	}
}
