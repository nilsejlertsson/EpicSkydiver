package com.krille0x7c2.EpicSkydiver.Screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.Tween.SpriteAccessorSplash;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;


public class SplashScreen extends Game implements Screen {

    private SpriteBatch batch;
    private Sprite sprite;


    private int gameHight;
    private float gameMidPointY;
    private GameWorld myGame;

    private TextureRegion splashDude;
    private TextureRegion splashTitle;
    private TextureRegion splashBackground;
    private IActivityRequestHandler hand;
    private TweenManager tweenManager;

    public SplashScreen(IActivityRequestHandler hand) {

        this.hand = hand;
        hand.showAds(false);

    }

    @Override
    public void render(float delta) {

        tweenManager.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        sprite.draw(batch);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

        sprite = new Sprite(Pictures.menuTitle2);
        sprite.setColor(1, 1, 1, 0);
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        float desiredWidth = width * .7f;
        float scale = desiredWidth / sprite.getWidth();

        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
                - (sprite.getHeight() / 2));
        setupTween();
        batch = new SpriteBatch();
    }

    private void setupTween() {

        Tween.registerAccessor(Sprite.class, new SpriteAccessorSplash());
        tweenManager = new TweenManager();

        final SplashScreen sp = new SplashScreen(hand);
        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {

                ((Game) Gdx.app.getApplicationListener())

                        .setScreen(new GameMenuScreen(myGame, hand));

            }
        };

        Tween.to(sprite, SpriteAccessorSplash.ALPHA, .8f).target(1)
                .ease(TweenEquations.easeInOutQuad).repeatYoyo(1, 5f)
                .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
                .start(tweenManager);

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
        // batch.dispose();

    }

    @Override
    public void create() {

    }

}
