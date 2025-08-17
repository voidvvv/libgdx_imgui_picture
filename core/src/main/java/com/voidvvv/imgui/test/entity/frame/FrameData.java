package com.voidvvv.imgui.test.entity.frame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class FrameData {

    String name;
    Vector2 renderOffset = new Vector2(0, 0);
    List<AttackCheck> attackCheckRects = new ArrayList<>();

    TextureRegion textureRegion;
    private float durationTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2 getRenderOffset() {
        return renderOffset;
    }

    public void setRenderOffset(Vector2 renderOffset) {
        this.renderOffset = renderOffset;
    }

    public List<AttackCheck> getAttackCheckRects() {
        return attackCheckRects;
    }

    public void setAttackCheckRects(List<AttackCheck> attackCheckRects) {
        this.attackCheckRects = attackCheckRects;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public float getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(float durationTime) {
        this.durationTime = durationTime;
    }
}
