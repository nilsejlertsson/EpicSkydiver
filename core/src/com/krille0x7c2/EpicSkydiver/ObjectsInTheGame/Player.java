package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Vector2 position, velocity, acceleration;
    private float accelerometerValue;
    private int width, height;
    private int hoverMax = 12;
    private int hoverMin = 2;
    private int hoverUpSpeed = -5;
    private int hoverDownSpeed = 4;
    private int dropDownSpeed = 60;
    private int goBackUpSpeed = -8;
    private int dropDownMax = 40;
    private Rectangle dudeBounds;
    private boolean isAlive;
    private boolean inHoverMode;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 2);
        acceleration = new Vector2(0, 0);
        dudeBounds = new Rectangle();
        isAlive = true;
        inHoverMode = true;

    }

    public void update(float delta) {

        accelerometerValue = Gdx.input.getAccelerometerX();
        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        dudeBounds.set(position.x + 7f, position.y + 4f, 10, 39);
        controlThePlayer();
    }

    private void controlThePlayer() {
        if (position.x > 112) {
            position.x = 112;
        }
        if (position.x < 0) {
            position.x = 0;
        }

        if (accelerometerValue > 2 && isAlive) {
            velocity.x = -60;
            acceleration.x = 30;
        } else if (accelerometerValue < -2 && isAlive) {
            velocity.x = 60;
            acceleration.x = 30;
        } else {
            velocity.x = 0;
            acceleration.x = 0;
        }
        if (velocity.y == dropDownSpeed && position.y > dropDownMax && isAlive) {
            velocity.y = goBackUpSpeed;
        }
        if (velocity.y == goBackUpSpeed && position.y < hoverMax && isAlive) {
            inHoverMode = true;
        }
        if (inHoverMode && isAlive) {
            if (position.y < hoverMin) {
                velocity.y = hoverDownSpeed;
            }
            if (position.y > hoverMax) {
                velocity.y = hoverUpSpeed;
            }
        }
    }

    public void onClick() {
        inHoverMode = false;
        velocity.y = dropDownSpeed;
    }

    public void onMove() {

    }

    public float getAccX() {
        return accelerometerValue;
    }

    public boolean isTheDudeAlive() {
        return isAlive;
    }

    public void onClickLeft() {
        velocity.x = -50;
        acceleration.x = 30;
    }

    public void onClickRight() {
        velocity.x = 50;
        acceleration.x = 30;
    }

    public void setY(float y) {
        position.y = y;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return dudeBounds;
    }

    public void stop() {

        Gdx.input.vibrate(200);
        velocity.y = 50;
        velocity.x = 0;
        acceleration.x = 0;
        acceleration.y = 100;

    }

    public void restart(int y, int midPointY) {

        position.y = y;
        position.x = midPointY - 45;
        velocity.x = 0;
        velocity.y = 2;
        acceleration.x = 0;
        acceleration.y = 0;
        isAlive = true;
        inHoverMode = true;


    }

    public void killDude(boolean kill) {
        isAlive = kill;
    }

}
