package com.voidvvv.imgui.test.entity;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.voidvvv.imgui.test.MainGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePicture {
    public final Sprite originSprite;


    private String name;

    private List<BaseSocket> sockets;

    private Map<BaseSocket, List<BaseRect>> rects;

    public BasePicture(Texture texture, String name) {
        this.originSprite = new Sprite(texture);
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
        renderSockets(shapeRenderer);
        renderRects(shapeRenderer);
    }

    private void renderSockets(ShapeRenderer shapeRenderer) {
        Camera camera = MainGame.getInstance().getCameraManager().getMainCamera();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for (BaseSocket socket : sockets) {
            shapeRenderer.circle(socket.getX(),socket.getY(),5f);
        }
        shapeRenderer.end();
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
        return originSprite.getTexture().getTextureObjectHandle();
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

    Vector3 tmpV3 = new Vector3();
    Vector3 tmpV3Factor = new Vector3();
    public void addNewSocket(Vector3 world) {
        tmpV3.set(world);
        worldToFactor(tmpV3, tmpV3Factor);
        String name = determineSocketName(tmpV3);
        sockets.add(new BaseSocket(name,tmpV3Factor.x,tmpV3Factor.y, tmpV3.x, tmpV3.y));
    }

    private String determineSocketName(Vector3 tmpV3) {
        return "Socket_" + sockets.size();
    }

    private void worldToFactor(Vector3 tmpV3,Vector3 tmpV3Factor) {
        float tmpX = tmpV3.x;
        float tmpY = tmpV3.y;

        Rectangle rectangle = this.originSprite.getBoundingRectangle();
        float x = rectangle.getX();
        float y = rectangle.getY();
        float width = rectangle.getWidth();
        float height = rectangle.getHeight();
        float originX = this.originSprite.getOriginX();
        float originY = this.originSprite.getOriginY();

        float worldOriginX = x + originX;
        float worldOriginY = y + originY;

        tmpV3Factor.x = Math.abs(tmpX - worldOriginX) / width;
        tmpV3Factor.y = Math.abs(tmpY - worldOriginY) / height;
        tmpV3Factor.z = 0;
    }
}
