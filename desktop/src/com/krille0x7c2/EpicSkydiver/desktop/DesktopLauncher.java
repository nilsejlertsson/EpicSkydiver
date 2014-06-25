package com.krille0x7c2.EpicSkydiver.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.MyGdxGame;

public class DesktopLauncher implements IActivityRequestHandler {
    public void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        new LwjglApplication(new MyGdxGame(this), config);
    }

    @Override
    public void showAds(boolean show) {

    }

    @Override
    public void postOnFacebook(boolean show) {

    }

    @Override
    public void showTost(boolean show) {

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
