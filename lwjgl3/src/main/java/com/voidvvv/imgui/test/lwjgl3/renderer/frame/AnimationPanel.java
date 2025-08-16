package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.AnimationPlayer;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.entity.frame.FrameData;
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
                if (ImGui.button(nameOfFrame(i))) {
                    MainGame.getInstance().getFrameDataManager().setCurrentFrameData(
                        basicAnimation.getFrame(i)
                    );
                }

            }
            ImGui.end();
        }
    }

    private String nameOfFrame(int i) {
        FrameData frame = basicAnimation.getFrame(i);
        TextureRegion textureRegion = frame.getTextureRegion();
        return textureRegion == null ? "": AnimationManager.nameOfTexture(textureRegion);
    }

    private boolean updateParam() {
        player = MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer();
        basicAnimation = MainGame.getInstance().getAnimationManager().getBasicAnimation();
        show.set(MainGame.getInstance().getAnimationManager().getBasicAnimation() != null);
        return show.get() && player != null && basicAnimation != null;
    }
}
