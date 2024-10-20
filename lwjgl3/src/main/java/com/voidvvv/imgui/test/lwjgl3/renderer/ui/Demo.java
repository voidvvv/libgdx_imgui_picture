package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.voidvvv.imgui.test.MainGame;
import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImString;

import java.util.HashMap;
import java.util.Map;

public class Demo implements UIRender{
    boolean showAsd =  true;
    ImBoolean asdOpen = new ImBoolean(true);

    ImString file = new ImString();

    Map<Long, Texture> textures = new HashMap<Long, Texture>();
    ImageListPanel imagePanel = new ImageListPanel();

    ImageDetailPanel pointsPanel = new ImageDetailPanel();
    public Demo() {
    }

    public void render () {
        // update
        renderPanel ();

        imagePanel.render();
        pointsPanel.render();

    }

    public void renderPanel () {
        resetPanel();
        if (asdOpen.get()) {
            if (ImGui.begin("asd", asdOpen, ImGuiWindowFlags.MenuBar)) {
                if (ImGui.beginMenuBar()) {
                    if (ImGui.beginMenu("aaa")) {
                        ImGui.menuItem("Undo", "CTRL+Z");
                        ImGui.endMenu();
                    }
                    ImGui.endMenuBar();
                }

                ImGui.inputText("file_string",file);
                ImGui.text("card2.png");
                if (ImGui.button("asd")) {
                    String s = file.get();
                    if (s == null || s.isEmpty()) {
                        s = "C:\\Users\\voidvvv\\Pictures\\asset\\card2.png";
                    }
                    FileHandle file = Gdx.files.absolute(s);
                    tryToLoadImage(file); // 获取到了image
                }
            }
            ImGui.end();
        }
    }

    private void tryToLoadImage(FileHandle file) {
        try {
            long hash = (long)file.hashCode();
            if (!textures.containsKey(hash)) {
                Texture texture = new Texture(file);
                System.out.println("success");
                textures.put(hash,texture);
                System.out.println("filename: " + file.name());
                MainGame.getInstance().getBasePictureWareHouse().pushImage(texture,file.name());
            }
        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    private void resetPanel() {
        if (ImGui.isKeyDown(ImGuiKey.LeftCtrl) && ImGui.isKeyPressed(ImGuiKey.A)) {
            asdOpen.set(true);
        }
    }
}
