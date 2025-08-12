package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImString;

public class FileOperaPanel implements UIRender {
    ImBoolean open = new ImBoolean(true);

    ImString filePath = new ImString();
    @Override
    public void render() {
        if (open.get()) {
            loadFilePenal();

            // rect cnt
            detailTmp();
            ImGui.end();
        }

        update();
    }

    private void loadFilePenal() {
        if (ImGui.begin("FileOperaPanel", open)) {
            ImGui.inputText("file", filePath);
            if (ImGui.button("load")) {
                loadFile = true;
            } else {
                loadFile = false;
            }
        }
    }
    boolean edit = true;

    ImFloat renderOffsetX = new ImFloat(0);
    ImFloat renderOffsetY = new ImFloat(0);
    ImFloat durationTime = new ImFloat(0);
    private void detailTmp() {
        FrameData currentFrameData = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();

        if (currentFrameData != null) {
            if (!edit) {
                ImGui.text("View Mode");
                ImGui.text("renderOffset: " + currentFrameData.getRenderOffset());
                ImGui.text("durationTime: " + currentFrameData.getDurationTime());
                ImGui.text("textureRegion: " + currentFrameData.getTextureRegion().getTexture().getWidth() + "x" +
                    currentFrameData.getTextureRegion().getTexture().getHeight());
                ImGui.text("attackCheckRects size: " + currentFrameData.getAttackCheckRects().size());
            } else {
                renderOffsetX.set(currentFrameData.getRenderOffset().x);
                renderOffsetY.set(currentFrameData.getRenderOffset().y);
                durationTime.set(currentFrameData.getDurationTime());
                ImGui.text("Edit Mode");
                ImGui.newLine();
                ImGui.text("renderOffset");
                ImGui.inputFloat(" x: ", renderOffsetX);
                ImGui.inputFloat(" y: ", renderOffsetY);
                ImGui.newLine();
                ImGui.inputFloat("durationTime", durationTime);
                ImGui.newLine();
                ImGui.text("textureRegion: " + currentFrameData.getTextureRegion().getTexture().getWidth() + "x" +
                    currentFrameData.getTextureRegion().getTexture().getHeight());
                ImGui.text("attackCheckRects size: " + currentFrameData.getAttackCheckRects().size());
            }
        } else {
            ImGui.text("No Frame Data Loaded");
        }
    }

    boolean loadFile = false;
    void update() {
        openFlag();
        loadFileFromPath();
        updateFrameData();
    }

    private void updateFrameData() {
        FrameData currentFrameData = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();
        if (currentFrameData != null) {
            if (edit) {
                currentFrameData.getRenderOffset().set(renderOffsetX.get(), renderOffsetY.get());
                currentFrameData.setDurationTime(durationTime.get());
            }
        }
    }

    private void openFlag() {
        if (ImGui.isKeyDown(ImGuiKey.LeftCtrl) && ImGui.isKeyPressed(ImGuiKey.A)) {
            open.set(true);
        }
    }

    private void loadFileFromPath() {
        if (loadFile) {
            String s = filePath.get();
            if (s == null || s.isEmpty()) {
                s = "C:\\Users\\voidvvv\\Pictures\\asset\\card2.png";
                filePath.set(s);
            }
            // 触发加载
            MainGame.getInstance().getFrameDataManager().setImg(s);
            loadFile = false;
        }
    }
}
