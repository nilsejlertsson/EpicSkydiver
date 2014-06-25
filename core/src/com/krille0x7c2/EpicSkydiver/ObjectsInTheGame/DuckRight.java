package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DuckRight {
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    private Rectangle duckBoundsBody, duckBoundsHead;

    private Vector2 position;

    public DuckRight(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(-30, -20);
        acceleration = new Vector2(0, 0);
        duckBoundsHead = new Rectangle();
        duckBoundsBody = new Rectangle();

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        duckBoundsBody.set(position.x + 10f, position.y + 2f, 8, 13);
        duckBoundsHead.set(position.x + 1f, position.y + 8f, 15, 4);
        if (position.x < -40) {
            position.x = 180;
            position.y = MathUtils.random(0, 204 - 30);

        }
        if (position.y < -55) {
            position.y = MathUtils.random(0, 204 - 30);
            position.x = 180;

        }

    }

    public void fallAndDie() {
        velocity.y = 50;
        acceleration.y = 50;
    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
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

    public float getRotation() {
        return rotation;
    }

    public Rectangle getBoundsBody() {
        return duckBoundsBody;
    }

    public Rectangle getBoundsHead() {
        return duckBoundsHead;
    }

    public void restart() {
        position.x = 150;
        velocity.x = -30;
        velocity.y = -20;
    }

}
