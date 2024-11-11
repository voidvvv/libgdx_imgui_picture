package com.voidvvv.imgui.test.stage;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.asset.BasePictureRender;
import com.voidvvv.imgui.test.data.MainScreenGroundData;
import com.voidvvv.imgui.test.stage.components.BackGround;
import com.voidvvv.imgui.test.stage.components.ForeGround;

public class MainRoot extends Group {
    BackGround backGround;
    MainScreenGroundData data;
    ForeGround foreGround;
    public MainRoot() {

    }

    public void init () {
        BasePictureRender basePictureRender = MainGame.getInstance().getBasePictureRender();

        data = new MainScreenGroundData();
        backGround = new BackGround(data);
        foreGround = new ForeGround(data);
        addActor(backGround);
        addActor(basePictureRender);
        addActor(foreGround);
    }
}
