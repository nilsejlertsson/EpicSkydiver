package com.krille0x7c2.EpicSkydiver.Assets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Christian Bodelsson
 */
public class Pictures {


    public static Animation duckAnimationL, duckAnimationR, coinAnimation,
            coinAnimation1, coinAnimation2;
    public static BitmapFont whiteText, blackTextScoreOnScreen,
            blackTextHighScoreInGameOver, blackTextScoreInGameOver,
            whiteHighScore, whiteHighScoreTitle;
    public static Texture texture;

    public static TextureRegion dudeFront, dudeLeft, dudeRight, dudeDown,
            duckUpLeft, duckLeft, duckUpRight, duckRight, sky, cloud, cloud1,
            cloud2, pauseWindow, gameOverWindow, coinFront, coin2,
            coin3, coin4, creditsText, menuDude, menuTitle, turnLeft, turnRight, menuHand, menuTitle2;

    public static Skin skin;
    public static TextureAtlas atlas;
    public static BitmapFont black;
    public static TextButton.TextButtonStyle textButtonStyle;

    public static void load() {
        loadTexture();
        loadSky();
        loadFonts();
        loadTextureAtlas();
        loadMenu();
        loadClouds();
        loadDucks();
        loadCoins();
        loadTheDude();
        animateDucks();
        animateCoin();
        loadCredits();
        loadPauseScreen();
        loadGameOverScreen();

    }

    private static void loadTextureAtlas() {

        black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);
        atlas = new TextureAtlas("button/button.pack");
        skin = new Skin(atlas);
        black.setScale(Gdx.graphics.getDensity() / 2, Gdx.graphics.getDensity() / 2);
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;


    }


    private static void loadSky() {
        sky = new TextureRegion(texture, 1200, 1410, 272, 408);
    }

    private static void loadDucks() {
        duckLeft = new TextureRegion(texture, 106, 246, 105, 51);
        duckLeft.flip(false, true);

        duckUpLeft = new TextureRegion(texture, 1, 246, 105, 51);
        duckUpLeft.flip(false, true);

        duckRight = new TextureRegion(texture, 316, 246, 105, 51);
        duckRight.flip(false, true);

        duckUpRight = new TextureRegion(texture, 211, 246, 105, 51);
        duckUpRight.flip(false, true);
    }

    private static void loadTheDude() {

        dudeRight = new TextureRegion(texture, 0, 0, 131, 246);
        dudeRight.flip(false, true);

        dudeFront = new TextureRegion(texture, 263, 0, 131, 246);
        dudeFront.flip(false, true);

        dudeLeft = new TextureRegion(texture, 131, 0, 131, 246);
        dudeLeft.flip(false, true);

        dudeDown = new TextureRegion(texture, 395, 0, 131, 246);
        dudeDown.flip(false, true);
    }

    private static void loadCredits() {

        creditsText = new TextureRegion(texture, 1496, 0, 552, 2048);
        creditsText.flip(false, true);
    }

    private static void loadCoins() {
        coinFront = new TextureRegion(texture, 0, 1073, 128, 128);
        coinFront.flip(false, true);

        coin2 = new TextureRegion(texture, 128, 1073, 128, 128);
        coin2.flip(false, true);

        coin3 = new TextureRegion(texture, 256, 1073, 128, 128);
        coin3.flip(false, true);
        coin4 = new TextureRegion(texture, 384, 1073, 128, 128);
        coin4.flip(false, true);
    }

    private static void loadFonts() {
        whiteText = new BitmapFont(Gdx.files.internal("font/white.fnt"));
        whiteText.setScale(.30f, -.30f);

        blackTextScoreOnScreen = new BitmapFont(
                Gdx.files.internal("font/white.fnt"));
        blackTextScoreOnScreen.setScale(.10f, -.10f);
        Pictures.blackTextScoreOnScreen.setColor(1, 1, 1, 1);

        blackTextScoreInGameOver = new BitmapFont(
                Gdx.files.internal("font/white.fnt"));
        blackTextScoreInGameOver.setScale(.25f, -.25f);
        blackTextScoreInGameOver.setColor(0, 0, 0, 1);

        blackTextHighScoreInGameOver = new BitmapFont(
                Gdx.files.internal("font/white.fnt"));
        blackTextHighScoreInGameOver.setScale(.2f, -.2f);
        blackTextHighScoreInGameOver.setColor(0, 0, 0, 1);

        whiteHighScore = new BitmapFont(Gdx.files.internal("font/white.fnt"));
        whiteHighScore.setScale(.40f, -.40f);

        whiteHighScoreTitle = new BitmapFont(Gdx.files.internal("font/white.fnt"));
        whiteHighScoreTitle.setScale(.17f, -.17f);

    }

    private static void animateDucks() {
        TextureRegion[] ducksL = {duckUpLeft, duckLeft};
        duckAnimationL = new Animation(0.08f, ducksL);
        duckAnimationL.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] ducksR = {duckUpRight, duckRight};
        duckAnimationR = new Animation(0.08f, ducksR);
        duckAnimationR.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public static void animateCoin() {

        TextureRegion[] coins = {coinFront, coin2, coin3, coin4};
        coinAnimation = new Animation(0.08f, coins);
        coinAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] coin_2 = {coinFront, coin2, coin3, coin4};
        coinAnimation1 = new Animation(0.08f, coin_2);
        coinAnimation1.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        TextureRegion[] coin_3 = {coinFront, coin2, coin3, coin4};
        coinAnimation2 = new Animation(0.08f, coin_3);
        coinAnimation2.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    }

    private static void loadClouds() {
        cloud = new TextureRegion(texture, 0, 299, 202, 156);
        cloud.flip(false, true);
        cloud1 = new TextureRegion(texture, 203, 299, 182, 156);
        cloud1.flip(false, true);
        cloud2 = new TextureRegion(texture, 386, 299, 257, 156);
        cloud2.flip(false, true);
    }

    private static void loadMenu() {

        turnLeft = new TextureRegion(texture, 655, 0, 385, 470);
        turnLeft.flip(false, true);

        turnRight = new TextureRegion(texture, 1040, 0, 385, 470);
        turnRight.flip(false, true);

        menuTitle = new TextureRegion(texture, 563, 1900, 912, 148);
        menuTitle.flip(false, true);

        menuTitle2 = new TextureRegion(texture, 563, 1900, 912, 148);


        menuHand = new TextureRegion(texture, 674, 568, 498, 515);
        menuHand.flip(false, true);

        menuDude = new TextureRegion(texture, 0, 1204, 481, 844);
        menuDude.flip(false, true);

    }

    private static void loadTexture() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    }

    private static void loadPauseScreen() {
        pauseWindow = new TextureRegion(texture, 541, 1267, 633, 624);
        pauseWindow.flip(false, true);
    }

    private static void loadGameOverScreen() {
        gameOverWindow = new TextureRegion(texture, 0, 456, 643, 617);
        gameOverWindow.flip(false, true);
    }

    public static void dispose() {
        texture.dispose();
        atlas.dispose();
        skin.dispose();
        whiteText.dispose();
        blackTextScoreOnScreen.dispose();
        blackTextScoreInGameOver.dispose();
        blackTextScoreOnScreen.dispose();
        whiteHighScore.dispose();
    }

}
