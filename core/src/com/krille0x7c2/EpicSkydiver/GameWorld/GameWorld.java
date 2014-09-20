package com.krille0x7c2.EpicSkydiver.GameWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.Assets.Sounds;
import com.krille0x7c2.EpicSkydiver.Enums.State;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Cloud;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Coin;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Duck;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Player;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates.CloudTemplate;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates.CoinTemplate;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates.DuckTemplate;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates.PlayerTemplate;
import com.krille0x7c2.EpicSkydiver.Screens.GameMenuScreen;

import static com.krille0x7c2.EpicSkydiver.Enums.Direction.LEFT;
import static com.krille0x7c2.EpicSkydiver.Enums.Direction.RIGHT;
import static com.krille0x7c2.EpicSkydiver.Enums.State.GAMEOVER;
import static com.krille0x7c2.EpicSkydiver.Enums.State.HIGHSCORE;
import static com.krille0x7c2.EpicSkydiver.Enums.State.PAUSE;
import static com.krille0x7c2.EpicSkydiver.Enums.State.RUN;

/**
 * Created by Christian Bodelsson
 */

public class GameWorld {

    private Player player;

    private Cloud cloud1, cloud2, cloud3;
    private Duck duckRight, duckLeft;
    private Coin coin1, coin2, coin3;
    private int midPointY;
    private int score;
    private int checker;
    private boolean fromGame;
    private State state;

    public GameWorld(int midPointY) {
        this.midPointY = midPointY;

        player = new PlayerTemplate(midPointY - 45, 2, 24, 45);
        cloud1 = new CloudTemplate(50, 110, 60, 35);
        cloud2 = new CloudTemplate(10, 50, 60, 35);
        cloud3 = new CloudTemplate(30, 180, 60, 35);
        duckLeft = new DuckTemplate(40, 150, 20, 15, 30, -20, LEFT);
        duckRight = new DuckTemplate(136, 190, 20, 15, -30, -20, RIGHT);
        coin1 = new CoinTemplate(MathUtils.random(10, 136 - 30), 230, 5, 5, -20, 20);
        coin2 = new CoinTemplate(MathUtils.random(10, 136 - 10), 220, 5, 5, -25, 50);
        coin3 = new CoinTemplate(MathUtils.random(10, 136 - 60), 210, 5, 5, -30, 50);
        state = RUN;
        score = 0;
        checker = 0;
        fromGame = false;
    }

    public void update(float delta) {

        switch (state) {

            case GAMEOVER:
                checkPauseOnPause();

                break;
            case PAUSE:
                checkPauseOnPause();
                break;

            case RUN:
            default:
                runTheGame(delta);

                checkPause();
                break;

        }

    }

    private void runTheGame(float delta) {

        updatePlayer(delta);
        updateClouds(delta);
        updateDucks(delta);

        updateCoins(delta);

        if (Intersector.overlaps(player.getBounds(), duckLeft.getBoundsBody())
                || Intersector.overlaps(player.getBounds(),
                duckLeft.getBoundsHead())) {
            if (score > Preference.getHighScore()) {
                Preference.setHighScore(score);

                state = HIGHSCORE;
            }
            removeCoins();
            stopClouds();
            stopDucks();
            stopPlayer();

            onlyPlayHitOnce(checker);
            checker++;

            Sounds.themeMusic.stop();

        }
        if (Intersector.overlaps(player.getBounds(), duckRight.getBoundsBody())
                || Intersector.overlaps(player.getBounds(),
                duckRight.getBoundsHead())) {
            if (score > Preference.getHighScore()) {
                Preference.setHighScore(score);

                state = HIGHSCORE;
            }
            removeCoins();
            stopClouds();
            stopDucks();
            stopPlayer();

            onlyPlayHitOnce(checker);
            checker++;
            Sounds.themeMusic.stop();

        }
        if (Intersector.overlaps(player.getBounds(), coin1.getBounds())
                && player.isTheDudeAlive()) {

            coin1.randomPosition();

            Sounds.coinSound.play(0.1f);

            addScore(1);

        }
        if (Intersector.overlaps(player.getBounds(), coin2.getBounds())
                && player.isTheDudeAlive()) {

            coin2.randomPosition();
            Sounds.coinSound.play(0.1f);

            addScore(1);

        }
        if (Intersector.overlaps(player.getBounds(), coin3.getBounds())
                && player.isTheDudeAlive()) {

            coin3.randomPosition();
            Sounds.coinSound.play(0.1f);

            addScore(1);

        }

        if (player.getY() > 204) {
            state = GAMEOVER;

            player.setY(20);

        }

    }


