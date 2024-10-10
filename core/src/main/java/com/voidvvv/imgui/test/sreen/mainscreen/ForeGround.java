package com.voidvvv.imgui.test.sreen.mainscreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.voidvvv.imgui.test.MainGame;
import com.voidvvv.imgui.test.base.DrawUpdatable;
import com.voidvvv.imgui.test.data.MainScreenGroundData;

public class ForeGround  implements DrawUpdatable {
    private SpriteBatch currentBatch;
    MainGame instance = MainGame.getInstance();
    MainScreenGroundData data;
    BitmapFont font;
    public ForeGround(MainScreenGroundData data) {
        this.data = data;
    }

    @Override
    public void update(float delta) {
        font = MainGame.getInstance().getAssetManager().get(MainGame.BASIC_FONT_NAME,BitmapFont.class);
        currentBatch = MainGame.getInstance().getDrawManager().getBaseBatch();

    }

    @Override
    public void draw() {
        renderText();
    }


    private void renderText() {
        OrthographicCamera mainCamera = instance.getCameraManager().getMainCamera();
        currentBatch.begin();
        currentBatch.setProjectionMatrix(mainCamera.combined);
        float s = this.data.xs;
        while (s <= this.data.rightUp.x){
            coordinateText(s,this.data.rightUp.y,s+"");
            s+=this.data.unit;
        }
        s = this.data.ys;
        while (s <= this.data.rightUp.y){
//            drawHorizonLine(s,shapeRenderer);
            coordinateText(this.data.leftDown.x,s,s+"");
            s+=this.data.unit;
        }
        currentBatch.end();
    }

    private void coordinateText(float s, float y,String text) {
        font.draw(currentBatch,text,s,y);

    }
}
