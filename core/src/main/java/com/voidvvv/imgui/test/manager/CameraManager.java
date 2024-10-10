package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.input.MyCameraInputListener;

public class CameraManager {
    public static final int VIEWPORT_WIDTH = 640;
    public static final int VIEWPORT_HEIGHT = 480;
    OrthographicCamera mainCamera;
    OrthographicCamera screenCamera;
    CameraController cameraController;

    public CameraManager() {
        this.mainCamera = new OrthographicCamera();
//        cameraController = new CameraController();
        screenCamera = new OrthographicCamera();
//        float initX = -((float) VIEWPORT_WIDTH / 2f);
//        mainCamera.position.set(0,0, 0);
//        mainCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

    }

    public void init() {
        cameraController = MainGame.getInstance().getCameraController();
    }

    Vector3 v3 = new Vector3();
    public void resize(float width, float height) {
        v3.set(mainCamera.position);
        mainCamera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mainCamera.position.set(v3);
        mainCamera.update();

        screenCamera.setToOrtho(false, width, height);
        screenCamera.update();
    }

    public OrthographicCamera getMainCamera() {
        return mainCamera;
    }

    public OrthographicCamera getScreenCamera() {
        return screenCamera;
    }

    public void update(float delta) {
        cameraController.update(mainCamera, delta);
        screenCamera.update();
    }
}
