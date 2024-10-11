package com.voidvvv.imgui.test.asset;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.PageData;
import com.voidvvv.imgui.test.manager.CameraManager;

import java.util.ArrayList;
import java.util.List;

public class BasePictureRender {

    PageData pageData = new PageData();

    float panelYFixFactor = 0.5f;
    float panelHeightFactor = 0.9f;
    float panelWidthFactor = 0.2f;

    float picXFactor = 0.85f;
    float picYFactor = 0.93f;

    float totalHeight = CameraManager.VIEWPORT_HEIGHT * panelHeightFactor;
    float totalWidth = CameraManager.VIEWPORT_WIDTH * panelWidthFactor;

    float penalX = 0f;
    float penalY = 0f;

    public void update(float delta) {
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        if (basePictureWareHouse.addNewSocketFlag) {
            basePictureWareHouse.addNewSocket();
        }

    }



    public void render() {
//        renderPenal();
        renderMainPicture();
    }

    private void renderMainPicture() {
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        BasePicture mainPicture = basePictureWareHouse.getMainPicture();
        if (mainPicture == null) {
            return;
        }
        ShapeRenderer shapeRenderer = MainGame.getInstance().getDrawManager().getShapeRenderer();
        SpriteBatch batch = MainGame.getInstance().getDrawManager().getBaseBatch();

        mainPicture.render(batch, shapeRenderer);

    }

    public void init() {

    }
}
