package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DrawManager {
    SpriteBatch baseBatch;

    ShapeRenderer shapeRenderer;

    public DrawManager() {

    }

    public void init () {
        baseBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
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

}
