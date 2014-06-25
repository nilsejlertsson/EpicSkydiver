package com.krille0x7c2.EpicSkydiver.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
    public static Sound hitSound, coinSound;
    public static Music themeMusic;

    public static void load() {

        themeMusic = Gdx.audio.newMusic(Gdx.files
                .internal("data/TheBattleOfCloudsByFredrikSigfridsson.mp3"));
        themeMusic.setVolume(0.2f);
        themeMusic.play();
        themeMusic.setLooping(true);

        coinSound = Gdx.audio.newSound(Gdx.files
                .internal("data/collectable.mp3"));
        hitSound = Gdx.audio.newSound(Gdx.files.internal("data/hit.mp3"));


    }

    public void dispose() {
        themeMusic.dispose();
        hitSound.dispose();
        coinSound.dispose();

    }

}
