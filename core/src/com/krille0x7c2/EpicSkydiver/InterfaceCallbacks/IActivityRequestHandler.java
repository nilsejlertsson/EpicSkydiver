package com.krille0x7c2.EpicSkydiver.InterfaceCallbacks;

public interface IActivityRequestHandler {
    public void showAds(boolean show);


    public void postOnFacebook(boolean show);

    public void showTost(boolean show);

    public boolean getSignedInGPGS();

    public void loginGPGS();

    public void submitScoreGPGS(int score);

    public void unlockAchievementGPGS(String achievementId);

    public void getLeaderboardGPGS();

    public void getAchievementsGPGS();

    public void onSignInSucceeded();

    public void onSignInFailed();

}