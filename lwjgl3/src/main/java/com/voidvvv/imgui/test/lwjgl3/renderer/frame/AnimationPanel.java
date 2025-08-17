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
import imgui.ImVec4;
import imgui.type.ImBoolean;

public class AnimationPanel implements UIRender {
    ImBoolean show = new ImBoolean(false);
    AnimationPlayer player = null;
    BasicAnimation basicAnimation;

    @Override
    public void render() {
        if (updateParam()) {
            int frameCount = basicAnimation.getFrameCount();
            int currentFrameIndex = player.getCurrentFrameIndex();
            ImGui.begin("Animation Panel", show);
            playButton();
            stopButton();
            for (int i = 0; i < frameCount; i++) {
                ImGui.pushID(i);
                if (frameButton(i, currentFrameIndex)) {
                    MainGame.getInstance().getAnimationPlayerManager().specifyFrame(i, basicAnimation);
                }
                ImGui.sameLine();
                ImGui.separator();
                if (deleteButton(i)) {
                    MainGame.getInstance().getAnimationManager().deleteFrame(i);
                }
                ImGui.popID();
            }
            ImGui.end();
        }
    }

    private boolean deleteButton(int i) {
        return ImGui.button("delete");
    }

    ImVec4 color = new ImVec4(0.5f, 0.5f, 0.5f, 1f);

    private boolean frameButton(int i, int currentFrameIndex) {
        if (i == currentFrameIndex) {
            return
                ImGui.colorButton(nameOfFrame(i), color);
        }
        return ImGui.button(nameOfFrame(i));
    }

    public void stopButton() {
        if (ImGui.button("stop")) {
            player.stop();
        }
    }

    private void playButton() {
        if (ImGui.button("Play")) {
            player.play();
        }
    }

    private String nameOfFrame(int i) {
        FrameData frame = basicAnimation.getFrame(i);
        return frame.getName();
    }

    private boolean updateParam() {
        basicAnimation = MainGame.getInstance().getAnimationManager().getBasicAnimation();

        player = MainGame.getInstance().getAnimationPlayerManager().getAnimationPlayer(basicAnimation);
        show.set(MainGame.getInstance().getAnimationManager().getBasicAnimation() != null);
        return show.get() && player != null && basicAnimation != null;
    }
}
