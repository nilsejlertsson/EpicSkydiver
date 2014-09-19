package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.krille0x7c2.EpicSkydiver.Enums.Direction;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Duck;

/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public class DuckTemplate implements Duck {


    private Vector2 velocity;
    private Vector2 acceleration;
    private float rotation;
    private int width;
    private int height;
    private Rectangle duckBoundsBody, duckBoundsHead;
    private int vectX, vectY;
    private Direction direction;
    private Vector2 position;

    public DuckTemplate(float x, float y, int width, int height, int vectX, int vectY, Direction direction) {
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.vectX = vectX;
        this.vectY = vectY;
        position = new Vector2(x, y);
        velocity = new Vector2(vectX, vectY);//-30 -20 to right and 30 -20 to left
        acceleration = new Vector2(0, 0);
        duckBoundsHead = new Rectangle();
        duckBoundsBody = new Rectangle();

    }

    public void update(float delta) {
        if (direction.equals(Direction.RIGHT)) {
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
        } else if (direction.equals(Direction.LEFT)) {
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
        if (direction.equals(Direction.RIGHT)) {
            position.x = 150;
            velocity.x = -30;
            velocity.y = -20;
        } else if (direction.equals(Direction.LEFT)) {
            position.x = -50;
            velocity.x = 30;
            velocity.y = -20;
        }
    }

}



