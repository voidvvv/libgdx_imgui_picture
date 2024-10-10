package com.voidvvv.imgui.test.sreen.mainscreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.asset.BasePictureRender;
import com.voidvvv.imgui.test.data.MainScreenGroundData;
import com.voidvvv.imgui.test.manager.CameraController;

public class MainScreen implements Screen {

    private SpriteBatch batch;
    private Texture image;
    OrthographicCamera camera;
    BackGround backGround;
    MainScreenGroundData data;
    ForeGround foreGround;
    @Override
    public void show() {
        data = new MainScreenGroundData();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        camera = new OrthographicCamera();
        backGround = new BackGround(data);
        foreGround = new ForeGround(data);
//        camera.position.set(0,0,0);
//        camera.update();
        AssetManager assetManager = MainGame.getInstance().getAssetManager();
        assetManager.load("font/yizi/yizi.png",Texture.class);
        assetManager.load(MainGame.BASIC_FONT_NAME, BitmapFont.class);
        assetManager.finishLoading();
    }



    @Override
    public void render(float delta) {
        MainGame.getInstance().getCameraManager().update(delta);
        BasePictureRender basePictureRender = MainGame.getInstance().getBasePictureRender();
        basePictureRender.update(delta);
        backGround.update(delta);
        foreGround.update(delta);
        // draw
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        // back ground
        backGround.draw();
        // picture
        basePictureRender.render();
        foreGround.draw();
    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, 640, 480);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
