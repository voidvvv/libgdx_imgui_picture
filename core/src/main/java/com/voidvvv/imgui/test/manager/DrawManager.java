package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.voidvvv.imgui.test.asset.IncompletePolygonRender;
import com.voidvvv.imgui.test.render.SimplePolygonRender;

public class DrawManager {
    SpriteBatch baseBatch;

    ShapeRenderer shapeRenderer;

    IncompletePolygonRender incompletePolygonRender;

    SimplePolygonRender simplePolygonRender;

    public DrawManager() {
        incompletePolygonRender = new IncompletePolygonRender();
        simplePolygonRender = new SimplePolygonRender();
    }

    public IncompletePolygonRender getIncompletePolygonRender() {
        return incompletePolygonRender;
    }

    public void setIncompletePolygonRender(IncompletePolygonRender incompletePolygonRender) {
        this.incompletePolygonRender = incompletePolygonRender;
    }

    public void init () {
        baseBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        simplePolygonRender.init();
        incompletePolygonRender.init();
    }

    public SpriteBatch getBaseBatch() {
        return baseBatch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public SpriteBatch begin() {
        if (baseBatch.isDrawing()) {
            baseBatch.end();
        }
        baseBatch.begin();
        return baseBatch;
    }

    public SimplePolygonRender getSimplePolygonRender() {
        return simplePolygonRender;
    }

    public void setSimplePolygonRender(SimplePolygonRender simplePolygonRender) {
        this.simplePolygonRender = simplePolygonRender;
    }
}
