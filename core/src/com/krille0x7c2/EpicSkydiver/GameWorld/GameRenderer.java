package com.krille0x7c2.EpicSkydiver.GameWorld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.Enums.Debug;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Cloud;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Coin;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Duck;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Player;

/**
 * Created by Christian Bodelsson
 */

public class GameRenderer {

    private final Debug debug;

    private GameWorld gameWorld;
    private OrthographicCamera orthographicCamera;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private int midPointY;
    private TextureRegion pauseCloud, gameOverCloud;
    private Player player;
    private Cloud cloud, cloud1, cloud2;
    private Duck duckL, duckR;
    private Coin coin1, coin2, coin3;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {

        this.debug = Debug.NO;
        gameWorld = world;
        this.midPointY = midPointY;
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(true, 136, gameHeight);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        initAsset();
        getTheObjectsFromGameWorld();

    }

    private void initAsset() {

        pauseCloud = Pictures.pauseWindow;
        gameOverCloud = Pictures.gameOverWindow;
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.disableBlending();
        spriteBatch.draw(Pictures.sky, 0, 0, 250, 250);

        if (gameWorld.isRunning()) {

            drawGameObjects(runTime);
            animate(player.getAccX(), player);
            drawScoreOnTopOfScreen();


        } else if (gameWorld.isGameOver() || gameWorld.isHighScore()) {

            drawGameObjects(runTime);
            drawGameoverWindow();
            drawScore();
            drawHighscore();


        } else if (gameWorld.isPaused()) {

            drawGameObjects(runTime);
            animate(player.getAccX(), player);
            drawPauseWindow();


        }

        spriteBatch.end();
        debugObjects();
        debugControls();

    }

    private void drawScoreOnTopOfScreen() {
        scoreColor();
        String score = gameWorld.getScore() + "";


        Pictures.blackTextScoreOnScreen.draw(spriteBatch, "" + gameWorld.getScore(),
                (136 / 2) - (3 * score.length() - 1), 4);
    }

    private void scoreColor() {
        int score = gameWorld.getScore();

        switch (score) {
            case 0:
                Pictures.blackTextScoreOnScreen.setColor(0, 0, 0, 1);

                break;
            case 20:
                Pictures.blackTextScoreOnScreen.setColor(1, 0, 0, 1);

                break;
            case 40:
                Pictures.blackTextScoreOnScreen.setColor(0, 1, 0, 1);
                break;

            case 60:
                Pictures.blackTextScoreOnScreen.setColor(173, 255, 47, 1);
                break;

            case 80:
                Pictures.blackTextScoreOnScreen.setColor(255, 140, 0, 1);
                break;
            case 100:
                Pictures.blackTextScoreOnScreen.setColor(255, 20, 147, 1);
                break;

            default:
                break;
        }
    }

    private void drawPauseWindow() {
        spriteBatch.draw(pauseCloud, 0, midPointY - 70, 136, 136);
    }

    private void drawGameoverWindow() {
        spriteBatch.draw(gameOverCloud, 0, midPointY - 70, 136, 136);
    }

    private void drawScore() {
        String score = gameWorld.getScore() + "";
        Pictures.blackTextScoreInGameOver.draw(spriteBatch,
                "" + gameWorld.getScore(), (136 / 2) - (10 * score.length()),
                midPointY - 40);
    }

    private void drawHighscore() {
        String score = Preference.getHighScore() + "";
        Pictures.blackTextHighScoreInGameOver.draw(spriteBatch,
                "" + Preference.getHighScore(), (136 / 2)
                        - (6 * score.length()), midPointY + 7
        );
    }

