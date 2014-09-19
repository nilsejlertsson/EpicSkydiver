package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public interface Duck {
    public void restart();

    public Rectangle getBoundsHead();

    public Rectangle getBoundsBody();

    public float getRotation();

    public float getHeight();

    public float getWidth();

    public float getY();

    public float getX();

    public void stop();

    public void fallAndDie();

    public void update(float delta);
}
