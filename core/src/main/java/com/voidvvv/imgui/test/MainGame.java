package com.voidvvv.imgui.test;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.voidvvv.imgui.test.asset.BasePictureRender;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.manager.*;
import com.voidvvv.imgui.test.operations.OperationStack;
import com.voidvvv.imgui.test.sreen.mainscreen.MainScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainGame extends Game {

    StatusManager statusManager;

    OperationStack operationStack;

    private int frameId;
    public static final String BASIC_FONT_NAME = "font/yizi/yizi.fnt";

    // singleton
    private static MainGame Instance;

    private CameraManager cameraManager;

    private DrawManager drawManager;

    BasePictureRender basePictureRender;

    BasePictureWareHouse basePictureWareHouse;

    AssetManager assetManager;
    AssetManager absoluteAssetManager = new AssetManager(new AbsoluteFileHandleResolver());

    CameraController cameraController;

    InputManager inputManager;

    FrameDataManager frameDataManager;

    DebugManager debugManager = new DebugManager();

    FrameAttackCheckColorManager colorManager;

    AnimationManager animationManager;

    AnimationPlayerManager animationPlayerManager;


    SplitImageManager splitImageManager;

    WorkContextManager contextManager;
    public AnimationPlayerManager getAnimationPlayerManager() {
        return animationPlayerManager;
    }

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

        operationStack = new OperationStack();
        statusManager = new StatusManager();
        frameDataManager = new FrameDataManager();
        colorManager = new FrameAttackCheckColorManager();
        animationManager = new AnimationManager();
        animationPlayerManager = new AnimationPlayerManager();
        splitImageManager = new SplitImageManager();
        contextManager = new WorkContextManager();
    }

    public WorkContextManager getContextManager() {
        return contextManager;
    }

    public SplitImageManager getSplitImageManager() {
        return splitImageManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public FrameAttackCheckColorManager getColorManager() {
        return colorManager;
    }

    public StatusManager getStatusManager() {
        return statusManager;
    }

    public void setStatusManager(StatusManager statusManager) {
        this.statusManager = statusManager;
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

    InputMultiplexer inputProcessor ;
    @Override
    public void create() {
        inputProcessor = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputProcessor);

        frameId = 0;
        cameraManager.init();
        drawManager.init();
        basePictureRender.init();
        inputManager.init();
        frameDataManager.init();
//        basePictureRender.init();
//        basePictureWareHouse.init();
        setScreen(screen);

    }

    public DebugManager getDebugManager() {
        return debugManager;
    }

    public FrameDataManager getFrameDataManager() {
        return frameDataManager;
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
//        this.cameraManager.resize(width, height);
    }

    public AssetManager getAbsoluteAssetManager() {
        return absoluteAssetManager;
    }

    public OperationStack getOperationStack() {
        return operationStack;
    }

    public int getFrameId() {
        return frameId;
    }

    public void addInputListener(InputProcessor processor) {
        inputProcessor.addProcessor(processor);
    }
}
