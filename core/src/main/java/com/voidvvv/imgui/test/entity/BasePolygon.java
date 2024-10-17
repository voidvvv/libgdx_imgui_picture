package com.voidvvv.imgui.test.entity;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.FloatArray;


public class BasePolygon extends Polygon {
    public String name;
    FloatArray floatArray;

    public BasePolygon (String name,float x, float y, float originX, float originY) {
        super();
        floatArray = new FloatArray();
        this.name = name;
        this.setPosition(x,y);
        this.setOrigin(originX,originY);
    }

    public void add(float x, float y) {
        floatArray.add(x, y);
        if (floatArray.size >= 6) {
            super.setVertices(floatArray.toArray());
        }
    }

    public boolean complete () {
        return super.getVertices().length >= 6;
    }
}
