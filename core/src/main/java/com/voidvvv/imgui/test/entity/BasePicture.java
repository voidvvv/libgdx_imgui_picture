package com.voidvvv.imgui.test.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.voidvvv.imgui.test.MainGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePicture {
    public final Sprite originSprite;

    public final Texture originTexture;

    private String name;

    private List<BaseSocket> sockets;

    private Map<BaseSocket, List<BaseRect>> rects;

    public BasePicture(Texture texture, String name) {
        this.originSprite = new Sprite(texture);
        this.originTexture = texture;
        sockets = new ArrayList<>();
        rects = new HashMap<>();
        this.name = name;
    }

    public void render (SpriteBatch batch, ShapeRenderer shapeRenderer) {
        Camera camera = MainGame.getInstance().getCameraManager().getMainCamera();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        originSprite.draw(batch);
        batch.end();
        renderRects(shapeRenderer);
    }

    private void renderRects(ShapeRenderer shapeRenderer) {
        Camera camera = MainGame.getInstance().getCameraManager().getMainCamera();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin();
        for (var entry : rects.entrySet()) {
            List<BaseRect> value = entry.getValue();
            BaseSocket key = entry.getKey();
            renderDot(key,shapeRenderer);
            for (BaseRect rect : value) {
                renderRect(key,rect,shapeRenderer);
            }
        }
        shapeRenderer.end();
    }

    private void renderRect(BaseSocket key, BaseRect rect, ShapeRenderer shapeRenderer) {
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
    }

    private void renderDot(BaseSocket key, ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }


    public int getTextureId () {
        return originTexture.getTextureObjectHandle();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseSocket> getSockets() {
        return sockets;
    }

    public void setSockets(List<BaseSocket> sockets) {
        this.sockets = sockets;
    }

    public Map<BaseSocket, List<BaseRect>> getRects() {
        return rects;
    }

    public void setRects(Map<BaseSocket, List<BaseRect>> rects) {
        this.rects = rects;
    }
}
