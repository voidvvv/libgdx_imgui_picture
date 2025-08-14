package com.voidvvv.imgui.test.input;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.entity.frame.FrameData;

public class FrameDataRenderListener extends InputListener {
    boolean drag = false;

    Vector2 lastPos = new Vector2();

    FrameData currentFrameData = null;

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y) {

//        lastPos.set(x, y);
//        MainGame.getInstance().getDebugManager().pushMsg("mouseMoved: " + x + ", " + y);
        return super.mouseMoved(event, x, y);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        drag = true;
        lastPos.set(x, y);
        currentFrameData = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//        System.out.println("touchUp: " + x + ", " + y);
        drag = false;
        currentFrameData = null;
        super.touchUp(event, x, y, pointer, button);
    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        FrameData global = MainGame.getInstance().getFrameDataManager().getCurrentFrameData();
        if (currentFrameData == global && drag) {
            MainGame.getInstance().getDebugManager().pushMsg("touchDragged: " + x + ", " + y);

            currentFrameData.getRenderOffset().set(
                currentFrameData.getRenderOffset().x + x - lastPos.x,
                currentFrameData.getRenderOffset().y + y - lastPos.y
            );

        } else {
            drag = false;
            currentFrameData = null;
        }
//        lastPos.set(x, y);
        super.touchDragged(event, x, y, pointer);
    }
}
