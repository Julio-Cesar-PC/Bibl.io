package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameOverScreen implements Screen {

	private static final int EXIT_BUTTON_WIDTH = 250;
	private static final int EXIT_BUTTON_HEIGHT = 120;
	private static final int PLAY_BUTTON_WIDTH = 300;
	private static final int PLAY_BUTTON_HEIGHT = 120;
	private static final int EXIT_BUTTON_Y = 100;
	private static final int PLAY_BUTTON_Y = 230;
	private static final int GAMEOVER_WIDTH = 400;
	private static final int GAMEOVER_HEIGHT = 170;

	final Biblio game;
	private final int score;
	BitmapFont font = new BitmapFont();
	private OrthographicCamera camera;
	private FitViewport viewport;

	SpriteBatch batch;
	Texture gameOverTexture;
	Texture bg;
	Texture playButtonActive;
	Texture playButtonInactive;
	Texture exitButtonActive;
	Texture exitButtonInactive;

	public GameOverScreen (final Biblio game, int score) {
		this.game = game;
		this.score = score;
		batch = new SpriteBatch();
		gameOverTexture = new Texture("gameOverScreen.png");
		playButtonActive = new Texture("play_button_active.png");
		playButtonInactive = new Texture("play_button_inactive.png");
		exitButtonActive = new Texture("exit_button_active.png");
		exitButtonInactive = new Texture("exit_button_inactive.png");
		batch = new SpriteBatch();
		bg = new Texture("bg.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 1280);
		viewport = new FitViewport(640,640, camera);

		final GameOverScreen gameOverScreen = this;

		Gdx.input.setInputProcessor(new InputAdapter() {

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				//Exit button
				int x = (int) camera.viewportWidth / 2 - EXIT_BUTTON_WIDTH / 2;
				if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && camera.viewportHeight - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && camera.viewportHeight - Gdx.input.getY() > EXIT_BUTTON_Y) {
					gameOverScreen.dispose();
					Gdx.app.exit();
				}

				//Play game button
				x = (int) camera.viewportWidth / 2 - PLAY_BUTTON_WIDTH / 2;
				if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && camera.viewportHeight - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && camera.viewportHeight - Gdx.input.getY() > PLAY_BUTTON_Y) {
					gameOverScreen.dispose();
					game.setScreen(new GameScreen(game));
				}

				return super.touchUp(screenX, screenY, pointer, button);
			}

		});
	}

	@Override
	public void show () {
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.x = -Gdx.graphics.getWidth()/2;
		camera.position.y = -Gdx.graphics.getHeight()/2;
		camera.update();
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.begin();

		batch.draw(bg, -camera.viewportWidth/2, -camera.viewportHeight/2);
		int x = (int) camera.viewportWidth / 2 - EXIT_BUTTON_WIDTH / 2;
		if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > EXIT_BUTTON_Y) {
			batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		} else {
			batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		}

		x = (int) camera.viewportWidth / 2 - PLAY_BUTTON_WIDTH / 2;
		if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY()  < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > PLAY_BUTTON_Y) {
			batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		} else {
			batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
		}
		x = (int) camera.viewportWidth / 2 - GAMEOVER_WIDTH / 2;
		batch.draw(gameOverTexture, x, 450, GAMEOVER_WIDTH, GAMEOVER_HEIGHT);
		font.getData().setScale(2);
		font.setColor(0.7f, 0, 1, 1);
		font.draw(batch,"Score: " + score, 40, 40);
		batch.end();
	}

	@Override
	public void resize (int width, int height) {

	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

}