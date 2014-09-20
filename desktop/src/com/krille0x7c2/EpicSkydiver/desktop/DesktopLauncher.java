package com.krille0x7c2.EpicSkydiver.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.krille0x7c2.EpicSkydiver.MyGdxGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.height = 640;
        cfg.width = 360;

        new LwjglApplication(new MyGdxGame(), cfg);
    }

}
