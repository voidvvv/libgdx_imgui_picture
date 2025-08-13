package com.voidvvv.imgui.test.lwjgl3.renderer.frame;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.lwjgl3.renderer.ui.UIRender;
import com.voidvvv.imgui.test.manager.DebugManager;
import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;

public class DebugMessagePenal implements UIRender {
    ImBoolean open = new ImBoolean(true);

    @Override
    public void render() {
        if (open.get()) {
            if (ImGui.begin("Debug Messages", open)) {
                ImGui.text("Debug Messages");
                ImGui.separator();
                ImGui.beginChild("##debugMessages", 0, 200, true, ImGuiWindowFlags.AlwaysAutoResize);
                DebugManager debugManager = MainGame.getInstance().getDebugManager();
                // Render debug messages
                for (String msg : debugManager.debugMessages) {
                    ImGui.text(msg);
                }
                // sycn rolling bar
                ImGui.setScrollY(ImGui.getScrollY() + ImGui.getTextLineHeight());
                ImGui.endChild();

            }
            ImGui.end();
        }

        update();
    }

    void update() {
        if (ImGui.isKeyDown(ImGuiKey.LeftCtrl) && ImGui.isKeyPressed(ImGuiKey.S)) {
            open.set(true);
        }
    }
}
