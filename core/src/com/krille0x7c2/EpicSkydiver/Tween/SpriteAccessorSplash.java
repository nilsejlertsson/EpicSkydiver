package com.krille0x7c2.EpicSkydiver.Tween;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;
/**
 * Created by Christian Bodelsson on 9/19/14.
 * email:krille0x7c2@gmail.com
 */
public class SpriteAccessorSplash implements TweenAccessor<Sprite> {


    public static final int ALPHA = 1;

    @Override
    public int getValues(Sprite target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case ALPHA:
                returnValues[0] = target.getColor().a;
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public void setValues(Sprite target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case ALPHA:
                target.setColor(1, 1, 1, newValues[0]);
                break;
        }
    }

}



