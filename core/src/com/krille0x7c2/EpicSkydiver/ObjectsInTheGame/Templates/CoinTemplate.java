package com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Templates;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.krille0x7c2.EpicSkydiver.ObjectsInTheGame.Interfaces.Coin;

/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public class CoinTemplate implements Coin {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float x;
    private float rotation;
    private int width;
    private int height;
    private int vectorInit;
    private int randomSeed;
    private Rectangle coinBounds;

    public CoinTemplate(float x, float y, int width, int height, int vectorInit, int randomSeed) {
        this.width = width;
        this.height = height;
        this.vectorInit = vectorInit;
        this.randomSeed = randomSeed;
        position = new Vector2(x, y);
        velocity = new Vector2(0, vectorInit);
        acceleration = new Vector2(0, 0);
        coinBounds = new Rectangle();

    }

    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        coinBounds.set(position.x + 0f, position.y + 0f, 5, 5);

        if (position.y < -5) {
            position.y = 215;
            position.x = MathUtils.random(10, 136 - 30);
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

    public Rectangle getBounds() {
        return coinBounds;
    }

    public void removeCoin() {
        position.x = 150;
    }

    public void randomPosition() {
        position.y = 215;
        position.x = MathUtils.random(10, 136 - randomSeed);

    }

    public void restart() {
        position.y = 204;
        position.x = 50;
    }

}
