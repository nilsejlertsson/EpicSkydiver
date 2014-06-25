package com.krille0x7c2.EpicSkydiver.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Preference {
    public static Preferences prefs;

    public static void load() {
        prefs = Gdx.app.getPreferences("skydiver");
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
    }

    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }


    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

}
