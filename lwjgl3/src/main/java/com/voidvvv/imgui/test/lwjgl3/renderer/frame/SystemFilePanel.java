package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.type.ImBoolean;
import imgui.type.ImString;

import java.io.File;

public class SystemFilePanel implements UIRender {
    public static boolean OPEN = false;
    File currentFile;
    String path;

    ImString filePath = new ImString();


    ImBoolean isOpen = new ImBoolean(false);

    public void open() {
        isOpen.set(true);
    }

    public void setDirectory(String path) {
        this.path = path;
        this.currentFile = new File(path);
    }

    @Override
    public void render() {
        updateFile();
        if (isOpen.get()) {
            if (ImGui.begin("System File Panel", isOpen)) {
                ImGui.inputText("Path", filePath);
                ImGui.sameLine();

                backFunc();
                enterKey();
                ImGui.separator();
                File[] files = currentFile.listFiles();
                if (files != null) {
                    for (File file : files) {
                        String name = file.getName();
                        if (ImGui.button(name)) {
                            if (file.isDirectory()) {
                                currentFile = file;
                                path = file.getAbsolutePath();
                            } else {
                                // Handle file selection
                                if (name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg")) {
                                    MainGame.getInstance().getFrameDataManager().setImg(file.getAbsolutePath());
                                }else {

                                    FrameUI.fileOperaPanel.tryLoad(file.getAbsolutePath());
                                }
                            }
                        }
                    }
                }
            }
            OPEN = isOpen.get();
            ImGui.end();
        }


    }

    private void enterKey() {
        if (ImGui.isKeyPressed(ImGuiKey.Enter) || ImGui.isKeyPressed(ImGuiKey.KeyPadEnter) ) {
            String s = filePath.get();
            if (s == null || s.isEmpty()) {
                return;
            }
            File file = new File(s);
            if (file.exists() && file.isDirectory()) {
                currentFile = file;
                path = currentFile.getAbsolutePath();
                filePath.set(path);
            }
        }
    }

    private void backFunc() {
        if (ImGui.button("Back")) {
            currentFile = currentFile.getParentFile();
            path = currentFile.getAbsolutePath();
            filePath.set(path);
        }
    }

    private void updateFile() {
        isOpen.set(OPEN);
        if (currentFile == null) {
            currentFile = new File("C://");
            path = currentFile.getAbsolutePath();
        }
        boolean directory = currentFile.isDirectory();
        if (!directory) {
            currentFile = currentFile.getParentFile();

        }
        path = currentFile.getAbsolutePath();
        filePath.set(path);
    }
}