    private void removeCoins() {
        coin1.removeCoin();
        coin2.removeCoin();
        coin3.removeCoin();
    }

    private void stopPlayer() {
        player.killDude(false);
        player.stop();
    }

    private void stopDucks() {
        duckLeft.stop();
        duckRight.stop();
    }

    private void stopClouds() {
        cloud1.stop();
        cloud2.stop();
        cloud3.stop();
    }

    private void updatePlayer(float delta) {
        player.update(delta);
    }

    private void updateClouds(float delta) {
        cloud1.update(delta);
        cloud2.update(delta);
        cloud3.update(delta);
    }

    private void updateDucks(float delta) {
        duckLeft.update(delta);
        duckRight.update(delta);
    }

    private void updateCoins(float delta) {
        coin1.update(delta);
        coin2.update(delta);
        coin3.update(delta);
    }

    private boolean checkIfPressed() {

        return Gdx.input.isKeyPressed(Keys.BACK)
                || Gdx.input.isKeyPressed(Keys.ENTER);
    }

    private boolean checkPressed() {
        return Gdx.input.isKeyPressed(Keys.BACK)
                || Gdx.input.isKeyPressed(Keys.ENTER);
    }

    private void checkPause() {
        if (checkPressed()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = PAUSE;

        }
    }


    private void checkPauseOnPause() {
        if (checkIfPressed()) {
            Sounds.themeMusic.stop();
            fromGame = true;


            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameMenuScreen(this));


        }
    }

    public boolean getStat() {
        return fromGame;
    }

    private void onlyPlayHitOnce(int x) {
        if (x == 0) {
            Sounds.hitSound.play(0.1f);
            x++;
        }
    }

    public void setStateRun() {
        state = RUN;
    }

    public void setStatePause() {
        state = PAUSE;
    }

    public boolean isHighScore() {
        return state == HIGHSCORE;
    }

    public boolean isGameOver() {

        return state == GAMEOVER;
    }

    public boolean isPaused() {
        return state == PAUSE;
    }

    public boolean isRunning() {
        return state == RUN;
    }

    public void resume() {
        state = RUN;
        Sounds.themeMusic.play();

    }

    public void restart() {

        restartPlayer();
        restartDucks();
        restartClouds();
        randomCoins();
        resetScore();
        checker = 0;

        state = RUN;
        Sounds.themeMusic.play();

    }

    private void restartPlayer() {
        player.restart(3, midPointY);
    }

    private void restartDucks() {
        duckLeft.restart();
        duckRight.restart();
    }

    private void resetScore() {
        score = 0;
    }

    private void randomCoins() {
        coin1.randomPosition();
        coin2.randomPosition();
        coin3.randomPosition();
    }

    private void restartClouds() {
        cloud1.restart();
        cloud2.restart();
        cloud3.restart();
    }

    public Player getPlayer() {
        return player;

    }

    public Cloud getCloud1() {
        return cloud1;
    }

    public Cloud getCloud2() {
        return cloud2;
    }

    public Cloud getCloud3() {
        return cloud3;
    }

    public Duck getDuckLeft() {
        return duckLeft;
    }

    public Duck getDuckRight() {
        return duckRight;

    }

    public Coin getCoins() {
        return coin1;
    }

    public Coin getCoin3() {
        return coin3;
    }

    public Coin getCoin2() {
        return coin2;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int add) {
        score += add;
    }

}
