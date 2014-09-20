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


public class GameRenderer {

    private final Debug debug;

    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private int midPointY;
    private TextureRegion pauseCloud, gameOverCloud;

    private Player player;
    private Cloud cloud, cloud1, cloud2;
    private Duck duckL, duckR;
    private Coin coin1, coin2, coin3;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {

        this.debug = Debug.CONTROLS;

        myWorld = world;
        this.midPointY = midPointY;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, gameHeight);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initAsset();
        getTheObjectsFromGameWorld();

    }

    private void initAsset() {

        pauseCloud = Pictures.pauseWindow;
        gameOverCloud = Pictures.gameoverWindow;
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();
        batcher.draw(Pictures.sky, 0, 0, 250, 250);

        if (myWorld.isRunning()) {

            drawGameObjects(runTime);
            animate(player.getAccX(), player);
            drawScoreOnTopOfScreen();


        } else if (myWorld.isGameOver() || myWorld.isHighScore()) {

            drawGameObjects(runTime);
            drawGameoverWindow();
            drawScore();
            drawHighscore();


        } else if (myWorld.isPaused()) {

            drawGameObjects(runTime);
            animate(player.getAccX(), player);
            drawPauseWindow();


        }

        batcher.end();
        debugObjects();
        debugControls();

    }

    private void drawScoreOnTopOfScreen() {
        scoreColor();
        String score = myWorld.getScore() + "";


        Pictures.blackTextScoreOnScreen.draw(batcher, "" + myWorld.getScore(),
                (136 / 2) - (3 * score.length() - 1), 4);
    }

    public void scoreColor() {
        int score = myWorld.getScore();

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
        batcher.draw(pauseCloud, 0, midPointY - 70, 136, 136);
    }

    private void drawGameoverWindow() {
        batcher.draw(gameOverCloud, 0, midPointY - 70, 136, 136);
    }

    private void drawScore() {
        String score = myWorld.getScore() + "";
        Pictures.blackTextScoreInGameOver.draw(batcher,
                "" + myWorld.getScore(), (136 / 2) - (10 * score.length()),
                midPointY - 40);
    }

    private void drawHighscore() {
        String score = Preference.getHighScore() + "";
        Pictures.blackTextHighScoreInGameOver.draw(batcher,
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
        batcher.enableBlending();
        batcher.draw(Pictures.cloud, cloud.getX(), cloud.getY(),
                cloud.getWidth(), cloud.getHeight());
        batcher.draw(Pictures.cloud1, cloud1.getX(), cloud1.getY(),
                cloud1.getWidth(), cloud1.getHeight());
        batcher.draw(Pictures.cloud2, cloud2.getX(), cloud2.getY(),
                cloud2.getWidth(), cloud2.getHeight());
        batcher.draw(Pictures.duckAnimationL.getKeyFrame(runTime),
                duckL.getX(), duckL.getY(), duckL.getWidth(), duckL.getHeight());
        batcher.draw(Pictures.duckAnimationR.getKeyFrame(runTime),
                duckR.getX(), duckR.getY(), duckR.getWidth(), duckR.getHeight());
        batcher.draw(Pictures.coinAnimation.getKeyFrame(runTime), coin1.getX(),
                coin1.getY(), coin1.getWidth(), coin1.getHeight());

        batcher.draw(Pictures.coinAnimation1.getKeyFrame(runTime),
                coin2.getX(), coin2.getY(), coin2.getWidth(), coin2.getHeight());

        batcher.draw(Pictures.coinAnimation2.getKeyFrame(runTime),
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

            shapeRenderer.end();
        }
    }

    private void getTheObjectsFromGameWorld() {

        player = myWorld.getPlayer();
        cloud = myWorld.getCloud();
        cloud1 = myWorld.getCloud1();
        cloud2 = myWorld.getCloud2();
        duckL = myWorld.getDuckLeft();
        duckR = myWorld.getDuckRight();
        coin1 = myWorld.getCoins();
        coin2 = myWorld.getCoin2();
        coin3 = myWorld.getCoin3();
    }

    private void animate(float x, Player p1) {
        if (x > 2 && p1.isTheDudeAlive()) {
            batcher.draw(Pictures.dudeLeft, p1.getX(), p1.getY(),
                    p1.getWidth(), p1.getHeight());
        } else if (x < -2 && p1.isTheDudeAlive()) {
            batcher.draw(Pictures.dudeRight, p1.getX(), p1.getY(),
                    p1.getWidth(), p1.getHeight());
        } else if (!p1.isTheDudeAlive()) {
            batcher.draw(Pictures.dudeDown, p1.getX(), p1.getY(),
                    p1.getWidth(), p1.getHeight());
        } else {
            batcher.draw(Pictures.dudeFront, p1.getX(), p1.getY(), p1.getWidth(),
                    p1.getHeight());
        }

    }
}
