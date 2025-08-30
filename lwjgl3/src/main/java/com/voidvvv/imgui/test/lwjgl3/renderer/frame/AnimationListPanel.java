package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.anim.BasicAnimation;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import com.voidvvv.imgui.test.manager.AnimationManager;
import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImString;

public class AnimationListPanel implements UIRender {
    AnimationManager animationManager;

    private final ImBoolean open = new ImBoolean(false);



    @Override
    public void render() {
        this.animationManager = MainGame.getInstance().getAnimationManager();
        if (open.get()) {
            ImGui.begin("Animation List", open);
            addAnimButton();
            ImGui.text("Animation List");
            ImGui.separator();
            renderList();
            ImGui.end();

        }

        update();
    }
    boolean addingAnimFlag = false;
    void update () {
        addAnim();
    }

    private void addAnim() {
        if (addingAnimFlag) {
            String animNameStr = animName.get();
            animationManager.addAnimation(animNameStr);
            addingAnimFlag = false;
        }
    }

    private void renderList() {
        if (animationManager.getAnimations().isEmpty()) {
            ImGui.text("No Animations Loaded");
        } else {
            for (int i = 0; i < animationManager.getAnimations().size(); i++) {
                BasicAnimation basicAnimation = animationManager.getAnimations().get(i);

                nameButton(basicAnimation);
                ImGui.sameLine();
                if (ImGui.button("Delete")) {

                }
            }
        }
    }

    private void nameButton(BasicAnimation basicAnimation) {
        if (ImGui.button(basicAnimation.getName())) {

        }
    }

    ImBoolean adding = new ImBoolean(false);
    ImString animName = new ImString();
    private void addAnimButton() {
        if (ImGui.button("Add Animation")) {
            animName.set("");
            adding.set(true);
        }
        addingWindow();
    }

    private void addingWindow() {
        if (adding.get()) {
            if (ImGui.begin("input animation name", adding, ImGuiWindowFlags.AlwaysAutoResize)) {
                ImGui.inputText("name", animName);
                ImGui.newLine();
                confirmButton();
                cancelButton();
                ImGui.end();
            }
        }
    }

    private void cancelButton() {
        if (ImGui.button("Cancel")) {
            adding.set(false);

        }
    }

    private void confirmButton() {
        if (ImGui.button("Confirm")) {
            addingAnimFlag = true;
            adding.set(false);
        }
    }

    public void setOpen(boolean open) {
        this.open.set(open);
    }
}
