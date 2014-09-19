package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public interface Coin {
    public void update(float delta);

    public float getAccX();

    public float getX();

    public float getY();

    public float getWidth();

    public float getHeight();

    public float getRotation();

    public Rectangle getBounds();

    public void removeCoin();

    public void randomPosition();

    public void restart();
}