    private void debugControls() {
        if (debug.equals(Debug.CONTROLS)) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.isTheDudeAlive()) {
                player.onClickRight();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.isTheDudeAlive()) {
                player.onClickLeft();
            }
        }
    }

    private void drawGameObjects(float runTime) {
        spriteBatch.enableBlending();
        spriteBatch.draw(Pictures.cloud, cloud.getX(), cloud.getY(),
                cloud.getWidth(), cloud.getHeight());
        spriteBatch.draw(Pictures.cloud1, cloud1.getX(), cloud1.getY(),
                cloud1.getWidth(), cloud1.getHeight());
        spriteBatch.draw(Pictures.cloud2, cloud2.getX(), cloud2.getY(),
                cloud2.getWidth(), cloud2.getHeight());
        spriteBatch.draw(Pictures.duckAnimationL.getKeyFrame(runTime),
                duckL.getX(), duckL.getY(), duckL.getWidth(), duckL.getHeight());
        spriteBatch.draw(Pictures.duckAnimationR.getKeyFrame(runTime),
                duckR.getX(), duckR.getY(), duckR.getWidth(), duckR.getHeight());
        spriteBatch.draw(Pictures.coinAnimation.getKeyFrame(runTime), coin1.getX(),
                coin1.getY(), coin1.getWidth(), coin1.getHeight());

        spriteBatch.draw(Pictures.coinAnimation1.getKeyFrame(runTime),
                coin2.getX(), coin2.getY(), coin2.getWidth(), coin2.getHeight());

        spriteBatch.draw(Pictures.coinAnimation2.getKeyFrame(runTime),
                coin3.getX(), coin3.getY(), coin3.getWidth(), coin3.getHeight());

    }

    private void debugObjects() {
        if (debug.equals(Debug.OBJECTS)) {
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(player.getBounds().x, player.getBounds().y,
                    player.getBounds().width, player.getBounds().height);

            shapeRenderer.rect(duckL.getBoundsHead().x, duckL.getBoundsHead().y,
                    duckL.getBoundsHead().width, duckL.getBoundsHead().height);

            shapeRenderer.rect(duckL.getBoundsBody().x, duckL.getBoundsBody().y,
                    duckL.getBoundsBody().width, duckL.getBoundsBody().height);

            shapeRenderer.rect(duckR.getBoundsBody().x, duckR.getBoundsBody().y,
                    duckR.getBoundsBody().width, duckR.getBoundsBody().height);

            shapeRenderer.rect(duckR.getBoundsHead().x, duckR.getBoundsHead().y,
                    duckR.getBoundsHead().width, duckR.getBoundsHead().height);

            shapeRenderer.rect(coin1.getBounds().x, coin1.getBounds().y,
                    coin1.getBounds().width, coin1.getBounds().height);

            shapeRenderer.rect(coin2.getBounds().x, coin2.getBounds().y,
                    coin2.getBounds().width, coin2.getBounds().height);

            shapeRenderer.rect(coin3.getBounds().x, coin3.getBounds().y,
                    coin3.getBounds().width, coin3.getBounds().height);

            shapeRenderer.end();
        }
    }

    private void getTheObjectsFromGameWorld() {

        player = gameWorld.getPlayer();
        cloud = gameWorld.getCloud1();
        cloud1 = gameWorld.getCloud2();
        cloud2 = gameWorld.getCloud3();
        duckL = gameWorld.getDuckLeft();
        duckR = gameWorld.getDuckRight();
        coin1 = gameWorld.getCoins();
        coin2 = gameWorld.getCoin2();
        coin3 = gameWorld.getCoin3();


    }

    private void animate(float x, Player p1) {
        if (x > 2 && p1.isTheDudeAlive()) {
            spriteBatch.draw(Pictures.dudeLeft, p1.getX(), p1.getY(),
                    p1.getWidth(), p1.getHeight());
        } else if (x < -2 && p1.isTheDudeAlive()) {
            spriteBatch.draw(Pictures.dudeRight, p1.getX(), p1.getY(),
                    p1.getWidth(), p1.getHeight());
        } else if (!p1.isTheDudeAlive()) {
            spriteBatch.draw(Pictures.dudeDown, p1.getX(), p1.getY(),
                    p1.getWidth(), p1.getHeight());
        } else {
            spriteBatch.draw(Pictures.dudeFront, p1.getX(), p1.getY(), p1.getWidth(),
                    p1.getHeight());
        }

    }
}
