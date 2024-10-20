package com.voidvvv.imgui.test.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class BasePicture {
    public int polygonIndexMax = 0;
    public final Sprite originSprite;


    private String name;

    private List<BaseSocket> sockets;

    private List<BasePolygon> basePolygons;

    public float x,y;
    float width,height;
    float originX,originY;
    private float scaleX = 1, scaleY = 1;
    boolean dirty = true;
    float rotation;

    public BasePicture(Texture texture, String name) {
        this.originSprite = new Sprite(texture);
        sockets = new ArrayList<>();
        this.name = name;
        basePolygons = new ArrayList<>();
        x = y = 0f;
        width = texture.getWidth();
        height = texture.getHeight();
        originX = 0f;
        originY = 0f;
        syncToSprite();
    }

    private void syncToSprite() {
        this.originSprite.setPosition(x - width/2, y - height/2);
        this.originSprite.setScale(scaleX,scaleY);
        this.originSprite.setRotation(rotation);
    }

    public List<BasePolygon> getBasePolygons() {
        return basePolygons;
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
        fix();
        return sockets;
    }


    Vector3 tmpV3 = new Vector3();
    Vector3 tmpV3Factor = new Vector3();
    public void addNewSocket(Vector3 world) {
        tmpV3.set(world);
        worldToFactor(tmpV3);
        String name = determineSocketName(tmpV3);
        sockets.add(new BaseSocket(name, tmpV3.x, tmpV3.y, tmpV3Factor.x, tmpV3Factor.y));
    }

    private String determineSocketName(Vector3 tmpV3) {
        return "Socket_" + sockets.size();
    }

    private void worldToFactor(Vector3 tmpV3) {
        final float positionX = x;
        final float positionY = y;
        tmpV3.x -= positionX;
        tmpV3.y -= positionY;
        tmpV3Factor.set(tmpV3);
        final float originX = this.originX;
        final float originY = this.originY;
        final float scaleX = this.scaleX;
        final float scaleY = this.scaleY;
        final boolean scale = scaleX != 1 || scaleY != 1;
        final float rotation = this.rotation;
        final float cos = MathUtils.cosDeg(rotation);
        final float sin = MathUtils.sinDeg(rotation);

        float x = tmpV3.x;
        float y = tmpV3.y;
        // scale if needed
        if (scale) {
            x *= scaleX;
            y *= scaleY;
        }

        // rotate if needed
        if (rotation != 0) {
            float oldX = x;
            x = cos * x - sin * y;
            y = sin * oldX + cos * y;
        }
        tmpV3.x = (positionX + x + originX);
        tmpV3.y = (positionY + y + originY);

    }

    public void fix() {
        if (!dirty) {
            return;
        }
        dirty = false;

        final List<BaseSocket> socketList = this.sockets;
        final float positionX = x;
        final float positionY = y;
        final float originX = this.originX;
        final float originY = this.originY;
        final float scaleX = this.scaleX;
        final float scaleY = this.scaleY;
        final boolean scale = scaleX != 1 || scaleY != 1;
        final float rotation = this.rotation;
        final float cos = MathUtils.cosDeg(rotation);
        final float sin = MathUtils.sinDeg(rotation);

        for (int i = 0; i < socketList.size(); i++) {
            BaseSocket baseSocket = socketList.get(i);
            float x = baseSocket.xStart;
            float y = baseSocket.yStart;
            // scale if needed
            if (scale) {
                x *= scaleX;
                y *= scaleY;
            }

            // rotate if needed
            if (rotation != 0) {
                float oldX = x;
                x = cos * x - sin * y;
                y = sin * oldX + cos * y;
            }
            baseSocket.setX(positionX + x + originX);
            baseSocket.setY(positionY + y + originY);
        }
    }


    public void deleteSocket(int deleteSocketIndex) {
        BaseSocket baseSocket = sockets.get(deleteSocketIndex);
        sockets.remove(baseSocket);
    }



    /** Sets the origin point to which all of the polygon's local vertices are relative to. */
    public void setOrigin (float originX, float originY) {
        this.originX = originX;
        this.originY = originY;
        syncToSprite();
        dirty = true;
    }

    /** Sets the polygon's position within the world. */
    public void setPosition (float x, float y) {
        this.x = x;
        this.y = y;
        syncToSprite();
        dirty = true;
    }


    /** Translates the polygon's position by the specified horizontal and vertical amounts. */
    public void translate (float x, float y) {
        this.x += x;
        this.y += y;
        syncToSprite();
        dirty = true;
    }

    /** Sets the polygon to be rotated by the supplied degrees. */
    public void setRotation (float degrees) {
        this.rotation = degrees;
        syncToSprite();
        dirty = true;
    }

    /** Applies additional rotation to the polygon by the supplied degrees. */
    public void rotate (float degrees) {
        rotation += degrees;
        syncToSprite();
        dirty = true;
    }

    /** Sets the amount of scaling to be applied to the polygon. */
    public void setScale (float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        syncToSprite();
        dirty = true;
    }

    /** Applies additional scaling to the polygon by the supplied amount. */
    public void scale (float amount) {
        this.scaleX += amount;
        this.scaleY += amount;
        syncToSprite();
        dirty = true;
    }

    /** Sets the polygon's world vertices to be recalculated when calling {@link #getTransformedVertices()
     * getTransformedVertices}. */
    public void dirty () {
        dirty = true;
    }

}
