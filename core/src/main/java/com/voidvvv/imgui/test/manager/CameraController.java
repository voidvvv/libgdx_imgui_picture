package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraController {

    public float zoomF = 0.f;
    public boolean moveLeft = false;
    public boolean moveRight = false;
    public boolean moveUp = false;
    public boolean moveDown = false;
    public boolean reset = false;

    public Vector3 move = new Vector3();

    public float factor = 50.0f;

    public void update (OrthographicCamera camera, float delta) {
        tryZoom(camera,delta);
        tryKeyMove(camera,delta);
        tryMoveMouse(camera,delta);
        tryReset(camera,delta);
        camera.update();
    }

    private void tryReset(OrthographicCamera camera, float delta) {
        if (reset) {
            camera.position.set(0f,0f,0f);
        }
        reset = false;
    }

    private void tryKeyMove(OrthographicCamera camera, float delta) {
        if (moveLeft()) {
            camera.position.x -= factor*delta;
        }

        if (moveRight()) {
            camera.position.x += factor*delta;
        }

        if (moveUp()) {
            camera.position.y += factor*delta;
        }

        if (moveDown()) {
            camera.position.y -= factor*delta;
        }
    }

    private void tryMoveMouse(OrthographicCamera camera, float delta) {
        if (move.len() > 0) {
//            camera.unproject(move);
            camera.position.add(move.x *delta * factor, -(move.y *delta * factor), move.z *delta * factor);
            move.set(0f,0f,0f);
        }
    }

    private void tryZoom(OrthographicCamera camera, float delta) {
        if (zoom()) {
            camera.zoom += factor * delta * zoomF;
            if (camera.zoom <= 0.4f) {
                camera.zoom = 0.4f;
            }
            if (camera.zoom >= 3.0f) {
                camera.zoom = 3.0f;
            }
            this.zoomF = 0f;
        }
    }

    private boolean moveDown() {
        return this.moveDown;
    }

    private boolean moveUp() {
        return this.moveUp;
    }

    private boolean moveRight() {
        return this.moveRight;
    }

    private boolean moveLeft() {
        return this.moveLeft;
    }

    private boolean zoom() {
        return this.zoomF != 0.f;
    }
}
