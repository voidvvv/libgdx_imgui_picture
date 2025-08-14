package com.voidvvv.imgui.test.manager;

import com.voidvvv.imgui.test.entity.anim.BasicAnimation;

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
}
