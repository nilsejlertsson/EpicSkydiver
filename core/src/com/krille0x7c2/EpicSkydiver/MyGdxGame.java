package com.krille0x7c2.EpicSkydiver;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.krille0x7c2.EpicSkydiver.Assets.Pictures;
import com.krille0x7c2.EpicSkydiver.Assets.Preference;
import com.krille0x7c2.EpicSkydiver.Screens.SplashScreen;


public class MyGdxGame extends Game implements ApplicationListener {


    public MyGdxGame() {


    }


    @Override
    public void create() {

        Pictures.load();
        Preference.load();
        setScreen(new SplashScreen());


    }

    @Override
    public void dispose() {
        super.dispose();
        Pictures.dispose();

    }


}
