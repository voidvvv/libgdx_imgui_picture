package com.voidvvv.imgui.test.manager;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.input.MyCameraInputListener;

public class InputManager {
    public void init () {
        new MyCameraInputListener(MainGame.getInstance().getCameraController());

    }
}
