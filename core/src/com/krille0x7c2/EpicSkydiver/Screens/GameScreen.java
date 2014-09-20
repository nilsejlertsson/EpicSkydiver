package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.krille0x7c2.EpicSkydiver.Assets.Sounds;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameRenderer;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.InputControls.HandleTouchOnScreen;


public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private GameRenderer gameRenderer;
    private float runTime;


    public GameScreen() {

        Sounds.load();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);
        gameWorld = new GameWorld(midPointY);
        gameRenderer = new GameRenderer(gameWorld, (int) gameHeight, midPointY);
        Gdx.input.setInputProcessor(new HandleTouchOnScreen(gameWorld.getPlayer(), gameWorld, midPointY));
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        gameWorld.update(delta);
        gameRenderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }


    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {


    }

}
