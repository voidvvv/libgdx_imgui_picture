package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.entity.frame.FrameDetailInfo;

public class FrameDataManager {
    FrameData currentFrameData;
    FrameDetailInfo frameDetailInfo;

    public FrameData getCurrentFrameData() {
        return currentFrameData;
    }

    public void setCurrentFrameData(FrameData currentFrameData) {
        this.currentFrameData = currentFrameData;
    }

    public FrameDetailInfo getFrameDetailInfo() {
        return frameDetailInfo;
    }

    public void setFrameDetailInfo(FrameDetailInfo frameDetailInfo) {
        this.frameDetailInfo = frameDetailInfo;
    }

    public void setImg(String path) {
        initFrameData(path);
    }

    private void initFrameData(String path) {
        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.absolute(path)));
        currentFrameData = new FrameData();
        currentFrameData.setTextureRegion(tr);
    }

    public void init() {

    }


    public void reset () {

    }
}
