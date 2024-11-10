package com.voidvvv.imgui.test.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.FloatArray;


public class BasePolygon extends Polygon {
    public String name;
    FloatArray floatArray;
    FloatArray renderPoint;
    Mesh mesh;

    int pcnt = 0;

    Color color = Color.BLUE;

    public BasePolygon (String name,float x, float y, float originX, float originY) {
        super();
        floatArray = new FloatArray();
        renderPoint = new FloatArray();
        this.name = name;
        this.setPosition(x,y);
        this.setOrigin(originX,originY);
        mesh = new Mesh(true,1000,2000, VertexAttribute.Position());

    }

    public void add(float x, float y) {
        floatArray.add(x, y);
        renderPoint.add(x,y,0f);
        float[] array = floatArray.toArray();
        float[] renderArray = renderPoint.toArray();
        pcnt++;
        mesh.setVertices(renderArray);
        if (pcnt >= 3) {
            super.setVertices(array);
            // fix indices

            short[] indicesArray = new short[(pcnt -2)*3];
            int p = 0;
            for (int i = 0; i < (pcnt -2) ; i++) {
                indicesArray[p++] = (short) i;
                indicesArray[p++] = (short) (i+1);
                indicesArray[p++] = (short) (i+2);
            }
            mesh.setIndices(indicesArray);
        }

    }

    public Color getColor() {
        return color;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public float[] getItems() {

        return floatArray.items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean complete () {
        return super.getVertices().length >= 6;
    }
}
