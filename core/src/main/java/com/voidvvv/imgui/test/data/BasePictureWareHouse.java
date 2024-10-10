package com.voidvvv.imgui.test.data;

import com.badlogic.gdx.graphics.Texture;
import com.voidvvv.imgui.test.entity.BasePicture;

import java.util.ArrayList;
import java.util.List;

public class BasePictureWareHouse {
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
}