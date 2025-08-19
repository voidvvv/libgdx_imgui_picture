package com.voidvvv.imgui.test.manager;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.AnimationPlayer;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.entity.frame.FrameData;

import java.util.HashMap;
import java.util.Map;

public class AnimationPlayerManager {

    Map<BasicAnimation, AnimationPlayer> animationPlayerMap = new HashMap<>();

    public AnimationPlayerManager() {

    }

    public AnimationPlayer getAnimationPlayer(BasicAnimation animation) {
        return animationPlayerMap.get(animation);
    }

    public void setAnimationPlayer(BasicAnimation animation, AnimationPlayer animationPlayer) {
        if (animationPlayer == null) {
            throw new IllegalArgumentException("AnimationPlayer cannot be null");
        }
        animationPlayerMap.put(animation, animationPlayer);
        animationPlayer.setAnimation(animation);
    }

    public void setAnimationPlayer(BasicAnimation animation) {
        setAnimationPlayer(animation, new AnimationPlayer());
    }

    public void update(float deltaTime) {
        for (var entry : animationPlayerMap.entrySet()) {
            AnimationPlayer animationPlayer = entry.getValue();
            if (animationPlayer != null) {
                animationPlayer.update(deltaTime);
            }
            if (animationPlayer != null && animationPlayer.isPlaying()) {
                MainGame.getInstance().getFrameDataManager().setCurrentFrameData(animationPlayer.getCurrentFrame());
            }

        }
    }

    public void specifyFrame (int i, BasicAnimation animation) {
        AnimationPlayer animationPlayer = getAnimationPlayer(animation);
        if (animationPlayer == null || animationPlayer.isPlaying()) {
            return;
        }

        FrameData frameData = animationPlayer.setCurrentFrameIndex(i);
        if (frameData != null) {
            MainGame.getInstance().getFrameDataManager().setCurrentFrameData(frameData);
        }
    }
}
