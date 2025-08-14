package com.voidvvv.imgui.test.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.entity.frame.FrameData;

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
}
