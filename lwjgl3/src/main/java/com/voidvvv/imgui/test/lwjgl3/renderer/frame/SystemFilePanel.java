package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import imgui.ImGui;
import imgui.type.ImBoolean;

import java.io.File;

public class SystemFilePanel implements UIRender {
    File currentFile;
    String path;


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
        boolean directory = currentFile.isDirectory();
        if (!directory) {
            currentFile = currentFile.getParentFile();
            path = currentFile.getAbsolutePath();
        }

        if (ImGui.begin("System File Panel", isOpen)) {
            ImGui.text("Current Directory: " + path);
            ImGui.separator();

            if (ImGui.button("Back")) {
                currentFile = currentFile.getParentFile();
                path = currentFile.getAbsolutePath();
            }

            File[] files = currentFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (ImGui.button(file.getName())) {
                        if (file.isDirectory()) {
                            currentFile = file;
                            path = file.getAbsolutePath();
                        } else {
                            // Handle file selection
                            System.out.println("Selected file: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }

    }
}
