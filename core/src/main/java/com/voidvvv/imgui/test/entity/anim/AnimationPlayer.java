package com.voidvvv.imgui.test.entity.anim;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.frame.FrameData;

public class AnimationPlayer {
    BasicAnimation animation;

    boolean loop = true;

    boolean playing = false;

    float currentTime = 0f;

    float durationTime = 0f;

    FrameData currentFrame;

    int currentFrameIndex = 0;

    public void play () {

        if (animation == null) {
            throw new IllegalStateException("Animation is not set.");
        }
        playing = true;
        // Reset animation state if needed
        resetAnimation();
    }

    private void resetAnimation() {
        currentTime = 0f;
        durationTime = 0f;
        currentFrameIndex = 0;
        currentFrame = animation.getFrame(0);
        MainGame.getInstance().getFrameDataManager().setCurrentFrameData(currentFrame);
    }

    public void stop () {
        playing = false;
        resetAnimation();
    }

    public void pause () {
        playing = false;
        // Optionally, you can keep the current state to resume later
        // currentTime and durationTime will still be updated
    }

    public void resume () {
        if (animation == null) {
            throw new IllegalStateException("Animation is not set.");
        }
        playing = true;
        // Resume from the current state
        // currentTime and durationTime will continue from where they left off
    }

    public void update(float delta) {
        if (animation == null) {
            animation = MainGame.getInstance().getAnimationManager().getBasicAnimation();

        }
        if (animation == null || !playing) {
            return;
        }

        currentTime += delta;
        durationTime += delta;
        if (durationTime >= currentFrame.getDurationTime()) {
            switchNextFrameOrEnd();
        }
    }

    public FrameData getCurrentFrame() {
        return currentFrame;
    }

    public boolean isPlaying() {
        return playing;
    }

    private void switchNextFrameOrEnd() {
        if (currentFrameIndex < animation.getFrameCount() - 1) {
            currentFrameIndex++;
        } else if (loop) {
            currentTime = 0f;
            currentFrameIndex = 0;
        } else {
            stop();
        }
        durationTime = 0f;
        currentFrame = animation.getFrame(currentFrameIndex);
    }

    public void setAnimation (BasicAnimation animation) {
        this.animation = animation;
        if (animation != null) {
            resetAnimation();
        } else {
            throw new IllegalArgumentException("Animation cannot be null.");
        }
    }

    public BasicAnimation getAnimation () {
        return animation;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public FrameData setCurrentFrameIndex(int currentFrameIndex) {
        this.currentFrameIndex = currentFrameIndex;
        this.currentFrame = animation.getFrame(currentFrameIndex);
        this.durationTime = 0f; // Reset duration time when changing frame
        return this.currentFrame;
    }

    public float getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(float durationTime) {
        this.durationTime = durationTime;
    }




}
