package com.krille0x7c2.EpicSkydiver.ObjectsOutsideTheGame;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CustomButton extends TextButton {
    private Stage stage = new Stage();

    boolean isClicked = false;

    private TextButton button;

    public CustomButton(String text, Skin skin) {
        super(text, skin);


        button = new TextButton(text, skin);

    }

    public void buttonClick() {

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // stage.addAction(Actions.sequence(Actions.moveBy(-stage.getWidth(),
                // 0, 1)));
                isClicked = true;
            }
        });

    }

    public boolean isClicked() {
        if (isClicked) {
            isClicked = false;
            return true;

        } else {
            return false;
        }

    }

    public void addButton() {
        stage.addActor(button);
    }

    public void renderButton(float delta) {
        stage.act(delta);

        stage.draw();
        buttonClick();

    }

}
