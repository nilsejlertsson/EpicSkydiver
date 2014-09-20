package com.krille0x7c2.EpicSkydiver.CreditWorld;


import com.krille0x7c2.EpicSkydiver.ObjectsOutsideTheGame.Credits;

/**
 * Created by Christian Bodelsson
 */
public class CreditWorld {

    private Credits cred;

    public CreditWorld() {
        cred = new Credits(10, 204, 120, 250);
    }

    public void update(float delta) {
        cred.update(delta);
    }

    public Credits getCred() {
        return cred;
    }

}
