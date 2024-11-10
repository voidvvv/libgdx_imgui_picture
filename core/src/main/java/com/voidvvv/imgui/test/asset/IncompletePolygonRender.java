package com.voidvvv.imgui.test.asset;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.BasePolygon;
import com.voidvvv.imgui.test.render.SimplePolygonRender;

public class IncompletePolygonRender {
    SimplePolygonRender simplePolygonRender;
    OrthographicCamera camera;


    public void init () {
        simplePolygonRender = MainGame.getInstance().getDrawManager().getSimplePolygonRender();
        camera = MainGame.getInstance().getCameraManager().getMainCamera();
    }

    public void render(BasePolygon basePolygon) {
        float[] items = basePolygon.getTransformedVertices();
        simplePolygonRender.draw(basePolygon,camera.combined);

    }
}
