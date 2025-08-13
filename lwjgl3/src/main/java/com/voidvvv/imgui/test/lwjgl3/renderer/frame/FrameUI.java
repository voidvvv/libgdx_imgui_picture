package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import imgui.ImGui;

public class FrameUI implements UIRender {
    FileOperaPanel fileOperaPanel = new FileOperaPanel();
    DebugMessagePenal debugMessagePenal = new DebugMessagePenal();

    SystemFilePanel systemFilePanel = new SystemFilePanel();

    @Override
    public void render() {
        fileOperaPanel.render();
        debugMessagePenal.render();
        systemFilePanel.render();

    }
}
