package com.voidvvv.imgui.test.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.voidvvv.imgui.test.manager.CameraController;

public class MyCameraInputListener implements InputProcessor {
    Vector3 lastMousePosition = new Vector3();

    CameraController controller;

    public MyCameraInputListener(CameraController controller) {
        this.controller = controller;
//        InputProcessor inputProcessor = Gdx.input.getInputProcessor();
//        if (inputProcessor instanceof InputMultiplexer) {
//            ((InputMultiplexer) inputProcessor).addProcessor(this);
//        }
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean result = false;
        if (keycode == Input.Keys.DOWN) {
            this.controller.moveDown = true;
            result = true;
        }
        if (keycode == Input.Keys.UP) {
            this.controller.moveUp = true;
            result = true;

        }
        if (keycode == Input.Keys.LEFT) {
            this.controller.moveLeft = true;
            result = true;

        }
        if (keycode == Input.Keys.RIGHT) {
            this.controller.moveRight = true;
            result = true;

        }
        if (keycode == Input.Keys.SPACE) {
            this.controller.reset = true;
            result = true;

        }
        return result;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.DOWN) {
            this.controller.moveDown = false;
        }
        if (keycode == Input.Keys.UP) {
            this.controller.moveUp = false;
        }
        if (keycode == Input.Keys.LEFT) {
            this.controller.moveLeft = false;
        }
        if (keycode == Input.Keys.RIGHT) {
            this.controller.moveRight = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        boolean result = false;
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) {
            this.controller.move.add(screenX - lastMousePosition.x,screenY - lastMousePosition.y,0);
            result = true;
        }
        lastMousePosition.set(screenX, screenY, 0);
        return result;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        lastMousePosition.set(screenX, screenY, 0);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        this.controller.zoomF = amountY;
        return false;
    }
}
