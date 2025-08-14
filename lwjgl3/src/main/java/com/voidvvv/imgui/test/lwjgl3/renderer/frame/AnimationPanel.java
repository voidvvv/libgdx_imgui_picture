package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.AnimationPlayer;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import com.voidvvv.imgui.test.manager.AnimationManager;
import imgui.ImGui;
import imgui.type.ImBoolean;

public class AnimationPanel implements UIRender {
    ImBoolean show = new ImBoolean(false);
    AnimationPlayer player = null;
    BasicAnimation basicAnimation;
    @Override
    public void render() {
        if (updateParam()) {
            int frameCount = basicAnimation.getFrameCount();
            ImGui.begin("Animation Panel", show);
            for (int i = 0; i < frameCount; i++) {
                if (ImGui.button("Frame " + i)) {
                    MainGame.getInstance().getFrameDataManager().setCurrentFrameData(
                        basicAnimation.getFrame(i)
                    );
                }

            }
        }
    }

    private boolean updateParam() {
        player = MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer();
        basicAnimation = MainGame.getInstance().getAnimationManager().getBasicAnimation();
        show.set(MainGame.getInstance().getAnimationManager().getBasicAnimation() == null);
        return show.get() && player != null && basicAnimation != null;
    }
}
