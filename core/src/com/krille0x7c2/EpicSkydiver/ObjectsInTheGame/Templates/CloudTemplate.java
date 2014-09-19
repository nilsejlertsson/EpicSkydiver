package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates;


import com.badlogic.gdx.math.Vector2;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Cloud;

public class CloudTemplate implements Cloud {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    public CloudTemplate(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, -35);
        acceleration = new Vector2(0, 0);

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        if (position.y < -35) {
            position.y = 250;
        }

    }

    public void stop() {
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

    public void restart() {
        velocity.x = 0;
        velocity.y = -30;
    }

}
