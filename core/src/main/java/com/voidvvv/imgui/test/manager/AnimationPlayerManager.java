package com.voidvvv.imgui.test.manager;

import com.voidvvv.imgui.test.entity.anim.AnimationPlayer;

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
        if (animationPlayer != null && animationPlayer.isPlaying()) {
            animationPlayer.update(deltaTime);
        }
    }
}
