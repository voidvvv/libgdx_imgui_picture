package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BaseSocket;
import imgui.ImGui;

public class PointsPanel {

    public void render() {
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        BasePicture mainPicture = basePictureWareHouse.getMainPicture();
        if (mainPicture == null) {
            return;
        }
        String pictureName = mainPicture.getName();
        if (ImGui.begin("PointsPanel")) {
            ImGui.text("picture name: " + pictureName);
            for (BaseSocket socket : mainPicture.getSockets()) {
                ImGui.text("socket name: " + socket.getName() + String.format(" x: %s - y:%s", socket.getxFactor(), socket.getyFactor()));
                ImGui.separator();
            }
        }
        ImGui.end();
    }
}
