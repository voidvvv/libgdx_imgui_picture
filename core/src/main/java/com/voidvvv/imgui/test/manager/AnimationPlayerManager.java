package com.voidvvv.imgui.test.manager;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.AnimationPlayer;
import com.voidvvv.imgui.test.entity.frame.FrameData;

public class AnimationPlayerManager {
    AnimationPlayer animationPlayer;

    public AnimationPlayerManager() {
        this.animationPlayer = new AnimationPlayer();
    }

    public AnimationPlayer getAnimationPlayer() {
        return animationPlayer;
    }

    public void setAnimationPlayer(AnimationPlayer animationPlayer) {
        this.animationPlayer = animationPlayer;
    }

    public void update(float deltaTime) {
        if (animationPlayer != null) {
            animationPlayer.update(deltaTime);

        }
        if (animationPlayer != null && animationPlayer.isPlaying()) {
            MainGame.getInstance().getFrameDataManager().setCurrentFrameData(animationPlayer.getCurrentFrame());
        }
    }

    public void specifyFrame (int i) {
        if (animationPlayer != null && animationPlayer.isPlaying()) {
            return;
        }
        if (animationPlayer == null) {
            animationPlayer = MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer();
        }
        FrameData frameData = animationPlayer.setCurrentFrameIndex(i);
        if (frameData != null) {
            MainGame.getInstance().getFrameDataManager().setCurrentFrameData(frameData);
        }
    }
}
