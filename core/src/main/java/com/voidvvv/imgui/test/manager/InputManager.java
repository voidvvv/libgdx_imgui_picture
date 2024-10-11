package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.input.BasePictureInputListener;
import com.voidvvv.imgui.test.input.MyCameraInputListener;

public class InputManager {
    public void init () {
        new MyCameraInputListener(MainGame.getInstance().getCameraController());
        BasePictureInputListener basePictureInputListener = new BasePictureInputListener();
        addInputProcessor(basePictureInputListener);
    }

    public void addInputProcessor(InputProcessor in) {
        InputProcessor inputProcessor = Gdx.input.getInputProcessor();
        if (inputProcessor instanceof InputMultiplexer) {
            ((InputMultiplexer) inputProcessor).addProcessor(in);
        }
    }
}
