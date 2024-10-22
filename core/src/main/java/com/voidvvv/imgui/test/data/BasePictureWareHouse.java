package com.voidvvv.imgui.test.data;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BasePolygon;
import com.voidvvv.imgui.test.entity.BaseSocket;
import com.voidvvv.imgui.test.operations.OperationStack;

import java.util.ArrayList;
import java.util.List;

public class BasePictureWareHouse {

    public boolean shouldAdd = false;
    public boolean shouldAddToPolygon = false;

    BasePicture mainPicture;

    List<BasePicture> basePictureList = new ArrayList<>();

    private BasePolygon currentPolygon;

    public BasePolygon getCurrentPolygon() {
        return currentPolygon;
    }

    public void setCurrentPolygon(BasePolygon currentPolygon) {
        this.currentPolygon = currentPolygon;
    }

    public void pushImage(Texture texture, String name) {
        this.basePictureList.add(new BasePicture(texture, name));

    }

    public int getTotalSize() {
        return basePictureList.size();
    }

    public BasePicture getPic(int i) {
        return basePictureList.get(i);
    }

    public BasePicture getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(BasePicture mainPicture) {
        this.mainPicture = mainPicture;
    }

    public void setMainPicture(int i) {
        this.setMainPicture(basePictureList.get(i));
    }

    public Vector3 tmpSocketVector = new Vector3();

    public boolean pressed = false;

    public void preAddSocket(Vector3 screen) {
        pressed = true;
        tmpSocketVector.set(screen);
//        if (shouldAdd) {
//
//            addNewSocketFlag = true;
//            addNewSocketToPolygonFlag = false;
//        }
//        if (shouldAddToPolygon) {
//            tmpSocketVector.set(screen);
//            addNewSocketToPolygonFlag = true;
//            addNewSocketFlag = false;
//        }
    }

    public void addNewSocket() {
        BasePicture currentPicture = getMainPicture();
        if (currentPicture == null) {
            return;
        }
        OrthographicCamera mainCamera = MainGame.getInstance().getCameraManager().getMainCamera();
        mainCamera.unproject(tmpSocketVector);
        currentPicture.addNewSocket(tmpSocketVector);

        OperationStack operationStack = MainGame.getInstance().getOperationStack();
        operationStack.addSocket(currentPicture, currentPicture.getSockets().size() - 1);
    }

    public void socketSelect(int socketIndex) {
        if (mainPicture == null) {
            return;
        }
        List<BaseSocket> sockets = mainPicture.getSockets();

    }

    public void deleteSocket(int deleteSocketIndex) {
        if (mainPicture == null) {
            return;
        }
        mainPicture.deleteSocket(deleteSocketIndex);
    }

    public void addNewSocketToPolygon() {
        final BasePolygon polygon = currentPolygon;
        if (polygon == null) {
            return;
        }
        OrthographicCamera mainCamera = MainGame.getInstance().getCameraManager().getMainCamera();
        mainCamera.unproject(tmpSocketVector);
        polygon.add(tmpSocketVector.x, tmpSocketVector.y);
    }


    public void addPolygon() {
        if (this.mainPicture == null) {
            return;
        }
        List<BasePolygon> basePolygons = mainPicture.getBasePolygons();
        String name = "polygon_" + (mainPicture.polygonIndexMax++);
        BasePolygon polygon = new BasePolygon(name, mainPicture.x, mainPicture.y, mainPicture.x, mainPicture.y);
        basePolygons.add(polygon);
    }
}
