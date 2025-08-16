package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.entity.frame.FrameData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class AnimationManager {
    BasicAnimation basicAnimation;

    public AnimationManager() {

    }

    public BasicAnimation getBasicAnimation() {
        return basicAnimation;
    }

    public void setBasicAnimation(BasicAnimation basicAnimation) {
        this.basicAnimation = basicAnimation;
    }

    public void loadAnimByPic(Texture tx) {
        TextureRegion tr = new TextureRegion(tx);
        FrameData frameData = new FrameData();
        frameData.setTextureRegion(tr);
        basicAnimation = new BasicAnimation(frameData);
    }

    public void loadAnimByAtlas(FileHandle fh, TextureAtlas textureAtlas) {
        if (textureAtlas == null) {
            Gdx.app.error("AnimationManager", "TextureAtlas is null");
            return;
        }
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        TreeSet<FrameData> frameDatas = new TreeSet<>(Comparator.comparing(a -> nameOfTexture(a.getTextureRegion())));
        for (TextureAtlas.AtlasRegion region : regions) {
            FrameData frameData = new FrameData();
            frameData.setTextureRegion(region);
            frameData.setDurationTime(0.1f);// default
            frameDatas.add(frameData);
        }
        if (frameDatas.isEmpty()) {
            Gdx.app.error("AnimationManager", "No regions found in TextureAtlas");
            return;
        }
        basicAnimation = new BasicAnimation(frameDatas);
        if (MainGame.getInstance().getFrameDataManager().getCurrentFrameData() == null) {
            MainGame.getInstance().getFrameDataManager().setCurrentFrameData(basicAnimation.getFrame(0));
        }
    }

    static public String nameOfTexture (TextureRegion textureRegion) {
        if (textureRegion == null) {
            return "";
        }
        if (textureRegion instanceof TextureAtlas.AtlasRegion ar) {
            return ar.name + "_" + ar.index;
        }
        return "TextureRegion (" + textureRegion.getTexture().getWidth() + "x" +
            textureRegion.getTexture().getHeight() + ")";

    }
}
