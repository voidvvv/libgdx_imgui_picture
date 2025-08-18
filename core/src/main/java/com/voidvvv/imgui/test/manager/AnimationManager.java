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

import java.util.*;

public class AnimationManager {
    BasicAnimation basicAnimation;

    List<BasicAnimation> animations = new ArrayList<>();

    Map<String, BasicAnimation> animationMap = new java.util.HashMap<>();

    public AnimationManager() {

    }

    public List<BasicAnimation> getAnimations() {
        return animations;
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
        frameData.setName(nameOfTexture(tr));
        basicAnimation = new BasicAnimation(frameData);
    }

    public void loadAnimByAtlas(TextureAtlas textureAtlas) {
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
            frameData.setName(nameOfTexture(region));
            frameDatas.add(frameData);
        }
        if (frameDatas.isEmpty()) {
            Gdx.app.error("AnimationManager", "No regions found in TextureAtlas");
            return;
        }
        BasicAnimation currentAnim = new BasicAnimation(frameDatas);
        addAnimation("name", currentAnim);

    }

    public void addAnimation (String name) {
        BasicAnimation currentAnim = new BasicAnimation();
        addAnimation(name, currentAnim);
    }

    private void addAnimation(String name, BasicAnimation currentAnim) {
        if (animationMap.containsKey(name)) {
            Gdx.app.error("AnimationManager", "Animation with name " + name + " already exists");
            return;
        }
        currentAnim.setName(name);
        animations.add(currentAnim);
        animationMap.put(name, currentAnim);
        if (basicAnimation == null) {
            basicAnimation = currentAnim;
        }
        if (MainGame.getInstance().getFrameDataManager().getCurrentFrameData() == null && currentAnim.getFrameCount() > 0) {
            basicAnimation = currentAnim;
            MainGame.getInstance().getFrameDataManager().setCurrentFrameData(currentAnim.getFrame(0));
        }
        MainGame.getInstance().getAnimationPlayerManager().setAnimationPlayer(currentAnim);
    }

    public void update() {
        realDeleteFrameInCurAnim();
        realSwitchAnim();
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

    public void deleteFrame(int i) {
        deleteId = i;
    }
    private int deleteId = -1;
    private void realDeleteFrameInCurAnim() {
        if (deleteId < 0) {
            return;
        }
        int i = deleteId;
        deleteId = -1;
        if (basicAnimation == null || basicAnimation.getFrameCount() <= i) {
            Gdx.app.error("AnimationManager", "BasicAnimation is null or index out of bounds");
            return;
        }
        FrameData needDelete = basicAnimation.getFrame(i);
        if (needDelete == null) {
            return;
        }
        basicAnimation.removeFrame(needDelete);
        if (MainGame.getInstance().getFrameDataManager().getCurrentFrameData() == needDelete) {
            MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer(basicAnimation).setCurrentFrameIndex(0);
            MainGame.getInstance().getFrameDataManager().setCurrentFrameData(basicAnimation.getFrame(0));
        }
        if (MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer(basicAnimation).getCurrentFrameIndex() > i) {
            MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer(basicAnimation).setCurrentFrameIndex(
                MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer(basicAnimation).getCurrentFrameIndex() - 1);

        }
    }

    public void trySwitchAnim(BasicAnimation anim) {
        waitToBeSwitchAnim = anim;
    }
    private BasicAnimation waitToBeSwitchAnim = null;
    private void realSwitchAnim () {
        if (waitToBeSwitchAnim == null) {
            return;
        }
        BasicAnimation oldAnim = MainGame.getInstance().getAnimationManager().getBasicAnimation();
        if (oldAnim != null) {
            MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer(oldAnim).stop();
        }
        MainGame.getInstance().getAnimationManager().setBasicAnimation(waitToBeSwitchAnim);
        waitToBeSwitchAnim = null;
    }

    public void deleteAnimation(BasicAnimation anim) {

    }
}
