package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.entity.BasePicture;
import imgui.ImGui;
import imgui.ImVec2;

public class ImageListPanel {
    boolean render = true;
    ImVec2 picSize = new ImVec2(50, 50);
    int tmpMainPic = -1;
    BasePictureWareHouse basePictureWareHouse = null;

    public void render() {
        renderMainPanel();

        postRender();
    }

    private void renderMainPanel() {
        tmpMainPic = -1;
        basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        int totalSize = basePictureWareHouse.getTotalSize();
        if (render) {
            if (ImGui.begin("images_01")) {
                picSize.set(ImGui.getWindowSizeX() * 0.75f, ImGui.getWindowSizeY() * 0.75f);


                for (int x = 0; x < totalSize; x++) {
                    BasePicture pic = basePictureWareHouse.getPic(x);
                    if (ImGui.imageButton(pic.getTextureId(), picSize)) {
                        tmpMainPic = x;
                    }
                    ImGui.text(pic.getName());
                    ImGui.sameLine();
                    if (ImGui.button("edit")) {
                        tmpMainPic = x;
                    }
                }
            }
            ImGui.end();
        }


    }

    public void postRender() {
        // post logic
        if (tmpMainPic >= 0) {
            basePictureWareHouse.setMainPicture(tmpMainPic);
        }
    }
}
