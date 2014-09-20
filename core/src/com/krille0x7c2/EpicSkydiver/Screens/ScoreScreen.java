package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;

/**
 * Created by Christian Bodelsson
 */

public class ScoreScreen implements Screen {

    public static Texture textureStart;
    public GameWorld gameWorld;
    private SpriteBatch spriteBatch;
    private OrthographicCamera orthographicCamera;
    private TextureRegion splashBackground;


    public ScoreScreen() {


        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(true, 136, 204);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        loadPic();
        Gdx.input.setCatchBackKey(true);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(splashBackground, 0, 0, 136, 204);
        drawHighScoreTitle();
        drawHighScore();
        spriteBatch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyUp(final int keycode) {
                if (keycode == Keys.BACK) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameMenuScreen(gameWorld));
                }
                return false;
            }
        });
    }


    private void loadPic() {
        splashBackground = Pictures.sky;
    }

    private void drawHighScoreTitle() {
        String title = "Highscore";
        Pictures.whiteHighScoreTitle.draw(spriteBatch, title, 8, 10);
    }

    private void drawHighScore() {


        String scoreFromPref = Preference.getHighScore() + "";
        Pictures.whiteHighScore.draw(spriteBatch, "" + Preference.getHighScore(),
                ((136 / 2)) - (13 * scoreFromPref.length()), 90);

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {

    }
}
