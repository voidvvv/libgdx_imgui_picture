package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
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
            if (animationManager.getAnimations().isEmpty()) {
                ImGui.text("No Animations Loaded");
            } else {
                for (int i = 0; i < animationManager.getAnimations().size(); i++) {
                    String name = animationManager.getAnimations().get(i).getName();
                    if (ImGui.button(name)) {

                    }
                    ImGui.sameLine();
                    if (ImGui.button("Delete")) {

                    }
                }
            }
            ImGui.end();

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
            adding.set(false);
        }
    }

    public void setOpen(boolean open) {
        this.open.set(open);
    }
}
