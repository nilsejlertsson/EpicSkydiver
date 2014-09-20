package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.CreditWorld.CreditWorld;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.ObjectsOutsideTheGame.Credits;


public class CreditScreen implements Screen {


    private GameWorld gameWorld;
    protected float screenWidth;
    protected float screenHeight;
    protected float gameWidth;
    protected float gameHeight;
    protected int midPointY;
    private SpriteBatch spriteBatch;
    private Credits credits;
    private OrthographicCamera orthographicCamera;
    private ShapeRenderer shapeRenderer;
    private TextureRegion backGround;


    public CreditScreen(GameWorld gameWorld) {


        this.gameWorld = gameWorld;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameWidth = 136;
        gameHeight = screenHeight / (screenWidth / gameWidth);
        Gdx.input.setCatchBackKey(true);
        midPointY = (int) (gameHeight / 2);
        credits = new CreditWorld().getCred();
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(true, 136, gameHeight);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        initAsset();

    }

    private void initAsset() {

        backGround = Pictures.sky;


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 150, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();


        spriteBatch.draw(backGround, 0, 0, 250, 250);
        spriteBatch.draw(Pictures.creditsText, credits.getX(), credits.getY(),
                credits.getWidth(), credits.getHeight());


        credits.update(delta);
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


    @Override
    public void hide() {
        // TODO Auto-generated method stub

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
