package com.krille0x7c2.EpicSkydiver.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.krille0x7c2.EpicSkydiver.Assets.Sounds;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameRenderer;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.InputControls.HandleTouchOnScreen;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;


public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private float runTime;


    private IActivityRequestHandler hand;

    public GameScreen(IActivityRequestHandler hand) {


        this.hand = hand;
        Sounds.load();

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);


        world = new GameWorld(midPointY, hand);
        renderer = new GameRenderer(world, (int) gameHeight, midPointY, hand);

        Gdx.input.setInputProcessor(new HandleTouchOnScreen(world.getPlayer(), world));
        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
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
