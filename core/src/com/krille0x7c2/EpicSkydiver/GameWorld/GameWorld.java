package com.krille0x7c2.EpicSkydiver.GameWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.Assets.Sounds;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Clouds;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Coin1;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Coin2;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Coin3;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.DuckLeft;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.DuckRight;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Player;
import com.krille0x7c2.EpicSkydiver.Screens.GameMenuScreen;


public class GameWorld {

    private Player player;

    private Clouds cloud, cloud1, cloud2;
    private DuckLeft duckLeft;
    private DuckRight duckRight;
    private Coin1 coin1;
    private Coin2 coin2;
    private Coin3 coin3;
    private int midPointY;
    private int score = 0;
    private int checker = 0;
    private IActivityRequestHandler hand;
    private boolean fromGame = false;

    public enum State {
        RUN, PAUSE, GAMEOVER, HIGHSCORE
    }

    private State state;

    public GameWorld(int midPointY, IActivityRequestHandler hand) {
        this.midPointY = midPointY;
        this.hand = hand;
        player = new Player(midPointY - 45, 2, 24, 45);
        cloud = new Clouds(50, 110, 60, 35);
        cloud1 = new Clouds(10, 50, 60, 35);
        cloud2 = new Clouds(30, 180, 60, 35);
        duckLeft = new DuckLeft(40, 150, 20, 15);
        duckRight = new DuckRight(136, 190, 20, 15);
        coin1 = new Coin1(MathUtils.random(10, 136 - 30), 230, 5, 5);
        coin2 = new Coin2(MathUtils.random(10, 136 - 10), 220, 5, 5);
        coin3 = new Coin3(MathUtils.random(10, 136 - 60), 210, 5, 5);
        state = State.RUN;
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
                if (hand.getSignedInGPGS()) {
                    hand.submitScoreGPGS(score);
                }
                state = State.HIGHSCORE;
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
                if (hand.getSignedInGPGS()) {
                    hand.submitScoreGPGS(score);
                }
                state = State.HIGHSCORE;
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
            state = State.GAMEOVER;

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
        cloud.stop();
        cloud1.stop();
        cloud2.stop();
    }

    private void updatePlayer(float delta) {
        player.update(delta);
    }

    private void updateClouds(float delta) {
        cloud.update(delta);
        cloud1.update(delta);
        cloud2.update(delta);
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

        if (Gdx.input.isKeyPressed(Keys.BACK)
                || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            return true;

        }
        return false;
    }

    private boolean checkPressed() {
        if (Gdx.input.isKeyPressed(Keys.BACK)
                || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            return true;
        }
        return false;
    }

    private void checkPause() {
        if (checkPressed()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state = State.PAUSE;

        }
    }


    private void checkPauseOnPause() {
        if (checkIfPressed()) {
            Sounds.themeMusic.stop();
            fromGame = true;


            ((Game) Gdx.app.getApplicationListener()).setScreen(new GameMenuScreen(this, hand));


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
        state = State.RUN;
    }

    public void setStatePause() {
        state = State.PAUSE;
    }

    public boolean isHighScore() {
        return state == State.HIGHSCORE;
    }

    public boolean isGameOver() {

        return state == State.GAMEOVER;
    }

    public boolean isPaused() {
        return state == State.PAUSE;
    }

    public boolean isRunning() {
        return state == State.RUN;
    }

    public void resume() {
        state = State.RUN;
        Sounds.themeMusic.play();

    }

    public void restart() {

        restartPlayer();
        restartDucks();
        restartClouds();
        randomCoins();
        resetScore();
        checker = 0;

        state = State.RUN;
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
        cloud.restart();
        cloud1.restart();
        cloud2.restart();
    }

    public Player getPlayer() {
        return player;

    }

    public Clouds getCloud() {
        return cloud;
    }

    public Clouds getCloud1() {
        return cloud1;
    }

    public Clouds getCloud2() {
        return cloud2;
    }

    public DuckLeft getDuckLeft() {
        return duckLeft;
    }

    public DuckRight getDuckRight() {
        return duckRight;

    }

    public Coin1 getCoins() {
        return coin1;
    }

    public Coin3 getCoin3() {
        return coin3;
    }

    public Coin2 getCoin2() {
        return coin2;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int add) {
        score += add;
    }

}
