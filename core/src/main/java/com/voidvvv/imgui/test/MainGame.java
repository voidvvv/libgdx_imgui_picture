package com.voidvvv.imgui.test;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.voidvvv.imgui.test.asset.BasePictureRender;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.manager.CameraController;
import com.voidvvv.imgui.test.manager.CameraManager;
import com.voidvvv.imgui.test.manager.DrawManager;
import com.voidvvv.imgui.test.manager.InputManager;
import com.voidvvv.imgui.test.sreen.mainscreen.MainScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {
    private int frameId;
    public static final String BASIC_FONT_NAME = "font/yizi/yizi.fnt";

    // singleton
    private static MainGame Instance;

    private CameraManager cameraManager;

    private DrawManager drawManager;

    BasePictureRender basePictureRender;

    BasePictureWareHouse basePictureWareHouse;

    AssetManager assetManager;

    CameraController cameraController;

    InputManager inputManager;


    public static MainGame getInstance() {
        if (Instance == null) {
            Instance = new MainGame();
        }
        return Instance;
    }

    @Override
    public void render() {
        frameId++;
        super.render();
    }

    private MainGame() {
        frameId = 0;
        screen = new MainScreen();
        basePictureRender= new BasePictureRender();
        drawManager = new DrawManager();
        cameraManager = new CameraManager();
        basePictureWareHouse = new BasePictureWareHouse();
        assetManager = new AssetManager();
        cameraController = new CameraController();
        inputManager = new InputManager();
    }

    public CameraController getCameraController() {
        return cameraController;
    }

    public void setCameraController(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public DrawManager getDrawManager() {
        return drawManager;
    }

    Screen screen;
    @Override
    public void create() {
        Gdx.input.setInputProcessor(new InputMultiplexer());

        frameId = 0;
        cameraManager.init();
        drawManager.init();
        basePictureRender.init();
        inputManager.init();
//        basePictureRender.init();
//        basePictureWareHouse.init();
        setScreen(screen);
    }

    public BasePictureWareHouse getBasePictureWareHouse() {
        return basePictureWareHouse;
    }

    public BasePictureRender getBasePictureRender() {
        return basePictureRender;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.cameraManager.resize(width, height);
    }

    public int getFrameId() {
        return frameId;
    }
}