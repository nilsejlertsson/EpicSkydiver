package com.krille0x7c2.EpicSkydiver.InputControls;


import com.badlogic.gdx.InputProcessor;
import com.krille0x7c2.EpicSkydiver.GameWorld.GameWorld;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Player;

/**
 * Created by Christian Bodelsson
 */

public class HandleTouchOnScreen implements InputProcessor {

    private Player player;
    private GameWorld gameWorld;

    public HandleTouchOnScreen(Player player, GameWorld gameWorld) {

        this.player = player;
        this.gameWorld = gameWorld;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (gameWorld.isPaused()) {
            gameWorld.resume();
        } else if (gameWorld.isGameOver()) {
            gameWorld.restart();
        } else {
            player.onClick();
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
