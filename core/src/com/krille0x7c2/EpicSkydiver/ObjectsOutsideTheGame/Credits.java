package com.krille0x7c2.EpicSkydiver.ObjectsOutsideTheGame;


import com.badlogic.gdx.math.Vector2;

public class Credits {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float x;
    private float rotation;
    private int width;
    private int height;

    public Credits(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, -20);
        acceleration = new Vector2(0, 0);

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        if (position.y < -255) {
            position.y = 215;
        }

    }

    public float getAccX() {
        return x;
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

}