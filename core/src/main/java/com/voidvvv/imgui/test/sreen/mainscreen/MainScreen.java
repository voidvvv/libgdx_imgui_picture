package com.voidvvv.imgui.test.sreen.mainscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.tools.javac.Main;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.asset.BasePictureRender;
import com.voidvvv.imgui.test.data.MainScreenGroundData;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.manager.AnimationPlayerManager;
import com.voidvvv.imgui.test.manager.CameraController;
import com.voidvvv.imgui.test.stage.MainRoot;
import com.voidvvv.imgui.test.stage.MainStage;

public class MainScreen implements Screen {
    MainStage mainStage;

    @Override
    public void show() {
        // init stage
        MainRoot mainRoot = new MainRoot();
        mainRoot.init();
        mainStage = new MainStage(new ScreenViewport(MainGame.getInstance().getCameraManager().getMainCamera()),
            MainGame.getInstance().getDrawManager().getBaseBatch());
        mainStage.setRoot(mainRoot);

        MainGame.getInstance().addInputListener(mainStage);

        // initial asset
        AssetManager assetManager = MainGame.getInstance().getAssetManager();
        assetManager.load("font/yizi/yizi.png",Texture.class);
        assetManager.load(MainGame.BASIC_FONT_NAME, BitmapFont.class);
        assetManager.finishLoading();
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.5f,0.5f,0.5f,1f);
        BasicAnimation basicAnimation = MainGame.getInstance().getAnimationManager().getBasicAnimation();
        if (basicAnimation != null) {
            AnimationPlayerManager animationPlayerManager = MainGame.getInstance().getAnimationPlayerManager();
            MainGame.getInstance().getAnimationManager().update();

            animationPlayerManager.update(delta);

            if (animationPlayerManager.getAnimationPlayer(basicAnimation).isPlaying()) {
                FrameData currentFrame = animationPlayerManager.getAnimationPlayer(basicAnimation).getCurrentFrame();
                MainGame.getInstance().getFrameDataManager().setCurrentFrameData(currentFrame);
            }
        }
        MainGame.getInstance().getCameraManager().update(delta);

        mainStage.act(delta);
        mainStage.draw();

    }


    @Override
    public void resize(int width, int height) {
        mainStage.getViewport().update(width, height);
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
        mainStage.dispose();
    }
}
