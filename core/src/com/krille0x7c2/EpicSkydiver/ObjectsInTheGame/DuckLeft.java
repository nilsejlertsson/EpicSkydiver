package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame;

import java.util.Random;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DuckLeft {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    Random random = new Random();
    int Low = 20;
    int High = 204;
    int r = random.nextInt(High - Low) + Low;
    private Rectangle duckBoundsBody, duckBoundsHead;

    public DuckLeft(float x, float y, int width, int height) {

        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(30, -20);
        acceleration = new Vector2(0, 0);
        duckBoundsBody = new Rectangle();
        duckBoundsHead = new Rectangle();

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));
        position.add(velocity.cpy().scl(delta));
        duckBoundsBody.set(position.x + 2f, position.y + 2f, 8, 13);
        duckBoundsHead.set(position.x + 4f, position.y + 8f, 15, 4);
        if (position.x > 160) {
            position.x = -50;
            position.y = MathUtils.random(0, 204 - 30);
        }
        if (position.y < -55) {
            position.y = MathUtils.random(0, 204 - 30);
            position.x = -50;

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
        position.x = -50;
        velocity.x = 30;
        velocity.y = -20;
    }

}
