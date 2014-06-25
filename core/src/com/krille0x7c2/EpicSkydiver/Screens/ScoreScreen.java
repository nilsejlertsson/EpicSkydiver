package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.Connections.Score;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;


public class ScoreScreen implements Screen {

    private SpriteBatch batch;
    protected BitmapFont whiteText;
    private OrthographicCamera cam;
    public static Texture textureStart;
    private Score score;
    public GameWorld myGame;
    private TextureRegion splashBackground;
    private Texture splashTexture;


    private Sprite splash;
    private IActivityRequestHandler hand;

    public ScoreScreen(
            final IActivityRequestHandler hand) {


        this.hand = hand;
        hand.showAds(false);

        // score = new Score(1, Preference.getHighScore());

        // score = new Score(1, Preference.getHighScore());

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        loadPic();
        Gdx.input.setCatchBackKey(true);

        // try {
        // nativeSql.getScore(1);
        // } catch (Exception e) {
        // nativeSql.addScore(0);
        // }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // splash.draw(batch);

        batch.draw(splashBackground, 0, 0, 136, 204);
        drawHighScoreTitle();
        drawHighScore();

        batch.end();

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
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameMenuScreen(myGame, hand));
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
        Pictures.whiteHighScoreTitle.draw(batch, title, 8, 10);
    }

    private void drawHighScore() {

        // int x = Preference.getHighScore();
        // int y = nativeSql.getScore(1).getScore();
        // if (x >= y) {
        String scoreFromPref = Preference.getHighScore() + "";
        Pictures.whiteHighScore.draw(batch, "" + Preference.getHighScore(),
                ((136 / 2)) - (13 * scoreFromPref.length()), 90);
        // nativeSql.updateScore(score);
        // }
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
