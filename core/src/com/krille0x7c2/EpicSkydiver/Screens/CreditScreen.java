package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.CreditWorld.CreditWorld;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.ObjectsOutsideTheGame.Credits;


public class CreditScreen implements Screen {


    protected float screenWidth;
    protected float screenHeight;
    protected float gameWidth;
    protected float gameHeight;
    protected int midPointY;
    private SpriteBatch batcher;
    private Credits cred;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private TextureRegion bg;


    public GameWorld myGame;
    private IActivityRequestHandler hand;

    public CreditScreen(GameWorld myGame, IActivityRequestHandler hand) {


        this.hand = hand;
        hand.showAds(false);
        this.myGame = myGame;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameWidth = 136;
        gameHeight = screenHeight / (screenWidth / gameWidth);
        Gdx.input.setCatchBackKey(true);
        midPointY = (int) (gameHeight / 2);
        cred = new CreditWorld().getCred();
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        initAsset();

    }

    private void initAsset() {

        bg = Pictures.sky;


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 150, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();


        batcher.draw(bg, 0, 0, 250, 250);
        batcher.draw(Pictures.creditsText, cred.getX(), cred.getY(),
                cred.getWidth(), cred.getHeight());


        cred.update(delta);
        batcher.end();

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
