package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;

public class FrameUI implements UIRender {
    FileOperaPanel fileOperaPanel = new FileOperaPanel();
    @Override
    public void render() {
        fileOperaPanel.render();
    }
}
