package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImString;

public class FileOperaPanel implements UIRender {
    ImBoolean open = new ImBoolean(true);

    ImString filePath = new ImString();
    @Override
    public void render() {
        if (open.get()) {
            if (ImGui.begin("FileOperaPanel", open)) {
                ImGui.inputText("file", filePath);
                if (ImGui.button("load")) {
                    loadFile = true;
                } else {
                    loadFile = false;
                }


            }

            // rect cnt
            detailTmp();
            ImGui.end();
        }

        update();
    }

    private void detailTmp() {
        FrameData currentFrameData = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();
        if (currentFrameData != null) {
            ImGui.text("renderOffset: " + currentFrameData.getRenderOffset());
            ImGui.text("durationTime: " + currentFrameData.getDurationTime());
            ImGui.text("textureRegion: " + currentFrameData.getTextureRegion().getTexture().getWidth() + "x" +
                    currentFrameData.getTextureRegion().getTexture().getHeight());
            ImGui.text("attackCheckRects size: " + currentFrameData.getAttackCheckRects().size());
        } else {
            ImGui.text("No Frame Data Loaded");
        }
    }

    boolean loadFile = false;
    void update() {
        loadFileFromPath();
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
