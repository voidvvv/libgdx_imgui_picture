package com.voidvvv.imgui.test.asset;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BasePolygon;
import com.voidvvv.imgui.test.entity.BaseSocket;
import com.voidvvv.imgui.test.entity.PageData;
import com.voidvvv.imgui.test.manager.CameraManager;

import java.util.ArrayList;
import java.util.List;

public class BasePictureRender {

    PageData pageData = new PageData();


    public void update(float delta) {
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        boolean pressed = basePictureWareHouse.pressed;
        if (pressed) {
            if (basePictureWareHouse.shouldAdd) {
                basePictureWareHouse.addNewSocket();
            }else if (basePictureWareHouse.shouldAddToPolygon) {
                basePictureWareHouse.addNewSocketToPolygon();
            }
        }

        // reset
        basePictureWareHouse.pressed = false;
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

        render(batch, shapeRenderer, mainPicture);
    }


    public void render (SpriteBatch batch, ShapeRenderer shapeRenderer, BasePicture basePicture) {
        drawBasePicture(batch,basePicture);
        renderSockets(shapeRenderer, basePicture);
        renderRects(shapeRenderer, basePicture);
    }

    private void drawBasePicture(SpriteBatch batch, BasePicture basePicture) {
        Camera camera = MainGame.getInstance().getCameraManager().getMainCamera();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        basePicture.originSprite.draw(batch);
        batch.end();
    }

    private void renderSockets(ShapeRenderer shapeRenderer, BasePicture basePicture) {
        Camera camera = MainGame.getInstance().getCameraManager().getMainCamera();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (int x= 0; x<basePicture.getSockets().size(); x++) {
            var socket = basePicture.getSockets().get(x);
            shapeRenderer.circle(socket.getX(),socket.getY(),5f);
        }
        shapeRenderer.end();
    }

    private void renderRects(ShapeRenderer shapeRenderer, BasePicture basePicture) {
        Camera camera = MainGame.getInstance().getCameraManager().getMainCamera();
        prepareForShape(shapeRenderer, camera);

        for (int i = 0; i < basePicture.getBasePolygons().size(); i++) {
            BasePolygon basePolygon = basePicture.getBasePolygons().get(i);
            if (basePolygon.complete()) {
                float[] transformedVertices = basePolygon.getTransformedVertices();
                shapeRenderer.polygon(transformedVertices);
            }
        }
        shapeRenderer.end();
    }

    private void prepareForShape(ShapeRenderer shapeRenderer, Camera camera) {
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
    }

    private void renderRect(BaseSocket key, BasePolygon rect, ShapeRenderer shapeRenderer) {
//        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
    }



    public void init() {

    }
}
