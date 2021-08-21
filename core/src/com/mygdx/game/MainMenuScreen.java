package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MainMenuScreen implements Screen {

    private static final int EXIT_BUTTON_WIDTH = 250;
    private static final int EXIT_BUTTON_HEIGHT = 120;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 120;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 230;

    final Biblio game;

    SpriteBatch batch;

    Texture playButtonActive;
    Texture playButtonInactive;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture bg;

    public MainMenuScreen (final Biblio game) {
        this.game = game;

        playButtonActive = new Texture("play_button_active.png");
        playButtonInactive = new Texture("play_button_inactive.png");
        exitButtonActive = new Texture("exit_button_active.png");
        exitButtonInactive = new Texture("exit_button_inactive.png");
        batch = new SpriteBatch();
        bg = new Texture("bg.png");


        final MainMenuScreen mainMenuScreen = this;

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                //Exit button
                int x = Gdx.graphics.getWidth() / 2 - EXIT_BUTTON_WIDTH / 2;
                if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > EXIT_BUTTON_Y) {
                    mainMenuScreen.dispose();
                    Gdx.app.exit();
                }

                //Play game button
                x = Gdx.graphics.getWidth() / 2 - PLAY_BUTTON_WIDTH / 2;
                if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > PLAY_BUTTON_Y) {
                    mainMenuScreen.dispose();
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
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(bg, -320, -320);
        int x = Gdx.graphics.getWidth() / 2 - EXIT_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > EXIT_BUTTON_Y) {
            batch.draw(exitButtonActive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            batch.draw(exitButtonInactive, x, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }

        x = Gdx.graphics.getWidth() / 2 - PLAY_BUTTON_WIDTH / 2;
        if (Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && Gdx.graphics.getHeight() - Gdx.input.getY()  < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && Gdx.graphics.getHeight() - Gdx.input.getY() > PLAY_BUTTON_Y) {
            batch.draw(playButtonActive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        } else {
            batch.draw(playButtonInactive, x, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT);
        }


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