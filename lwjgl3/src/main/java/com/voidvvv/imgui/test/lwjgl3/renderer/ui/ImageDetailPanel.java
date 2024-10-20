package com.voidvvv.imgui.test.lwjgl3.renderer.ui;

import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.data.BasePictureWareHouse;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BasePolygon;
import com.voidvvv.imgui.test.entity.BaseSocket;
import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.type.ImBoolean;
import imgui.type.ImInt;

import java.util.List;

public class ImageDetailPanel {
    public static final int TYPE_SOCKETS = 0;
    public static final int TYPE_POLYGON = 1;

    int socketIndex = -1;
    int deleteSocketIndex = -1;
    SocketDetailPanel socketDetailPanel;
    PolygonDetailPenal polygonDetailPenal;
    ImInt typeInt = new ImInt(0);

    String[] types = {"sockets", "polygons"};

    ImBoolean shouldAdd = new ImBoolean(false);

    boolean polygonAddFlag = false;

    public ImageDetailPanel() {
        socketDetailPanel = new SocketDetailPanel();
        polygonDetailPenal = new PolygonDetailPenal();
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
        // socket penal
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        BasePicture mainPicture = basePictureWareHouse.getMainPicture();
        if (mainPicture == null) {
            return;
        }
        String pictureName = mainPicture.getName();
        if (ImGui.begin("ImageDetailPanel")) {
            ImGui.text("picture name: " + pictureName);
            ImGui.sameLine();
            ImGui.separator();
            ImGui.sameLine();
            ImGui.combo("type select", typeInt, types);
            // points
            renderPointsList(mainPicture);
            // polygon penal
            renderPolygonList(mainPicture);
        }
        ImGui.end();


        socketDetailPanel.renderDetail();
        polygonDetailPenal.render();
    }

    private void renderPolygonList(BasePicture mainPicture) {
        if (typeInt.get() != TYPE_POLYGON) {
            return;
        }
        polygonAddFlag = ImGui.button("add Polygon");
        if (ImGui.collapsingHeader("Shapes")) {
            List<BasePolygon> basePolygons = mainPicture.getBasePolygons();
            if (basePolygons != null) {
                for (int i = 0; i < basePolygons.size(); i++) {
                    BasePolygon basePolygon = basePolygons.get(i);
                    String des = basePolygon.getName();
                    if (ImGui.selectable(des)) {
                        polygonDetailPenal.init(mainPicture, i);
                    }
                }
            }
        }
    }

    private void renderPointsList(BasePicture mainPicture) {
        if (typeInt.get() != TYPE_SOCKETS) {
            return;
        }
        ImGui.checkbox("couldAddSockets", shouldAdd);
        if (ImGui.collapsingHeader("Points")) {
            for (int i = 0; i < mainPicture.getSockets().size(); i++) {
                BaseSocket socket = mainPicture.getSockets().get(i);
                String des = "socket name: " + socket.getName() + String.format(" x: %s - y:%s", socket.getxStart(), socket.getyStart());
                if (ImGui.selectable(des)) {
                    socketDetailPanel.initDetailPanel(socket, i);
                }
                ;
            }
        }
    }


    public void postRender() {
        if (typeInt.get() == TYPE_POLYGON) {
            shouldAdd.set(false);
        }
        BasePictureWareHouse basePictureWareHouse = MainGame.getInstance().getBasePictureWareHouse();
        if (socketIndex >= 0) {
            basePictureWareHouse.socketSelect(socketIndex);
        }

        if (deleteSocketIndex >= 0) {
            basePictureWareHouse.deleteSocket(deleteSocketIndex);
        }
        socketDetailPanel.postRender();

        basePictureWareHouse.shouldAdd = shouldAdd.get();

        if (polygonAddFlag) {
            basePictureWareHouse.addPolygon();
            polygonAddFlag = false;
        }
    }
}
