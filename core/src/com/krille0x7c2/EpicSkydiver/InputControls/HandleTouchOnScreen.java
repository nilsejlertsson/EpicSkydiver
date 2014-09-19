package com.krille0x7c2.EpicSkydiver.InputControls;


import com.badlogic.gdx.InputProcessor;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Player;


public class HandleTouchOnScreen implements InputProcessor {

    private Player myPlayer;
    private GameWorld world;

    public HandleTouchOnScreen(Player player, GameWorld world) {

        myPlayer = player;
        this.world = world;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (world.isPaused()) {
            world.resume();
        } else if (world.isGameOver()) {
            world.restart();
        } else {
            myPlayer.onClick();
        }

        return true;
    }

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
