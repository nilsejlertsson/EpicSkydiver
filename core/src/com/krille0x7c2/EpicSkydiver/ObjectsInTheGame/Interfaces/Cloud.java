package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces;

/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public interface Cloud {
    public void update(float delta);

    public void stop();

    public float getX();

    public float getY();

    public float getWidth();

    public float getHeight();

    public float getRotation();

    public void restart();
}
