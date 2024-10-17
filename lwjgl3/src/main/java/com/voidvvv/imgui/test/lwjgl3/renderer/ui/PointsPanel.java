package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BaseSocket;
import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.type.ImBoolean;

public class PointsPanel {
    int socketIndex = -1;
    int deleteSocketIndex = -1;
    SocketDetailPanel socketDetailPanel;

    ImBoolean shouldAdd= new ImBoolean(false);
    public PointsPanel() {
        socketDetailPanel = new SocketDetailPanel();
    }

    public void render() {
        reset();
        beforeRender();
        panelRender();
        postRender();
    }

    private void beforeRender() {

        // should add socket
        if (ImGui.isKeyDown(ImGuiKey.LeftCtrl) && ImGui.isKeyPressed(ImGuiKey.S)) {
            shouldAdd.set(!shouldAdd.get());
        }
    }

    private void reset() {
        socketIndex = -1;
        deleteSocketIndex = -1;
//        detailSocket = null;
        socketDetailPanel.detailReset();
    }

    private void panelRender() {
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        BasePicture mainPicture = basePictureWareHouse.getMainPicture();
        if (mainPicture == null) {
            return;
        }
        String pictureName = mainPicture.getName();
        if (ImGui.begin("PointsPanel")) {
            ImGui.text("picture name: " + pictureName);
            ImGui.sameLine();
            ImGui.separator();
            ImGui.sameLine();
            ImGui.checkbox("couldAdd", shouldAdd);
            for (int i = 0; i < mainPicture.getSockets().size(); i++) {
                BaseSocket socket = mainPicture.getSockets().get(i);

                String des = "socket name: " + socket.getName() + String.format(" x: %s - y:%s", socket.getX(), socket.getY());
                if (ImGui.selectable(des)) {
                    socketDetailPanel.initDetailPanel(socket,i);
                };
            }
        }
        ImGui.end();
        socketDetailPanel.renderDetail();
    }


    public void postRender() {
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        if (socketIndex >= 0) {
            basePictureWareHouse.socketSelect(socketIndex);
        }

        if (deleteSocketIndex >= 0) {
            basePictureWareHouse.deleteSocket(deleteSocketIndex);
        }
        socketDetailPanel.postRender();

        basePictureWareHouse.shouldAdd = shouldAdd.get();
    }
}
