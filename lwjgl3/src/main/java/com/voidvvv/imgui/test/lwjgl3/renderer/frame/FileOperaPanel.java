package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

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

    final static String TIP_DEFAULT = "No Frame Data Loaded";
    final static String TIP_ERROR = "Path illegal, please input a valid path";

    ImString filePath = new ImString();
    @Override
    public void render() {
        if (open.get()) {
            ImGui.begin("FileOperaPanel", open);
            loadFilePenal();

            // rect cnt
            detailTmp();
            ImGui.end();
        }

        update();
    }

    private void loadFilePenal() {
        ImGui.inputText("file", filePath);
        if (ImGui.button("load")) {
            loadFile = true;
        } else {
            loadFile = false;
        }
    }
    boolean edit = true;

    ImFloat renderOffsetX = new ImFloat(0);
    ImFloat renderOffsetY = new ImFloat(0);
    ImFloat durationTime = new ImFloat(0);

    ImString tip = new ImString(TIP_DEFAULT);
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
                ImGui.separator();
                addNewRectButton(currentFrameData);
                ImGui.newLine();
                rectList(currentFrameData);
            }
        } else {
            ImGui.text(tip.get());
        }
    }
    ImFloat tmpX = new ImFloat(0);
    ImFloat tmpY = new ImFloat(0);
    ImFloat tmpW = new ImFloat(50);
    ImFloat tmpH = new ImFloat(50);
    private void rectList(FrameData currentFrameData) {
        if (ImGui.beginChild("rectList", ImGuiWindowFlags.AlwaysAutoResize)) {
            FrameAttackCheckColorManager colorManager = MainGame.getInstance().getColorManager();
            for (int i = 0; i < currentFrameData.getAttackCheckRects().size(); i++) {
                com.voidvvv.imgui.test.entity.frame.AttackCheck attackCheck = currentFrameData.getAttackCheckRects().get(i);
                ImGui.pushID(i);
                Color color = colorManager.getColor(i);
                ImGui.colorButton("rect " + i, color.r, color.g, color.b, 1f, ImGuiColorEditFlags.NoTooltip);
                ImGui.text("rect " + i);
                ImGui.sameLine();
                if (ImGui.button("remove")) {
                    currentFrameData.getAttackCheckRects().remove(attackCheck);
                    ImGui.popID();
                    break;
                }
                Rectangle rectangle = attackCheck.rectangle;
                tmpX.set(rectangle.x);
                tmpY.set(rectangle.y);
                tmpW.set(rectangle.width);
                tmpH.set(rectangle.height);
                ImGui.inputFloat("x", tmpX);
                ImGui.inputFloat("y", tmpY);
                ImGui.inputFloat("w", tmpW);
                ImGui.inputFloat("h", tmpH);
                ImGui.separator();
                ImGui.popID();
            }
            ImGui.endChild();

        }

    }

    private void addNewRectButton(FrameData currentFrameData) {
        if (ImGui.button("add new AttackCheck Rect")) {
            currentFrameData.getAttackCheckRects().add(new com.voidvvv.imgui.test.entity.frame.AttackCheck(new Rectangle(0f, 0f, 50, 50), "physic", 1));
        }

    }

    boolean loadFile = false;
    void update() {
        openFlag();
        loadFileFromPath();
        updateFrameData();
//        updateRect
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
            try {
                MainGame.getInstance().getFrameDataManager().setImg(s);
            } catch (Exception e) {
                tip.set(TIP_ERROR + "\n" + e.getMessage());
            }
            loadFile = false;
        }
    }
}
