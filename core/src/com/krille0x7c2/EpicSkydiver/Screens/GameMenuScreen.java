package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.MyGdxGame;


public class GameMenuScreen implements Screen {

    private GameWorld myGame;
    private MyGdxGame game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture splashTexture;
    private Sprite splash;

    private Skin skin;
    private OrthographicCamera cam;
    private TextureRegion background;
    private TextureRegion turnLeft;
    private TextureRegion turnRight;
    private TextureRegion menuTitle;
    private TextureRegion menuHand;
    private TextureRegion menuDude;
    private TextureAtlas atlasplay, atlasCredits, atlasScore, atlasShare;
    private TextButton buttonPlay, buttonShare, buttonCredits, buttonScore;
    private IActivityRequestHandler hand;
    private BitmapFont black;
    private TextureAtlas atlas;
    private TextButtonStyle textButtonStyle;


    public GameMenuScreen(final GameWorld myGame,
                          final IActivityRequestHandler hand) {
        // this.nativeFunctions = nativeFunctions;
        stage = new Stage();
        this.hand = hand;

        hand.showAds(false);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);

        initAssets();

        float gameHeight = setUpSize();

        int midPointY = (int) (gameHeight / 2);

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        buttonPlay = new TextButton("Play", textButtonStyle);
        buttonScore = new TextButton("Score", textButtonStyle);
        buttonCredits = new TextButton("Credits", textButtonStyle);
        buttonShare = new TextButton("Share", textButtonStyle);

        stage.addActor(buttonPlay);
        stage.addActor(buttonScore);
        stage.addActor(buttonCredits);
        stage.addActor(buttonShare);

        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(
                        Actions.moveBy(-stage.getWidth(), 0, 1),
                        Actions.run(new Runnable() {

                            @Override
                            public void run() {
                                ((Game) Gdx.app.getApplicationListener())

                                        .setScreen(new GameScreen(hand));

                            }
                        })
                ));
            }
        });

        buttonCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(
                        Actions.moveBy(-stage.getWidth(), 0, 1),
                        Actions.run(new Runnable() {

                            @Override
                            public void run() {
                                ((Game) Gdx.app.getApplicationListener())

                                        .setScreen(new CreditScreen(myGame, hand));

                            }
                        })
                ));
            }
        });
        buttonScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                if (hand.getSignedInGPGS()) {
                    hand.getLeaderboardGPGS();
                } else {
                    ((Game) Gdx.app.getApplicationListener())

                            .setScreen(new ScoreScreen(hand));
                }


            }
        });
        buttonShare.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                hand.postOnFacebook(true);
            }
        });

    }

    private float setUpSize() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        return gameHeight;
    }

    private void initAssets() {

        background = Pictures.sky;
        turnLeft = Pictures.turnLeft;
        turnRight = Pictures.turnRight;
        menuTitle = Pictures.menuTitle;
        menuHand = Pictures.menuHand;
        menuDude = Pictures.menuDude;
        //skin = Pictures.skin;
        textButtonStyle = Pictures.textButtonStyle;

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        batch.draw(background, 0, 0, 136, 204);
        batch.draw(menuTitle, 20, 0, 100, 30);
        batch.draw(menuDude, 44, 35, 50, 80);
        batch.draw(turnLeft, 0, 35, 40, 40);
        batch.draw(turnRight, 96, 35, 40, 40);
        batch.draw(menuHand, 96, 80, 40, 40);

        batch.end();

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        buttonPlay.setSize(width / 2, height / 8);
        buttonScore.setSize(width / 2, height / 8);
        buttonCredits.setSize(width / 2, height / 8);
        buttonShare.setSize(width / 2, height / 8);

        buttonPlay.setPosition((width / 4) - (buttonPlay.getWidth() / 2),
                (height / 3) - buttonPlay.getHeight());

        buttonScore
                .setPosition(
                        (width / 4) - (buttonPlay.getWidth() / 2),
                        ((height / 3) - buttonPlay.getHeight())
                                - (buttonPlay.getHeight() + buttonPlay
                                .getHeight() / 3)
                );

        buttonCredits.setPosition((width - width / 4)
                        - (buttonPlay.getWidth() / 2),
                (height / 3) - buttonPlay.getHeight()
        );

        buttonShare
                .setPosition(
                        (width - width / 4) - (buttonPlay.getWidth() / 2),
                        ((height / 3) - buttonPlay.getHeight())
                                - (buttonPlay.getHeight() + buttonPlay
                                .getHeight() / 3)
                );


    }

    @Override
    public void show() {

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

    }

    @Override
    public void dispose() {
        stage.dispose();
        atlasplay.dispose();

        splashTexture.dispose();

    }

}
