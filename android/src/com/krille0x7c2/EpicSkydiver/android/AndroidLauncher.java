package com.krille0x7c2.EpicSkydiver.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.krille0x7c2.EpicSkydiver.InterfaceCallbacks.IActivityRequestHandler;
import com.krille0x7c2.EpicSkydiver.MyGdxGame;
import com.krille0x7c2.EpicSkydiver.R;

import java.util.List;


public class AndroidLauncher extends AndroidApplication implements IActivityRequestHandler, GameHelper.GameHelperListener {


    private static Context mContext;
    private MyGdxGame myGame;
    protected AdView adView;
    IActivityRequestHandler myRequestHandler;
    public GameHelper gameHelper;
    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;
    private final int POST_TO_FACEBOOK = 2;
    private final int POST_TOAST = 3;
    private final boolean DEBUG_BUILD = true;


    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_ADS: {
                    adView.setVisibility(View.VISIBLE);
                    break;
                }
                case HIDE_ADS: {
                    adView.setVisibility(View.GONE);
                    break;
                }
                case POST_TO_FACEBOOK: {
                    postFacebook();
                    break;
                }
                case POST_TOAST: {
                    showTostInGame();
                    break;
                }

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameHelper = new GameHelper(this, GameHelper.CLIENT_ALL);
        gameHelper.enableDebugLog(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
//        cfg.useAccelerometer=true;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);


        setContentView(R.layout.main);
        AndroidLauncher.mContext = getApplicationContext();

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainLayout);


        myGame = new MyGdxGame(this);
        //initialize(myGame, false);
        View gameView = initializeForView(myGame, cfg);
        gameHelper.setup(this);


        adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-2759887838881651/8517685724");
        adView.setAdSize(AdSize.SMART_BANNER);


//        AdRequest adRequest = new AdRequest.Builder()
//        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Emulator
//        .addTestDevice("4AC8E617D2E54B186007737033E857AC") // My  Nexus test
//        .build();


        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        layout.addView(gameView);

        // Add the AdMob view
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(adView, adParams);

        setContentView(layout);


    }

    @Override
    public void onStart() {
        super.onStart();
        gameHelper.onStart(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        gameHelper.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean getSignedInGPGS() {
        // TODO Auto-generated method stub
        return gameHelper.isSignedIn();
    }

    @Override
    public void loginGPGS() {
        // TODO Auto-generated method stub
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (final Exception ex) {
        }
    }

    @Override
    public void submitScoreGPGS(int score) {
        // TODO Auto-generated method stub
        Games.Leaderboards.submitScore(gameHelper.getApiClient(),
                getString(R.string.leaderboard_highscore), score);

    }

    @Override
    public void unlockAchievementGPGS(String achievementId) {
        // TODO Auto-generated method stub
        Games.Achievements.unlock(gameHelper.getApiClient(), achievementId);
    }


    @Override
    public void getLeaderboardGPGS() {
        // TODO Auto-generated method stub

        startActivityForResult(
                Games.Leaderboards.getLeaderboardIntent(
                        gameHelper.getApiClient(),
                        getString(R.string.leaderboard_highscore)), 100
        );
    }

    @Override
    public void getAchievementsGPGS() {
        // TODO Auto-generated method stub
        startActivityForResult(
                Games.Achievements.getAchievementsIntent(gameHelper
                        .getApiClient()), 101
        );
    }

    @Override
    public void onSignInFailed() {


    }

    @Override
    public void onSignInSucceeded() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /**
     * Called before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


    @Override
    public void showAds(boolean show) {
        handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);

    }

    @Override
    public void postOnFacebook(boolean show) {

        handler.sendEmptyMessage(show ? POST_TO_FACEBOOK : HIDE_ADS);

    }


    public void postFacebook() {
        String urlToShare = "https://play.google.com/store/apps/details?id=com.krille0x7c2.EpicSkydiver";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

        boolean facebookAppFound = false;
        List<ResolveInfo> matches = getPackageManager().queryIntentActivities(
                intent, 0);
        for (ResolveInfo info : matches) {
            if (info.activityInfo.packageName.toLowerCase().startsWith(
                    "com.facebook")) {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }

        // If no app installed
        if (!facebookAppFound) {
            String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u="
                    + urlToShare;
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
        }

        startActivity(intent);
    }


    @Override
    public void showTost(boolean show) {
        handler.sendEmptyMessage(show ? POST_TOAST : HIDE_ADS);

    }

    public void showTostInGame() {
        Toast.makeText(this, "This is a Toast message.", Toast.LENGTH_LONG).show();

    }

}