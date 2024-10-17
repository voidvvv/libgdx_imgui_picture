package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.voidvvv.imgui.test.entity.BaseSocket;
import imgui.ImGui;
import imgui.type.ImBoolean;
import imgui.type.ImString;

public class SocketDetailPanel {
    ImString socketName = new ImString();

    byte detailNameMod = 0; // 0 fix, 1 editable
    boolean detailNewWindow = true;
    ImBoolean detailOpen = new ImBoolean(true);
    BaseSocket detailSocket = null;
    int detailSocketIndex = -1;

    public void detailReset() {
        detailSocketIndex = -1;
    }

    public void initDetailPanel(BaseSocket socket, int i) {
        detailSocket = socket;
        detailSocketIndex = i;
        detailOpen.set(true);
        detailNewWindow = true;
        detailNameMod = 0;
    }

    public void renderDetail() {
        if (!detailOpen.get()) {
            return;
        }
        if (detailSocket != null) {

            final BaseSocket socket = detailSocket;
            final int socketIndex = detailSocketIndex;
            // prepare
            if (detailNewWindow) {
                socketName.set(socket.getName());
                detailNewWindow = false;
            }
            if (ImGui.begin("detail",detailOpen)) {
                // name
                if (detailNameMod == 0) {
                    ImGui.text(socket.getName());
                } else {
                    ImGui.inputText("input name", socketName);
                }
                // same line
                ImGui.sameLine();
                // operate name
                if (detailNameMod == 0) {
                    if (ImGui.button("change")) {
                        detailNameMod = 1;
                    }
                } else {
                    if (ImGui.button("confirm")) {
                        detailNameMod = 0;
                        socket.setName(socketName.get());
                    }
                    ImGui.sameLine();
                    if (ImGui.button("cancel")) {
                        detailNameMod = 0;
                    }
                }
            }
            ImGui.end();
        } else {
            detailNewWindow = true;
        }
    }

    public void postRender() {

    }
}
