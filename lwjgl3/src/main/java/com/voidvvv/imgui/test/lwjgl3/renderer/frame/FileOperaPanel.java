package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.frame.FrameData;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import com.voidvvv.imgui.test.manager.FrameAttackCheckColorManager;
import imgui.ImGui;
import imgui.flag.ImGuiColorEditFlags;
import imgui.flag.ImGuiKey;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.type.ImBoolean;
import imgui.type.ImFloat;
import imgui.type.ImString;

public class FileOperaPanel implements UIRender {
    ImBoolean open = new ImBoolean(true);

    ImString tip = new ImString(TIP_DEFAULT);
    final static String TIP_DEFAULT = "No Frame Data Loaded";
    final static String TIP_ERROR = "Path illegal, please input a valid path";

    ImString filePath = new ImString();

    @Override
    public void render() {
        if (open.get()) {
            ImGui.begin("FileOperaPanel", open);
            loadFilePenal();

            ImGui.end();
        }

        update();
    }

    private void loadFilePenal() {
        ImGui.inputText("file", filePath);
        ImGui.sameLine();
        if (ImGui.button("openFileSys")) {
            SystemFilePanel.OPEN = true;
        }
        if (ImGui.button("load")) {
            loadFile = true;
        } else {
            loadFile = false;
        }
    }


    boolean loadFile = false;

    void update() {
        openFlag();
        loadFileFromPath();
//        updateRect
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
            try {
                FileHandle fh = Gdx.files.absolute("");
                if (isPic(fh)) {
//                    MainGame.getInstance().getAnimationManager().loadAnimByPic(fh);
                }

            } catch (Exception e) {
                tip.set(TIP_ERROR + "\n" + e.getMessage());
            }
            loadFile = false;
        }
    }

    private boolean isPic(FileHandle fh) {
        return fh != null && (fh.extension().equalsIgnoreCase("png") || fh.extension().equalsIgnoreCase("jpg")
                || fh.extension().equalsIgnoreCase("jpeg") || fh.extension().equalsIgnoreCase("gif"));
    }
}
