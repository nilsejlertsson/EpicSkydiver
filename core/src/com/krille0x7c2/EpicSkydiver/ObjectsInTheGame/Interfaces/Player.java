package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public interface Player {


    public void update(float delta);

    public void onClick();

    public void onMove();

    public float getAccX();

    public boolean isTheDudeAlive();

    public void onClickLeft();

    public void onClickRight();

    public float getX();

    public float getY();

    public void setY(float y);

    public float getWidth();

    public float getHeight();

    public Rectangle getBounds();

    public void stop();

    public void restart(int y, int midPointY);

    public void killDude(boolean kill);

}
