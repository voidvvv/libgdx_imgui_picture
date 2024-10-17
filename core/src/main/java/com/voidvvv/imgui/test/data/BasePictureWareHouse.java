package com.voidvvv.imgui.test.data;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.BasePicture;
import com.voidvvv.imgui.test.entity.BaseSocket;

import java.util.ArrayList;
import java.util.List;

public class BasePictureWareHouse {

    public boolean shouldAdd = false;

    public boolean addNewSocketFlag = false;

    BasePicture mainPicture;

    List<BasePicture> basePictureList = new ArrayList<>();

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

    public void preAddSocket(Vector3 screen) {
        if (shouldAdd) {
            tmpSocketVector.set(screen);
            addNewSocketFlag = true;
        }
    }

    public void addNewSocket() {
        BasePicture currentPicture = getMainPicture();
        if (currentPicture == null) {
            addNewSocketFlag = false;
            return;
        }
        OrthographicCamera mainCamera = MainGame.getInstance().getCameraManager().getMainCamera();
        mainCamera.unproject(tmpSocketVector);
        currentPicture.addNewSocket(tmpSocketVector);
        addNewSocketFlag = false;
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
}
