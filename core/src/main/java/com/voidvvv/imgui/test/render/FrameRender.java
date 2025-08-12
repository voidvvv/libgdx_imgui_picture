package com.voidvvv.imgui.test.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.frame.AttackCheck;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.input.FrameDataRenderListener;
import com.voidvvv.imgui.test.manager.FrameAttackCheckColorManager;

import java.util.ArrayList;
import java.util.List;

public class FrameRender extends Actor {
    FrameData frameData;

    FrameAttackCheckColorManager colorManager;

    public FrameRender() {
        setName("FrameRender");
        setSize(100, 100);
        setPosition(0, 0);
        frameData = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();
        if (frameData != null) {
            setBounds(
                frameData.getRenderOffset().x,
                frameData.getRenderOffset().y,
                frameData.getTextureRegion().getRegionWidth(),
                frameData.getTextureRegion().getRegionHeight()
            );
        }
        addListener(new FrameDataRenderListener());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        frameData = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();
        colorManager = MainGame.getInstance().getColorManager();
        if (frameData == null) {
            return;
        }
        setBounds(
            frameData.getRenderOffset().x,
            frameData.getRenderOffset().y,
            frameData.getTextureRegion().getRegionWidth(),
            frameData.getTextureRegion().getRegionHeight()
        );
        List<AttackCheck> attackCheckRects = frameData.getAttackCheckRects();
        // attackCheckRects.size()
        colorManager.newColorUntil(
            attackCheckRects.size()
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (frameData == null) {
            return;
        }
        TextureRegion textureRegion = frameData.getTextureRegion();
        Vector2 renderOffset = frameData.getRenderOffset();

        batch.draw(textureRegion,
                   renderOffset.x, renderOffset.y);

        List<AttackCheck> attackCheckRects = frameData.getAttackCheckRects();
        batch.end();
        drawAttackRect(attackCheckRects);
        batch.begin();
    }

    private void drawAttackRect(List<AttackCheck> attackCheckRects) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        ShapeRenderer shapeRenderer = MainGame.getInstance().getDrawManager().getShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(MainGame.getInstance().getCameraManager().getMainCamera().combined);
        shapeRenderer.begin();
        if (!attackCheckRects.isEmpty()) {
            for (int i = 0; i < attackCheckRects.size(); i++) {
                AttackCheck attackCheck = attackCheckRects.get(i);
                Rectangle rectangle = attackCheck.rectangle;

                Color color = colorManager.getColor(i);
                // fill rect
                color.a = 0.3f;
                shapeRenderer.setColor(color);
                shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.rect(
                    rectangle.getX(),
                    rectangle.getY(),
                    rectangle.getWidth(),
                    rectangle.getHeight()
                );

                // line
                color.a = 1f;
                shapeRenderer.setColor(color);
                shapeRenderer.set(ShapeRenderer.ShapeType.Line);
                shapeRenderer.rect(
                    rectangle.getX(),
                    rectangle.getY(),
                    rectangle.getWidth(),
                    rectangle.getHeight()
                );
            }
        }
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

    }

}
