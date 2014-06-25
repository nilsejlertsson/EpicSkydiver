package com.krille0x7c2.EpicSkydiver;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.Screens.SplashScreen;


public class MyGdxGame extends Game implements ApplicationListener, IActivityRequestHandler {


    private IActivityRequestHandler hand;


    public MyGdxGame(IActivityRequestHandler hand) {

        this.hand = hand;


    }


    @Override
    public void create() {

        Pictures.load();
        Preference.load();
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        setScreen(new SplashScreen(hand));


        int midPointY = (int) (gameHeight / 2);

        setScreen(new SplashScreen(hand));


    }

    @Override
    public void dispose() {
        super.dispose();
        Pictures.dispose();

    }


    @Override
    public void showAds(boolean show) {
        // TODO Auto-generated method stub

    }


    @Override
    public void postOnFacebook(boolean show) {
        // TODO Auto-generated method stub

    }


    @Override
    public void showTost(boolean show) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean getSignedInGPGS() {
        return false;
    }

    @Override
    public void loginGPGS() {

    }

    @Override
    public void submitScoreGPGS(int score) {

    }

    @Override
    public void unlockAchievementGPGS(String achievementId) {

    }

    @Override
    public void getLeaderboardGPGS() {

    }

    @Override
    public void getAchievementsGPGS() {

    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void onSignInFailed() {

    }


}
