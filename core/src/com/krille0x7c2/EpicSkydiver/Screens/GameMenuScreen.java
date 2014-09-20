package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;

/**
 * Created by Christian Bodelsson
 */

public class GameMenuScreen implements Screen {


    private Stage stage;
    private SpriteBatch spriteBatch;
    private OrthographicCamera orthographicCamera;
    private TextureRegion background, menuDude, menuHand, menuTitle, turnRight, turnLeft;
    private TextButton playButton, buttonShare, buttonCredits, buttonScore;
    private TextButtonStyle textButtonStyle;

    public GameMenuScreen(final GameWorld gameWorld) {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(false);
        initAssets();
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(true, 136, 204);
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);
        playButton = new TextButton("Play", textButtonStyle);
        buttonScore = new TextButton("Score", textButtonStyle);
        buttonCredits = new TextButton("Credits", textButtonStyle);
        buttonShare = new TextButton("Share", textButtonStyle);
        stage.addActor(playButton);
        stage.addActor(buttonScore);
        stage.addActor(buttonCredits);
        stage.addActor(buttonShare);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stage.addAction(Actions.sequence(
                        Actions.moveBy(-stage.getWidth(), 0, 1),
                        Actions.run(new Runnable() {

                            @Override
                            public void run() {
                                ((Game) Gdx.app.getApplicationListener())

                                        .setScreen(new GameScreen());

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

                                        .setScreen(new CreditScreen(gameWorld));

                            }
                        })
                ));
            }
        });
       /* buttonScore.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                if (hand.getSignedInGPGS()) {
                    hand.getLeaderboardGPGS();
                } else {
                    ((Game) Gdx.app.getApplicationListener())

                            .setScreen(new ScoreScreen());
                }


            }
        });
        buttonShare.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                hand.postOnFacebook(true);
            }
        });*/

    }

    private void initAssets() {

        background = Pictures.sky;
        turnLeft = Pictures.turnLeft;
        turnRight = Pictures.turnRight;
        menuTitle = Pictures.menuTitle;
        menuHand = Pictures.menuHand;
        menuDude = Pictures.menuDude;

        textButtonStyle = Pictures.textButtonStyle;

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 136, 204);
        spriteBatch.draw(menuTitle, 20, 0, 100, 30);
        spriteBatch.draw(menuDude, 44, 35, 50, 80);
        spriteBatch.draw(turnLeft, 0, 35, 40, 40);
        spriteBatch.draw(turnRight, 96, 35, 40, 40);
        spriteBatch.draw(menuHand, 96, 80, 40, 40);
        spriteBatch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        playButton.setSize(width / 2, height / 8);
        buttonScore.setSize(width / 2, height / 8);
        buttonCredits.setSize(width / 2, height / 8);
        buttonShare.setSize(width / 2, height / 8);

        playButton.setPosition((width / 4) - (playButton.getWidth() / 2),
                (height / 3) - playButton.getHeight());

        buttonScore
                .setPosition(
                        (width / 4) - (playButton.getWidth() / 2),
                        ((height / 3) - playButton.getHeight())
                                - (playButton.getHeight() + playButton
                                .getHeight() / 3)
                );

        buttonCredits.setPosition((width - width / 4)
                        - (playButton.getWidth() / 2),
                (height / 3) - playButton.getHeight()
        );

        buttonShare
                .setPosition(
                        (width - width / 4) - (playButton.getWidth() / 2),
                        ((height / 3) - playButton.getHeight())
                                - (playButton.getHeight() + playButton
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


    }

}
